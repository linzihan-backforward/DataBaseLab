/**
 * 
 */
package com.hzk.library.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author linzihan
 *
 */
@Entity
@Table(name="book")
public class Book {
	@Id
	@Min(1)
	private int ID;
	
	@ManyToOne
	@JoinColumn(name="isbn")
	private BookList bookList;
	
	@NotBlank
	private String title;
	
	@ManyToOne
	@JoinColumn(name="state_ID")
	private BookState state;

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
	 * @return 书名
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return 书的状态
	 */
	public BookState getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(BookState state) {
		this.state = state;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

	public BookList getBookList() {
		return bookList;
	}

	public void setBookList(BookList bookList) {
		this.bookList = bookList;
	}

	@Override
	public String toString() {
		return "Book [ID=" + ID +  ", 书名=" + title +", 基本信息=" + bookList + ", 状态=" + state.getTitle() + "]";
	}

	/**
	 * @param iD
	 * @param title
	 * @param state
	 */
	public Book(int iD, String title) {
		super();
		ID = iD;
		this.title = title;
	}

	/**
	 * 
	 */
	public Book() {
		
	}

}
