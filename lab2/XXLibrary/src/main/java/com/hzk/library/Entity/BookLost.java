/**
 * 
 */
package com.hzk.library.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 丢失图书记录类
 * @author linzihan
 *
 */
@Entity
@Table(name="booklost")
public class BookLost {
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name="userID")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="bookID")
	private Book lostBook;
	
	@OneToOne
	@JoinColumn(name="borrowID")
	private BookBorrow bookBorrow;
	
	private int type;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the lostBook
	 */
	public Book getLostBook() {
		return lostBook;
	}

	/**
	 * @param lostBook the lostBook to set
	 */
	public void setLostBook(Book lostBook) {
		this.lostBook = lostBook;
	}

	/**
	 * @return the bookBorrow
	 */
	public BookBorrow getBookBorrow() {
		return bookBorrow;
	}

	/**
	 * @param bookBorrow the bookBorrow to set
	 */
	public void setBookBorrow(BookBorrow bookBorrow) {
		this.bookBorrow = bookBorrow;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BookLost [id=" + id + ", user=" + user + ", lostBook=" + lostBook + ", bookBorrow=" + bookBorrow
				+ ", type=" + type + "]";
	}

}
