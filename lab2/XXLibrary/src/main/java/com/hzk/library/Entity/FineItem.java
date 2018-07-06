/**
 * 
 */
package com.hzk.library.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author linzihan
 *
 */
@Entity
@Table(name="fineitem")
public class FineItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="fineID")
	private Fine fine;
	
	@Column(name="finetype")
	private FineType type;
	
	@Column(nullable=true)
	private int borrowovertimeID;
	
	@Column(nullable=true)
	private int booklostID;
	
	@Column(nullable=true)
	private int bookdamagedID;

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
	 * @return the fine
	 */
	public Fine getFine() {
		return fine;
	}

	/**
	 * @param fine the fine to set
	 */
	public void setFine(Fine fine) {
		this.fine = fine;
	}

	/**
	 * @return the type
	 */
	public FineType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(FineType type) {
		this.type = type;
	}

	/**
	 * @return the borrowovertimeID
	 */
	public int getBorrowovertimeID() {
		return borrowovertimeID;
	}

	/**
	 * @param borrowovertimeID the borrowovertimeID to set
	 */
	public void setBorrowovertimeID(int borrowovertimeID) {
		this.borrowovertimeID = borrowovertimeID;
	}

	/**
	 * @return the booklostID
	 */
	public int getBooklostID() {
		return booklostID;
	}

	/**
	 * @param booklostID the booklostID to set
	 */
	public void setBooklostID(int booklostID) {
		this.booklostID = booklostID;
	}

	/**
	 * @return the bookdamagedID
	 */
	public int getBookdamagedID() {
		return bookdamagedID;
	}

	/**
	 * @param bookdamagedID the bookdamagedID to set
	 */
	public void setBookdamagedID(int bookdamagedID) {
		this.bookdamagedID = bookdamagedID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FineItem [ID=" + ID + ", fine=" + fine + ", type=" + type + ", borrowovertimeID=" + borrowovertimeID
				+ ", booklostID=" + booklostID + ", bookdamagedID=" + bookdamagedID + "]";
	}
	
	
	
	
}
