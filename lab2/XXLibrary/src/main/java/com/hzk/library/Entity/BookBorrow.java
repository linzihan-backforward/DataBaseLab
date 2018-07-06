/**
 * 
 */
package com.hzk.library.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author linzihan
 *
 */
@Entity
@Table(name="bookborrow")
public class BookBorrow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;

	@ManyToOne
	@JoinColumn(name="userID")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="bookID")
	private  Book book;
	
	@Column(name="borrowtime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date borrowTime;
	
	@Column(name="overtime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date overtime;
	
	@Column(name="returnedtime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date returnedTime;
	
	//是否归还？
	@Column(name="ifreturned")
	private boolean ifReturned;
	
	//此为第几次续借？
	@Column(name="renewtime")
	private int renewTime;
	
	@Column(name="borrowstate")
	private BookBorrowState borrowState;

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * @return the borrowTime
	 */
	public Date getBorrowTime() {
		return borrowTime;
	}

	/**
	 * @param borrowTime the borrowTime to set
	 */
	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}

	/**
	 * @return the overtime
	 */
	public Date getOvertime() {
		return overtime;
	}

	/**
	 * @param overtime the overtime to set
	 */
	public void setOvertime(Date overtime) {
		this.overtime = overtime;
	}

	/**
	 * @return the returnedTime
	 */
	public Date getReturnedTime() {
		return returnedTime;
	}

	/**
	 * @param returnedTime the returnedTime to set
	 */
	public void setReturnedTime(Date returnedTime) {
		this.returnedTime = returnedTime;
	}

	/**
	 * @return the ifReturned
	 */
	public boolean isIfReturned() {
		return ifReturned;
	}

	/**
	 * @param ifReturned the ifReturned to set
	 */
	public void setIfReturned(boolean ifReturned) {
		this.ifReturned = ifReturned;
	}

	/**
	 * @return the renewTime
	 */
	public int getRenewTime() {
		return renewTime;
	}

	/**
	 * @param renewTime the renewTime to set
	 */
	public void setRenewTime(int renewTime) {
		this.renewTime = renewTime;
	}

	/**
	 * @return the borrowState
	 */
	public BookBorrowState getBorrowState() {
		return borrowState;
	}

	/**
	 * @param borrowState the borrowState to set
	 */
	public void setBorrowState(BookBorrowState borrowState) {
		this.borrowState = borrowState;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BookBorrow [借书顺序号=" + ID + ", 用户名:" + user.getName() + ", 书名：" + book.getTitle() + ", borrowTime=" + borrowTime
				+ ", overtime=" + overtime + ", returnedTime=" + returnedTime + ", 是否归还：" + ifReturned
				+ ", renewTime=" + renewTime + ", borrowState=" + borrowState + "]";
	}
	
	
	
}
