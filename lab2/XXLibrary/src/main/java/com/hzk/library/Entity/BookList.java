/**
 * 
 */
package com.hzk.library.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author linzihan
 *
 */
@Entity
@Table(name="booklist")
public class BookList {
	
	@Id
	private String isbn;
	
	private String title;
	
	@ManyToOne
	@JoinColumn(name="category_ID")
	private BookCategory category;
	
	private String author;
	
	private String press;
	
	@Column(name="publishday")
	@Temporal(TemporalType.DATE)
	private Date publishDay;
	
	private int pages;
	
	private Double price;
	
	private String intro;

	@OneToMany(mappedBy="bookList")
	private List<Book> books; 
	
	
	
	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the title
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
	 * @return the category
	 */
	public BookCategory getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(BookCategory category) {
		this.category = category;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the press
	 */
	public String getPress() {
		return press;
	}

	/**
	 * @param press the press to set
	 */
	public void setPress(String press) {
		this.press = press;
	}

	/**
	 * @return the publishDay
	 */
	public Date getPublishDay() {
		return publishDay;
	}

	/**
	 * @param publishDay the publishDay to set
	 */
	public void setPublishDay(Date publishDay) {
		this.publishDay = publishDay;
	}

	/**
	 * @return the pages
	 */
	public int getPages() {
		return pages;
	}

	/**
	 * @param pages the pages to set
	 */
	public void setPages(int pages) {
		this.pages = pages;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the intro
	 */
	public String getIntro() {
		return intro;
	}

	/**
	 * @param intro the intro to set
	 */
	public void setIntro(String intro) {
		this.intro = intro;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "BookList [ISBN=" + isbn + ", 书名=" + title + ", 类别=" + category.getTitle() + ", 作者=" + author
				+ ", 出版社=" + press + ", 出版日期=" + publishDay + ", 总页数=" + pages + ", 单价=" + price
				+",库存（本）="+books.size()+ ", 简介=" + intro  + "]";
	}

	
}
