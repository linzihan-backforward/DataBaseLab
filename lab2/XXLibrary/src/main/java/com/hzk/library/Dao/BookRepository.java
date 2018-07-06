/**
 * 
 */
package com.hzk.library.Dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hzk.library.Entity.Book;
import com.hzk.library.Entity.BookCategory;
import com.hzk.library.Entity.BookState;

/**
 * @author linzihan
 *
 */
public interface BookRepository extends JpaRepository<Book, Integer> {

	//修改特定书的状态：
	@Modifying
	@Query(value="UPDATE  book SET state_ID =:s WHERE ID =:b",nativeQuery=true)
	int updatebooksState( @Param("s") Integer state, @Param("b") Integer bookid);
	
	//根据出版社查找
	List<Book> getByBookList_press(String press);
	
	//根据作者查找
	List<Book> getByBookList_author(String author);
	
	//根据图书状态查找
	List<Book> getByState(BookState bookState);
	
	//根据图书类别查找
	Page<Book> getByBookList_category(BookCategory bookCategory,Pageable pageable);
}
