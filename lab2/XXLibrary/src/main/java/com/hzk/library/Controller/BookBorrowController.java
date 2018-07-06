/**
 * 
 */
package com.hzk.library.Controller;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzk.library.Common.ResultWithMsg;
import com.hzk.library.Dao.FineRepository;
import com.hzk.library.Entity.BookBorrow;
import com.hzk.library.Entity.Fine;
import com.hzk.library.Entity.FineItem;
import com.hzk.library.Entity.FineType;
import com.hzk.library.Entity.User;
import com.hzk.library.Service.BookBorrowService;
import com.hzk.library.Service.FineService;
import com.hzk.library.Service.UserService;
import com.hzk.library.VO.BookBorrowVO;
import com.hzk.library.VO.FineVO;

/**
 * @author linzihan
 *
 */
@Controller
public class BookBorrowController {
	
	@Autowired
	private BookBorrowService bbs;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FineService fineservice;
	
	@Autowired
	private FineRepository fineReository;
	
	//1、续借某本图书
	@PutMapping("/bookborrow/{bookid}")
	@ResponseBody
	@Secured({"ROLE_Reader","ROLE_Reader_VIP"})
	public int WriteBookReBorrow(@PathVariable int bookid){
		int res = bbs.WriteBookBorrowAgain(bookid);
		System.out.println("图书续借数量="+res);
		return res;
	}
	
	/**
	 * 2、获取未还图书的借阅记录
	 * @param bookid
	 * @return
	 */
	@GetMapping("/bookreturn/{bookid}")
	@ResponseBody
    public BookBorrowVO getNotReturned(@PathVariable int bookid){
	   return bbs.getByBook(bookid);
    }
	
	/**
	 * 3、发起还书的输入界面
	 * @return
	 */
	//@Secured("ROLE_ADMIN")
	@GetMapping("/bookreturn")
	public String bookreturnInput(){
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String username;
//		if(principal instanceof UserDetails ){
//			username= ((UserDetails) principal).getUsername();
//			Collection<? extends GrantedAuthority> authorities = ((UserDetails) principal).getAuthorities();
//			for (GrantedAuthority grantedAuthority : authorities) {
//				System.out.println("权限="+grantedAuthority.getAuthority());
//			}
//		}else username = principal.toString();
//		System.out.println("登陆用户名="+username);
		
		return "bookborrow/returninput";
	}
	
	/**
	 * 4、提交还书的Post请求
	 * @return
	 */
	@PostMapping("/bookreturn")
	public String bookReturnSave(@RequestParam List<Integer> bookid){
		bbs.WriteReturnBook(bookid);
		return "redirect:/bookreturn";
	}
	
	//5、取得某本书的是否可借、书名等信息
	@GetMapping("/bookborrow/selectbook/{bookid}")
	@ResponseBody
	public ResultWithMsg canBorrowed(@PathVariable Integer bookid) {
		return bbs.getBook(bookid);
	}
	
	//6、发起借书的输入界面
	@GetMapping("/bookborrow")
	public String bookborrowInput(){
		return "bookborrow/borrowinput";
	}
	
	/**
	 * 7、提交借书的Post请求
	 * @param readerid
	 * @param book
	 * @return
	 */
	@PostMapping("/bookborrow")
	@Transactional
	public String bookborrowSave(@RequestParam String readerid,@RequestParam List<Integer> book){
		
		//逐本书检查该书是否可以出借：
		//      1、只有本书的状态=0“表示可借阅”时才可以。
		//      2、本书的状态=1“表示已借出”时，提供借阅人的编号（借书证）后，且不超过续借次数，且未超期，就可以续借。
		
		ResultWithMsg resultWithMsg=bbs.canBorrowed(book);
		
		if(resultWithMsg.isResult()){//都可以借阅
			bbs.WriteBorrowBook(readerid, book);
			return "redirect:/bookborrow";
		}
		else{   //如果有不能借阅的书
			
			return "redirect:/bookborrow";
		}
		
	}
	
