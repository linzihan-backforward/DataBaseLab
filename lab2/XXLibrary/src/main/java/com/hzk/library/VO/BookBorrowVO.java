/**
 * 
 */
package com.hzk.library.VO;

import java.util.Date;

/**
 * @author linzihan
 *
 */
public class BookBorrowVO {

	//顺序号
	private int AID;

	//借阅者编号
	private String readerID;
	//借阅者姓名
	private String readerName;
	
	//所借图书编号
	private int bookID;
	
	//所借图书的书名
	private String title;
	
	//所借图书的ISBN
	private String ISBN;
	
	//借出时间
	private Date borrowTime;
	
	//预计超期时间
	private Date overTime;
	
	//实际归还时间
	private Date returnedTime;
	
	//是否归还了
	private boolean ifReturned;
	
	//此为第几次续借？
	private int renewTime;
	
	//借阅状态
	private String borrowState;

	public int getAID() {
		return AID;
	}

	public void setAID(int iD) {
		AID = iD;
	}

	public String getReaderID() {
		return readerID;
	}

	public void setReaderID(String readerID) {
		this.readerID = readerID;
	}

	public String getReaderName() {
		return readerName;
	}

	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public Date getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}

	public Date getOverTime() {
		return overTime;
	}

	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}

	public Date getReturnedTime() {
		return returnedTime;
	}

	public void setReturnedTime(Date returnedTime) {
		this.returnedTime = returnedTime;
	}

	public boolean isIfReturned() {
		return ifReturned;
	}

	public void setIfReturned(boolean ifReturned) {
		this.ifReturned = ifReturned;
	}

	public int getRenewTime() {
		return renewTime;
	}

	public void setRenewTime(int renewTime) {
		this.renewTime = renewTime;
	}

	public String getBorrowState() {
		return borrowState;
	}

	public void setBorrowState(String borrowState) {
		this.borrowState = borrowState;
	}

	@Override
	public String toString() {
		return "BookBorrowVO [AID=" + AID + ", readerID=" + readerID + ", readerName=" + readerName + ", bookID=" + bookID
				+ ", title=" + title + ", ISBN=" + ISBN + ", borrowTime=" + borrowTime + ", overTime=" + overTime
				+ ", returnedTime=" + returnedTime + ", ifReturned=" + ifReturned + ", renewTime=" + renewTime
				+ ", borrowState=" + borrowState + "]";
	}
	
	
}
