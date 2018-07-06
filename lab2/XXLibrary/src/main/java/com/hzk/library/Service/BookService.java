/**
 * 
 */
package com.hzk.library.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hzk.library.Entity.Book;
import com.hzk.library.Entity.BookCategory;
import com.hzk.library.VO.BookVO;

/**
 * @author linzihan
 *
 */
public interface BookService {
	
	//根据指定的图书编号得到图书信息
	Optional<BookVO> getBookbyID(int id);
	
	//根据图书分类列出库存图书
	Page<BookVO> GetBooksByCategery(BookCategory category,Pageable pageable);
	
	//根据作者列出库存图书
	List<BookVO> GetBooksByAuthor(String author);
	
	//根据出版社列出库存图书
	List<BookVO> GetBooksByPress(String press);
	
	//列出所有的库存图书
	Page<BookVO> getAllBooks(Pageable pageable);
	
	//根据指定的参数，保存（Create,persist用汉语已经表达不准确了）图书记录
	List<Book> saveAll(List<Book> books);
	
	//增加一条记录
	Book addOne(Book book);
}
