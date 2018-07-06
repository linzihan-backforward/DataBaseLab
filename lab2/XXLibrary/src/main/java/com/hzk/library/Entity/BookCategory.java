/**
 * 
 */
package com.hzk.library.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author linzihan
 *
 */
@Entity
@Table(name="bookcategory")
public class BookCategory {
	@Id
	private int ID;
	
	private String title;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BookCategory [ID=" + ID + ", title=" + title + "]";
	}

	/**
	 * 
	 */
	public BookCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param iD
	 * @param title
	 */
	public BookCategory(int iD, String title) {
		super();
		ID = iD;
		this.title = title;
	}

}
