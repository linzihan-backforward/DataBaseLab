/**
 * 
 */
package com.hzk.library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hzk.library.Common.ResultWithMsg;
import com.hzk.library.Dao.BookBorrowRepository;
import com.hzk.library.Entity.BookBorrow;
import com.hzk.library.Entity.User;
import com.hzk.library.Service.BookBorrowService;
import com.hzk.library.VO.BookBorrowVO;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BookBorrowTest {
   
	@Autowired
	private BookBorrowService bookborrow;
	
	@Autowired
	private BookBorrowRepository br;
	

	@Test
	public void returnbooksTest(){
		
		List<Integer> bookIdList = Arrays.asList(1008,1009);
		
		boolean cnt=bookborrow.WriteReturnBook(bookIdList);
		System.out.println("成功还书列表："+bookIdList.toString());
	}
	
	//判断某本书是没有还的书吗，如果是，得到借阅记录？
	@Test
	public void getNotReturnedTest(){
		List<BookBorrow> bookBorrow=br.getNotReturned(1017);
		if(bookBorrow.size()==0){
			System.out.println("未找到！");
		}else{	
			System.out.println("未还的书名="+bookBorrow.get(0).getBook().getTitle()); 
			System.out.println("借书人="+bookBorrow.get(0).getUser().getName());
		}
	}
	//某本书可以被借吗
	@Test
	public void canbookBorrowed() {
		System.out.println(bookborrow.getBook(1011));
	}
	//某读者借了几本书
	@Test
	public void  getBorrowedBooks(){
		//造一个用户对象
		String userid="A0101";
		User user= new User();
		user.setId(userid);
		
		List<BookBorrow> bookBorrows= bookborrow.getBorrowedBooksByUser(userid);   
 
		bookBorrows.stream().forEach(System.out::println);

	    System.out.println("共借阅了"+bookborrow.GetBorrowedBookCntForReader(user)+"本");	
	}
	
	//借一本书：
	@Test
	@Transactional
	public void WriteBookBorrowsTest(){
		String userid="A0101";
		List<Integer> books= new ArrayList<>();

		books.add(1002);
		books.add(1011);

        ResultWithMsg resultWithMsg=bookborrow.canBorrowed(books);
		
		if(resultWithMsg.isResult()){//都可以借阅
			bookborrow.WriteBorrowBook(userid,books);
		}
		System.out.println(resultWithMsg);
	}
	
	@Test
	public void WritebookBorrowXJTest(){
		//续借某本书
		//条件：1、未还；2、未超最大续借次数（2次）；
		
		int cnt=bookborrow.WriteBookBorrowAgain(1017);
		String result=cnt==0?"失败！":"成功！";
		System.out.println("续借"+result);
	}
}
