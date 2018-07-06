/**
 * 
 */
package com.hzk.library.Entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="bookstock")
public class BookStock {
	@Id
	private String isbn;
	
	private String title;
	@ManyToOne
	@JoinColumn(name="location_ID")
	private Library libraryArea;
	
	private int amount;
	
	@Column(name="entryday")
	@Temporal(TemporalType.DATE)
	private Calendar entryDay;

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
	 * @return the libraryArea
	 */
	public Library getLibraryArea() {
		return libraryArea;
	}

	/**
	 * @param libraryArea the libraryArea to set
	 */
	public void setLibraryArea(Library libraryArea) {
		this.libraryArea = libraryArea;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the entryDay
	 */
	public Calendar getEntryDay() {
		return entryDay;
	}

	/**
	 * @param entryDay the entryDay to set
	 */
	public void setEntryDay(Calendar entryDay) {
		this.entryDay = entryDay;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA); 
		return "BookStock [isbn=" + isbn + ", 书名=" + title + ", 库区=" + libraryArea + ", 库存数量=" + amount
				+ ",入库日期=" + format.format(entryDay.getTime())  + "]";
	}
	
	
}
