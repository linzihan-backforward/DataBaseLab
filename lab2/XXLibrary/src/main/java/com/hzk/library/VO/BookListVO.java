/**
 * 
 */
package com.hzk.library.VO;

import java.util.Date;

import com.hzk.library.Entity.BookCategory;

/**
 * @author linzihan
 *
 */
public class BookListVO {
	
	private String isbn;
	
	private String title;
	
	private BookCategory category;
	
	private String author;
	
	private String press;
	
	private Date publishDay;
	
	private int pages;
	
	private Double price;
	
	private String intro;

	private int bookcnt;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BookCategory getCategory() {
		return category;
	}

	public void setCategory(BookCategory category) {
		this.category = category;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public Date getPublishDay() {
		return publishDay;
	}

	public void setPublishDay(Date publishDay) {
		this.publishDay = publishDay;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getBookcnt() {
		return bookcnt;
	}

	public void setBookcnt(int bookcnt) {
		this.bookcnt = bookcnt;
	}

	@Override
	public String toString() {
		return "BookListVO [isbn=" + isbn + ", title=" + title + ", category=" + category + ", author=" + author
				+ ", press=" + press + ", publishDay=" + publishDay + ", pages=" + pages + ", price=" + price
				+ ", intro=" + intro + ", bookcnt=" + bookcnt + "]";
	} 
	
}
