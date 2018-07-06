/**
 * 
 */
package com.hzk.library.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hzk.library.Entity.BookBorrow;
import com.hzk.library.Entity.User;


/**
 * @author kecheng
 *
 */
public interface BookBorrowRepository extends JpaRepository<BookBorrow, Integer>{
	
	/**
	 * 刷新图书借阅记录：检查并刷新 图书借阅逾期状态。
	 */
	@Modifying
	@Query(value = "update bookborrow set borrowstate=1 where overtime <now() and ifreturned<>1 and borrowstate=0",nativeQuery=true)
	int refreshOvertime();
	
	/**得到某读者目前未还的借书记录
	 * 用了JPQL
	 * @param user
	 * @return
	 */
	 @Query("SELECT b FROM BookBorrow b WHERE b.borrowState!=3  AND b.user=:user ORDER BY b.borrowTime")
	 List<BookBorrow> getBorrowedBooksByUser(@Param("user") User user);
	 
	//
	 /**
	  * 得到某读者所有的借书记录
	  * @param user
	  * @return
	  */
	 @Query("SELECT b FROM BookBorrow b WHERE b.user=:user ORDER BY borrowTime")
	 List<BookBorrow> getAllBorrowedBooksByUser(@Param("user") User user);	 

	 //得到某读者所借图书的数量
	//用了JPQL
	 @Query("SELECT COUNT(*) FROM BookBorrow b WHERE b.borrowState!=3  AND b.user=?1")
	 int countBorrowedByUser(User user);
	 
	 //根据图书编号得到该书的未还的借阅记录，根据借书逻辑，应该最多只有一条记录。0=未逾期，1=逾期，2=读者丢失，3=已归还
	 //用了SQL
	 @Query(value="SELECT *  FROM bookborrow b WHERE (b.ifreturned=0 or b.ifreturned=2) AND b.bookID=?1 ",nativeQuery=true)
	 List<BookBorrow> getNotReturned(int bookid);
	 
	 @Modifying
	 @Query(value="update bookborrow set renewtime=renewtime+1,overtime=timestampadd(day, 30,overtime) where id=?1",nativeQuery=true)
	 int WriteBookReBorrow(int id);
	 
	 //根据借书流水号得到所借图书的编号
	 @Query(value="select bookid from bookborrow where id=?1",nativeQuery=true)
	 int getbookidByID(int id);
}
