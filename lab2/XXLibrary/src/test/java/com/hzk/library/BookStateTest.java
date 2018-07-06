/**
 * 
 */
package com.hzk.library;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hzk.library.Dao.BookStateRepository;

/**
 * @author kecheng
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookStateTest {

	@Autowired
	private BookStateRepository bsr;
		
	@Test
	public void getAlltest() {
		System.out.println(bsr.findAll());
	}

}
