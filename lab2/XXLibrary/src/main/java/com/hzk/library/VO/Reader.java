/**
 * 
 */
package com.hzk.library.VO;

/**
 * 用于将读者概况传到视图端的对象。与User相比多了一个借书数量字段，少了密码字段。
 *
 */
public class Reader {
	private String id;

	private String name;

	private String readerID;
	
	private String gender;
	
	private String Email;
	
	private String phoneNumber;
	
	private String state;
	
	private int checkoutAmount;
	
	private String role;
	//当前所借图书数量
	private int borrowedBookCnt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReaderID() {
		return readerID;
	}
	public void setReaderID(String readerID) {
		this.readerID = readerID;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getCheckoutAmount() {
		return checkoutAmount;
	}
	public void setCheckoutAmount(int checkoutAmount) {
		this.checkoutAmount = checkoutAmount;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getBorrowedBookCnt() {
		return borrowedBookCnt;
	}
	public void setBorrowedBookCnt(int borrowedBookCnt) {
		this.borrowedBookCnt = borrowedBookCnt;
	}

	
}