	/**
	 * 8、得到某读者的借还书记录：
	 * 1、全部未还书
	 * 2、全部借阅记录
	 * @param userid
	 * @param model
	 * @return
	 */	
	@GetMapping("/bookborrow/byreader/{userid}")
	@Transactional
	public String bookBorrowedByUser(@RequestParam(value="fetchall",required=false,defaultValue="false") Boolean fetchAll, @PathVariable("userid") String userid,Model model){
		
		List<BookBorrow> bookBorrows;
		//第一步要刷新借书记录：是超期的标记为超期；
		bbs.refreshOvertime();
		
		//if(fetchAll !=null && "true".equals(fetchAll.toLowerCase()))  //为真则得到所有借书记录
		if(fetchAll)
		    bookBorrows= bbs.getBorrowRecordsByUser(userid);
		else 
			bookBorrows= bbs.getBorrowedBooksByUser(userid);

		Optional<User> user;
		
		String username;
		
		user = userService.getUserById(userid);
		
		if(user.isPresent()){
			username = user.get().getName();
		}else{
			 username = "0000".equals(userid)?"":"未查到";
		}
		
		List<BookBorrowVO> bookBorrowsVO =new ArrayList<>(bookBorrows.size());
		
		for (BookBorrow bookBorrow : bookBorrows) {
			
			BookBorrowVO bookBorrowVO=new BookBorrowVO();
			
			bookBorrowVO.setAID(bookBorrow.getID());
			bookBorrowVO.setReaderID(bookBorrow.getUser().getId());//借阅者编号
			bookBorrowVO.setReaderName(bookBorrow.getUser().getName());//借阅者姓名
			bookBorrowVO.setBookID(bookBorrow.getBook().getID());//所借图书编号
			bookBorrowVO.setTitle(bookBorrow.getBook().getTitle());//所借图书的书名
			bookBorrowVO.setISBN(bookBorrow.getBook().getBookList().getIsbn());//所借图书的ISBN
			bookBorrowVO.setBorrowTime(bookBorrow.getBorrowTime());//借出时间
			bookBorrowVO.setOverTime(bookBorrow.getOvertime());//预计超期时间
			bookBorrowVO.setReturnedTime(bookBorrow.getReturnedTime());//实际归还时间
			bookBorrowVO.setIfReturned(bookBorrow.isIfReturned());//是否归还了
			bookBorrowVO.setRenewTime(bookBorrow.getRenewTime());//此为第几次续借？
			bookBorrowVO.setBorrowState(bookBorrow.getBorrowState().toString());//借阅状态
			
			bookBorrowsVO.add(bookBorrowVO);			
		}
		//"0000"是特殊的读者编号，系统内部保留，不提供给用户。
		model.addAttribute("bookBorrowsVO",bookBorrowsVO);
		model.addAttribute("userid", userid);
		model.addAttribute("username",username);
		model.addAttribute("fetchall", fetchAll);
		
		return "bookborrow/list";
	}
		/**
		 * 9、发起罚款的输入界面
		 * @return
		 */
		@GetMapping("/bookfine")
		public String bookFineInput(){
			return "bookborrow/fineinput";
		}
		
		/**
		 * 10、保存罚单
		 * @param model
		 * @param fine
		 * @param borrowovertimeID
		 * @param m_item
		 * @return
		 */
		@PostMapping("/bookfine")
		public String bookFineSave(Model model, Fine fine , Integer[] borrowovertimeID,float[] m_item){
			
			
			Set<FineItem> fineItems=new HashSet<>();
			//计算罚款合计
			float sum = 0.0f;
			for (float f : m_item) {
				sum=sum + f ;
			}
			
			for (Integer n : borrowovertimeID) {
				FineItem fineItem= new FineItem();
				fineItem.setBorrowovertimeID(n);
				fineItem.setFine(fine);  //这一句很重要。从表记下主表的指针。
				
				fineItem.setType(FineType.图书超期);
				
				fineItems.add(fineItem);
			} 
			fine.setItems(fineItems);
			//罚款合计
			fine.setAmount(sum);
			fine.setFinetype(FineType.图书超期);
			
			model.addAttribute("fine", fineservice.append(fine));
			
			return "redirect:/bookfine/deal";
		}
		
