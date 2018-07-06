/**
 * 
 */
package com.hzk.library;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hzk.library.Service.BooklistService;
import com.hzk.library.VO.BookListVO;

/**
 * @author kecheng
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class BooklistTest {

	@Autowired
	private BooklistService booklistservice;
	/**
	 * Test method for {@link com.hzk.library.ServiceImpl.BooklistServiceImpl#getBooklistbyID(java.lang.String)}.
	 */
	@Test
	@Transactional
	public void testGetBooklistbyID() {
		String isbn="7-101-02858-6";
		BookListVO blist=booklistservice.getBooklistbyID(isbn);
		
		System.out.println(blist);
	}

}
