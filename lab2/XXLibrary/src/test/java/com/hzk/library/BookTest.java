/**
 * 
 */
package com.hzk.library;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hzk.library.Dao.BookCategoryRepository;
import com.hzk.library.Dao.BookRepository;
import com.hzk.library.Dao.BookStockRepository;
import com.hzk.library.Dao.BooklistRepository;
import com.hzk.library.Entity.BookCategory;
import com.hzk.library.Service.BookService;
import com.hzk.library.ServiceImpl.BookServiceImpl;
import com.hzk.library.VO.BookVO;

/**
 * @author kecheng
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookTest {
    
	@Autowired
	BookCategoryRepository bookcate;
	
	@Autowired
	private BookService bookservice;
	
	@Autowired
	private BookRepository br;
	
	//改变本书的状态
//	@Test
//	@Transactional
//	public void UpdateStatementTest(){
//		br.updatebooksState(2, 1011);
//	}
//	
	
	@Test
	public void getbookbyID() {
		Optional<BookVO> book=bookservice.getBookbyID(1001);
		if(book.isPresent()) System.out.println(book.get());
	}

	
	@Test
	public void getbookbycategory() {
		BookCategory bookCategory=new BookCategory();
		bookCategory.setTitle("学习辅导书");
		bookCategory.setID(3);

		System.out.println(bookservice.GetBooksByCategery(bookCategory,new PageRequest(0, 10)).getContent());
	}
	
	
	@Test
	public void listCategory(){
		System.out.println(bookcate.findAll());
	}
}
