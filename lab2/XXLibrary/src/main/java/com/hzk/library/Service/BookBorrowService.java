/**
 * 
 */
package com.hzk.library.Service;

import java.util.List;

import com.hzk.library.Common.ResultWithMsg;
import com.hzk.library.Entity.BookBorrow;
import com.hzk.library.Entity.User;
import com.hzk.library.VO.BookBorrowVO;

/**
 * @author linzihan
 *
 */
public interface BookBorrowService {
	
	/**
	 * 刷新图书借阅记录，根据最晚还书时间来判断是否超期，并写入超期状态。
	 */
	int refreshOvertime();
	
	/**
	 * 续借某本书
	 * @param bookid,本书的编号
	 * @返回值：修改涉及的记录数。
	 */
	int WriteBookBorrowAgain(Integer bookid);
	
	/**
	 * 
	 * @param id，读者编号
	 * @param bookIdList，拟借图书编号的列表
	 * @return
	 */
	//读者借书的数量未超标、无超期、无罚款未交，则为某读者登记指定的几本书的借书信息,第二个参数是几本书的编号集合
	//给定一个拟借书记录的列表(List)，包含所借几本书的书号、借书人编号。
	 //假设这几本书都不需要续借。
	boolean WriteBorrowBook(String id,List<Integer> bookIdList); 
	
	//还书
	//入参：拟还书记录的列表(List)，内含所借几本书的书号。
	boolean WriteReturnBook(List<Integer> bookIdList);
	
	//如果该书处于已经被借出，且未归还的状态，则返回借阅记录，否则范围一个空值。
	BookBorrowVO getByBook(int bookid);
	
	//某读者当前借了几本书？
	int GetBorrowedBookCntForReader(User user);
	
	//未还的记录有哪些？
	List<BookBorrow> getBorrowedBooksByUser(String userid);

	//全部借书记录。按借书时间逆序排列，最新借的书排在前面。
	List<BookBorrow> getBorrowRecordsByUser(String userid);

	//逐本书检查这些书是否可以出借，入参是书编号的集合，返回值是boolean
	ResultWithMsg canBorrowed(List<Integer> books);
	
	//找到、锁住一本可以出借的书，成功，则在返回结果的message中保存书名、编号。否则返回错误提示
	ResultWithMsg getBook(Integer bookid);
	
	
}
