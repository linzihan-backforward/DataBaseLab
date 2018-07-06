/**
 * 
 */
package com.hzk.library.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzk.library.Dao.BookCategoryRepository;
import com.hzk.library.Dao.BookStateRepository;
import com.hzk.library.Entity.Book;
import com.hzk.library.Entity.BookCategory;
import com.hzk.library.Entity.BookState;
import com.hzk.library.Service.BookService;
import com.hzk.library.Service.BooklistService;
import com.hzk.library.VO.BookListVO;
import com.hzk.library.VO.BookVO;

/**
 * @author linzihan
 * 在类上加上映射：/books
 *
 */
@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookCategoryRepository bookcate;
	
	@Autowired
	private BookStateRepository bookStateRepository;
	
	@Autowired
	private BooklistService booklistservice;
	
	@Autowired
	BookService bookservice; 
	
	//得到所有的图书
	@GetMapping
	public String getbooks(Model model,@PageableDefault(page=0,size=10,sort={"bookList.isbn","ID"}) Pageable pageable){
		//得到图书类别：
		List<BookCategory> bc = (List<BookCategory>) bookcate.findAll();

		model.addAttribute("bookcategory",bc);
		
		model.addAttribute("bycategory","所有类别" ); 
		
		Page<BookVO>  bvo=bookservice.getAllBooks(pageable);
		
		model.addAttribute("booksVO",bvo.getContent()); //内容
		
		int pageno=bvo.hasContent()?1+bvo.getNumber():0; //页号,从1开始,如果没有内容,则=0

		model.addAttribute("pageno", pageno); 
		
		model.addAttribute("pagecnt", bvo.getTotalPages()); //总页数
		
//		System.out.println("pageno="+ (1 + bvo.getNumber()) );
//		System.out.println("pagecnt="+ bvo.getTotalPages());
		return "books/list";
	}
	
	//根据某个图书类型查找图书信息
	@GetMapping("/category/{id}")
	public String getbooksByCategory(Model model,@PathVariable("id") Integer id,@PageableDefault(page=0,size=10,sort={"bookList.isbn","ID"}) Pageable pageable){
		
		//得到图书类别：
		List<BookCategory> bc =(List<BookCategory>) bookcate.findAll();

		model.addAttribute("bookcategory",bc);
		
		Optional<BookCategory> category= bookcate.findById(id);
		
		if(category.isPresent()){
			model.addAttribute("bycategory",category.get().getTitle() ); 
		}
		Page<BookVO> bvo = bookservice.GetBooksByCategery(new BookCategory(id,""),pageable);
		
		model.addAttribute("booksVO",bvo ); 
		
		model.addAttribute("booksVO",bvo.getContent()); //内容
		
		int pageno=bvo.hasContent()?1+bvo.getNumber():0; //页号,从1开始,如果没有内容,则=0

		model.addAttribute("pageno", pageno); 
		
		model.addAttribute("pagecnt", bvo.getTotalPages()); //总页数
		
		return "books/list";
	}
	
	/**
	 * 发起图书入库的界面
	 * @return
	 */
	@GetMapping("/putin")
	public String putinBooks(Model model){
		//用户输入ISBN,系统从书库编目（书库编目是标准图书出版信息库，有专门的渠道更新。遇到特殊图书也可以手工录入编目信息）中查询出该书的书名等基本信息。
		//继续完善
		List<BookState> bookStates = new ArrayList<>();
		Iterable<BookState> bIterable = bookStateRepository.findAll();
		for (BookState bookState : bIterable) {
			bookStates.add(bookState);
		}

		model.addAttribute("bookstates", bookStates);
		return "books/putin";
	}
	
	/**
	 * 保存管理员输入的几本待入库的书的信息。
	 */
	@PostMapping("/putin")
	public String writePutinBooks(@Valid Book book, BindingResult errors ){
		if(errors.hasErrors()){
			System.out.println(errors.toString());
			return "redirect:/books/putin";
		}else {
			 bookservice.addOne(book);
			 return "redirect:/books";
		}
		 
	}
	
	@GetMapping("/booklist/{isbn}")
	@ResponseBody
	public BookListVO getBooklistByIsbn(@PathVariable String isbn){
		BookListVO bl = booklistservice.getBooklistbyID(isbn);
	    return bl;
	}
}