		//11、发起罚款处理缴纳的界面
		@GetMapping("/bookfine/deal")
		public String fineDeal(){
			return "bookborrow/finedeal";
		}
		
		@PostMapping("/bookfine/deal/{fineid}")
		@ResponseBody
		public String finedealPost(@PathVariable int fineid){
			
			Fine fine=fineReository.getOne(fineid);
			fineservice.dealFine(fine);
			return "逾期的图书已做还书处理，本罚单处理完成。罚款流水号："+fineid;
		}
		
		/**
		 * 12、得到某个读者的所有未处理罚单
		 */
		@GetMapping("/bookfine/readerid/{id}")
		@ResponseBody
		List<FineVO> getfineByReaderid(@PathVariable String id){
			return fineservice.getFineByUserid(id);
		}
		
		/**
		 * 13、得到某读者的借还书记录：
		 * 1、全部未还书
		 * 2、全部借阅记录
		 * @param userid
		 * @param model
		 * @return
		 */	
		@GetMapping("/bookborrow/bymyself")
		@Transactional
		public String bookBorrowedByMyself(@RequestParam(value="fetchall",required=false,defaultValue="false") Boolean fetchAll, Model model){
			
			List<BookBorrow> bookBorrows;
			//1、要刷新借书记录：是超期的标记为超期；
			bbs.refreshOvertime();
			
			//2、得到当前登录用户的userid;
			String userid;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username;
			if(principal instanceof UserDetails ){
				username= ((UserDetails) principal).getUsername();
				Collection<? extends GrantedAuthority> authorities = ((UserDetails) principal).getAuthorities();
			}else username = principal.toString();
			
			userid = userService.getidByName(username);
			
			
			//System.out.println("userid="+userid);
			
			//if(fetchAll !=null && "true".equals(fetchAll.toLowerCase()))  //为真则得到所有借书记录
			if(fetchAll)
			    bookBorrows= bbs.getBorrowRecordsByUser(userid);
			else 
				bookBorrows= bbs.getBorrowedBooksByUser(userid);

			
			List<BookBorrowVO> bookBorrowsVO =new ArrayList<>(bookBorrows.size());
			
			for (BookBorrow bookBorrow : bookBorrows) {
				
				BookBorrowVO bookBorrowVO=new BookBorrowVO();
				
				bookBorrowVO.setAID(bookBorrow.getID());
				bookBorrowVO.setReaderID(bookBorrow.getUser().getId());//借阅者编号
				bookBorrowVO.setReaderName(bookBorrow.getUser().getName());//借阅者姓名
				bookBorrowVO.setBookID(bookBorrow.getBook().getID());//所借图书编号
				bookBorrowVO.setTitle(bookBorrow.getBook().getTitle());//所借图书的书名
				bookBorrowVO.setISBN(bookBorrow.getBook().getBookList().getIsbn());//所借图书的ISBN
				bookBorrowVO.setBorrowTime(bookBorrow.getBorrowTime());//借出时间
				bookBorrowVO.setOverTime(bookBorrow.getOvertime());//预计超期时间
				bookBorrowVO.setReturnedTime(bookBorrow.getReturnedTime());//实际归还时间
				bookBorrowVO.setIfReturned(bookBorrow.isIfReturned());//是否归还了
				bookBorrowVO.setRenewTime(bookBorrow.getRenewTime());//此为第几次续借？
				bookBorrowVO.setBorrowState(bookBorrow.getBorrowState().toString());//借阅状态
				
				bookBorrowsVO.add(bookBorrowVO);			
			}
			//"0000"是特殊的读者编号，系统内部保留，不提供给用户。
			model.addAttribute("bookBorrowsVO",bookBorrowsVO);
			model.addAttribute("userid", userid);
			model.addAttribute("username",username);
			model.addAttribute("fetchall", fetchAll);
			
			return "bookborrow/list2";
		}
}
