/**
 * 
 */
package com.hzk.library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hzk.library.Dao.BookStockRepository;

/**
 * @author kecheng
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookStockTest {

	@Autowired
	private BookStockRepository bookStockRepository;
	@Test
	public void getbookStock() {
		System.out.println(bookStockRepository.findById("7-111-07886-1"));
	}

}
