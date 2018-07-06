/**
 * 
 */
package com.hzk.library.ServiceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.naming.spi.DirStateFactory.Result;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzk.library.Common.ResultWithMsg;
import com.hzk.library.Dao.BookBorrowRepository;
import com.hzk.library.Dao.BookRepository;
import com.hzk.library.Entity.Book;
import com.hzk.library.Entity.BookBorrow;
import com.hzk.library.Entity.BookBorrowState;
import com.hzk.library.Entity.BookState;
import com.hzk.library.Entity.User;
import com.hzk.library.Exception.BookFineException;
import com.hzk.library.Exception.BorrowDisabledException;
import com.hzk.library.Exception.BorrowOverAmountException;
import com.hzk.library.Exception.BorrowOverTimeException;
import com.hzk.library.Exception.NonBookException;
import com.hzk.library.Service.BookBorrowService;
import com.hzk.library.Service.UserService;
import com.hzk.library.VO.BookBorrowVO;
import com.hzk.library.VO.BookVO;

/**
 * @author linzihan
 *
 */

@Service
public class BookBorrowServiceImpl implements BookBorrowService {
    
	@Autowired
	public UserService userservice;
	
	@Autowired
	private BookBorrowRepository bookBorrowRepository;

	@Autowired
	private BookRepository bookRepository;
	
	/* (non-Javadoc)
	 * 某读者登记指定的几本书的借书信息,第二个参数是几本书的集合
	 */
	@Override
	@Transactional
	public boolean WriteBorrowBook(String id,List<Integer> bookIdList) throws RuntimeException {

		//读者借书的数量未超标、无超期、无罚款未交
		//都不是本人已经在借，需要续借的。

		//传过来空的图书列表,则抛出异常
		if(bookIdList==null){
			throw new NonBookException();
		}
		//取得该用户最大可借图书的数量
        int maxBookCnt = userservice.getUserById(id).get().getCheckoutAmount();

        //取得目前该读者已经借出的书的数量
        int curBookCnt = GetBorrowedBookCntForReader(new User(id));
        
        //待借书的数量
        int toBookCnt=bookIdList.size();
        
        //根据严重程度，依次判断：
        //1、如果该用户被人为地暂时禁止借书
        if (userservice.isDisabled(id)) {
			throw new BorrowDisabledException();
		}
        
        //2、如果有罚款未交，则抛出罚款未交的异常
        if(userservice.IsReaderfined(id)){
        	throw new BookFineException();
        }
        
        //3、如果超期了，则抛出超期异常
        if(userservice.IsReaderOverTime(id)){
        	throw new BorrowOverTimeException();
        }
        
        //4、判断是否超标
        //如果超标了，则抛出超出数量异常：
        if(toBookCnt+curBookCnt>maxBookCnt){
        	throw new BorrowOverAmountException();
        }
          
        //还要逐本书检查：全是新借阅的，没有需要续借的。
            //待完善
        
        
        //该读者的借阅周期是：计量单位（天）
        int BorrowPeriod = userservice.getBorrowPeriodOfUser(id);
        
        //产生借阅记录
        List<BookBorrow> bookBorrows =new ArrayList<>();
       
        BookBorrow bookBorrow=null;
        
        //BookState bState = new BookState(2,"已借出");
        
        //逐本书
        for (Integer bookid : bookIdList) {
			bookBorrow = new BookBorrow();
			bookBorrow.setUser(new User(id));   //1、借书人
			Book book = new Book();
			book.setID(bookid);
			bookBorrow.setBook(book);          //2、哪本书		
			bookBorrow.setBorrowTime(new Date());//3、什么时候借的
			
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, BorrowPeriod);
			bookBorrow.setOvertime(cal.getTime());  //4、最迟什么时候还
			
			bookBorrow.setIfReturned(false);   //5、还书了吗？：没有
			bookBorrow.setBorrowState(BookBorrowState.未逾期);  //6、状态正常
			bookBorrow.setRenewTime(0);        //7、默认续借次数=0
			
			bookBorrows.add(bookBorrow);
	        
			//逐本书修改它的状态为=2“已经借出”
			bookRepository.updatebooksState(2, bookid);
		}
        bookBorrowRepository.saveAll(bookBorrows);
        

