/**
 * 
 */
package com.hzk.library;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hzk.library.Dao.BookBorrowRepository;
import com.hzk.library.Entity.User;
import com.hzk.library.Service.UserService;

/**
 * @author kecheng
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	@Autowired
	private UserService userservice;
	
	@Autowired
	public BookBorrowRepository bookBorrowRepository;
	
	@Test
	public void OverTimetest() {
		System.out.println("B0101有超期图书吗？"+userservice.IsReaderOverTime("B0101"));
	}

	@Test
	public void ReaderfinedTest(){
		System.out.println("A0101有罚款未交吗？"+userservice.IsReaderfined("A0101"));
	}
	
	@Test
	public void getusertest() {
		Optional<User> user=userservice.getUserById("A0101");
		if(user.isPresent()){
		System.out.println(user.get());
		System.out.println("共借阅了"+bookBorrowRepository.countBorrowedByUser(user.get())+"本");			
		}
	
	}
  
	@Test
	public void getAllUser(){
		userservice.getAllUsers().stream().forEach(System.out::println);
	}
}
