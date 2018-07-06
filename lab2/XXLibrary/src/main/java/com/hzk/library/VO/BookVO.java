/**
 * 
 */
package com.hzk.library.VO;

import java.util.Date;

/**
 * @author linzihan
 *
 */
public class BookVO {
	private int ID;
	private String title;
	private String state; //状态
	private String isbn;
	private String category;
	private String author;
	private String press;
	private Date publishDay;
    private int pages;
	
	private Double price;
	
	private String intro;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
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

	@Override
	public String toString() {
		return "BookVO [ID=" + ID + ", title=" + title + ", state=" + state + ", isbn=" + isbn + ", category="
				+ category + ", author=" + author + ", press=" + press + ", publishDay=" + publishDay + ", pages="
				+ pages + ", price=" + price + ", intro=" + intro + "]";
	}
	
	
}