        return true;
	}

	/* 某读者借了几本书？
	 * @see com.hzk.library.Service.BookBorrowService#GetBorrowedBookCntForReader(com.hzk.library.Entity.User)
	 */
	@Override
	public int GetBorrowedBookCntForReader(User user) {
		
		return bookBorrowRepository.countBorrowedByUser(user);
	}

	/* 列出该读者的借书记录（只包含未还书的记录）。
	 * @see com.hzk.library.Service.BookBorrowService#getBorrowedBooksByUser(java.lang.String)
	 */
	@Override
	public List<BookBorrow> getBorrowedBooksByUser(String userid) {
		//造一个用户对象
		User user= new User();
		user.setId(userid);
		
		return bookBorrowRepository.getBorrowedBooksByUser(user);
	}

	//
	/* 全部借书记录。按借书时间逆序排列，最新借的书排在前面。
	 * @see com.hzk.library.Service.BookBorrowService#getBorrowRecordsByUser(java.lang.String)
	 */
	@Override
	public List<BookBorrow> getBorrowRecordsByUser(String userid) {
		//造一个用户对象
		User user= new User();
		user.setId(userid);
		return bookBorrowRepository.getAllBorrowedBooksByUser(user);
	}
	
	

	/* (non-Javadoc)
	 * @see com.hzk.library.Service.BookBorrowService#canBorrowed(java.util.List)
	 */
	@Override
	public ResultWithMsg canBorrowed(List<Integer> books) {
		StringBuilder mStringBuilder= new StringBuilder();
		boolean result = true;
		for (Integer bookid : books) {
			//检查书的“状态”字段：!=1则不能借阅。
			Book book=bookRepository.getOne(bookid);
			if (book.getState().getID() != 1){
				//表示：非可借阅
				mStringBuilder.append("《"+book.getTitle()+"》"+book.getState().getTitle());
				result = false;
			}
		}
		
		return new ResultWithMsg(result, mStringBuilder.toString());
	}

	/* (non-Javadoc)
	 * 找到、锁住一本可以出借的书，成功，则在返回结果的message中保存书名,bookid中保存编号。否则返回错误提示
	 * @see com.hzk.library.Service.BookBorrowService#getBook(java.lang.Integer)
	 */
	@Override
	@Transactional
	public ResultWithMsg getBook(Integer bookid) {
		//需要一个锁定待借图书的机制，比如一个临时表，设个锁定本书的有效时限。
		boolean tempLocked = false;
		ResultWithMsg rs;
		
		Optional<Book> book=bookRepository.findById(bookid);
		if(book.isPresent()){
				if (book.get().getState().getID() == 1 && !tempLocked){
					rs = new ResultWithMsg(true,book.get().getTitle());
				} else{
					rs = new ResultWithMsg(false,"借书目录中已经加入本书或者本书不能被借阅！");		
				}
		}else{
			rs = new ResultWithMsg(false,"不存在编号为："+bookid+"的图书！");
		}
		rs.setBookid(bookid);
		return rs;
	}

	/* (non-Javadoc)
	 * 还书
	 * @see com.hzk.library.Service.BookBorrowService#WriteReturnBook(java.util.List)
	 */
	@Override
	@Transactional
	public boolean WriteReturnBook(List<Integer> bookIdList) {
		//找到几本书对应的借书记录：如果有任何一本书没有找到适当的借阅未还记录，则抛出异常
		
		StringBuilder sb=new StringBuilder();
		List<BookBorrow> bookBorrow=null;
		List<BookBorrow> returnedbooks= new ArrayList<>();
		
		for (Integer bookid : bookIdList) {
			bookBorrow = bookBorrowRepository.getNotReturned(bookid);
			if(bookBorrow.isEmpty()){
				sb.append("编号：["+bookid+"]的书没有相应的借书未还记录。");
			}else{
				bookBorrow.get(0).setReturnedTime(new Date());
				bookBorrow.get(0).setIfReturned(true);
				bookBorrow.get(0).setBorrowState(BookBorrowState.已归还);
				
				//修改对应书的基本信息
				bookBorrow.get(0).getBook().setState(new BookState(1,"可借阅"));

				returnedbooks.add(bookBorrow.get(0));
			}
		}
		
		//上面是否还要做借书记录异常的提示：当同一本书的借书未还记录数>1时，违反了业务逻辑。
		
		if("".equals(sb.toString())){ //如果都找到了
			bookBorrowRepository.saveAll(returnedbooks);
		}else{  //有异常
			throw new RuntimeException(sb.toString());
		}
		
		//修改相应借书者的状态：
		

		return true;
	}

	/* (non-Javadoc)
	 * 如果该书处于已经被借出，且未归还的状态，则返回借阅记录，否则范围一个空值。
	 * 入参：图书编号
	 * @see com.hzk.library.Service.BookBorrowService#getByBook(int)
	 */
	@Override
	public BookBorrowVO getByBook(int bookid) {
		List<BookBorrow> bookBorrow=bookBorrowRepository.getNotReturned(bookid);
		if(bookBorrow.size()==0)
			return null;
		else{
			
			BookBorrowVO bookBorrowVO=new BookBorrowVO();
			
			bookBorrowVO.setAID(bookBorrow.get(0).getID());   //借阅流水号
			bookBorrowVO.setReaderID(bookBorrow.get(0).getUser().getId());//借阅者编号
			bookBorrowVO.setReaderName(bookBorrow.get(0).getUser().getName());//借阅者姓名
			bookBorrowVO.setBookID(bookBorrow.get(0).getBook().getID());//所借图书编号
			bookBorrowVO.setTitle(bookBorrow.get(0).getBook().getTitle());//所借图书的书名
			bookBorrowVO.setISBN(bookBorrow.get(0).getBook().getBookList().getIsbn());//所借图书的ISBN
			bookBorrowVO.setBorrowTime(bookBorrow.get(0).getBorrowTime());//借出时间
			bookBorrowVO.setOverTime(bookBorrow.get(0).getOvertime());//预计超期时间
			bookBorrowVO.setReturnedTime(bookBorrow.get(0).getReturnedTime());//实际归还时间
			bookBorrowVO.setIfReturned(bookBorrow.get(0).isIfReturned());//是否归还了
			bookBorrowVO.setRenewTime(bookBorrow.get(0).getRenewTime());//此为第几次续借？
			bookBorrowVO.setBorrowState(bookBorrow.get(0).getBorrowState().name());  //借阅状态
			
			return bookBorrowVO;	
		}
			
	}

	/* (non-Javadoc)
	 * 续借某本书
	 * @see com.hzk.library.Service.BookBorrowService#WriteBookBorrowAgain(java.lang.Integer)
	 */
	@Override
	@Transactional
	public int WriteBookBorrowAgain(Integer bookid) {
		int Result = 0;
		BookBorrowVO bbVo = getByBook(bookid);
		//此书已借、未还，且续借次数<2
		if( bbVo!=null && (bbVo.getRenewTime()<2)){
		   	Result = bookBorrowRepository.WriteBookReBorrow(bbVo.getAID());	
		}
		return Result;
	}

	/* (non-Javadoc)
	 * @see com.hzk.library.Service.BookBorrowService#refreshOvertime()
	 */
	@Override
	@Transactional
	public int refreshOvertime() {
		// TODO Auto-generated method stub
		return bookBorrowRepository.refreshOvertime();
	}
}
