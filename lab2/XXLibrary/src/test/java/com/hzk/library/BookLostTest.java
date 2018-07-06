/**
 * 
 */
package com.hzk.library;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hzk.library.Dao.BookLostRepository;
import com.hzk.library.Entity.BookLost;
import com.hzk.library.Entity.User;


/**
 * @author kecheng
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookLostTest {

	@Autowired
	private BookLostRepository  BLR;
	
	
	@Test
	@Transactional
	public void getBookslostByUser() {
		User user=new User();
		user.setId("A0101");
		
		List<BookLost> bl =BLR.getByUser(user);
		
		for (BookLost bookLost : bl) {
			System.out.println(bookLost);
		}
	}

}
