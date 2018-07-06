/**
 * 
 */
package com.hzk.library.Entity;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * 罚款表
 * @author linzihan
 *
 */
@Entity
@Table(name="fine")
public class Fine {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID;
	
	@ManyToOne
	@JoinColumn(name="userID")
	private User user;
	
	private float amount;
	
	private FineType finetype;
	
	private boolean ispayed;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date payeddate; 
	
	@OneToMany(cascade={CascadeType.PERSIST}, mappedBy="fine")
	private Set<FineItem> items;

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
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	public FineType getFinetype() {
		return finetype;
	}

	public void setFinetype(FineType finetype) {
		this.finetype = finetype;
	}
	
	/**
	 * @return the ispayed
	 */
	public boolean isIspayed() {
		return ispayed;
	}

	/**
	 * @param ispayed the ispayed to set
	 */
	public void setIspayed(boolean ispayed) {
		this.ispayed = ispayed;
	}

	/**
	 * @return the payeddate
	 */
	public Date getPayeddate() {
		return payeddate;
	}

	/**
	 * @param payeddate the payeddate to set
	 */
	public void setPayeddate(Date payeddate) {
		this.payeddate = payeddate;
	}

	
	/**
	 * @return the items
	 */
	public Collection<FineItem> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(Set<FineItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Fine [ID=" + ID + ", user=" + user + ", amount=" + amount + ", ispayed=" + ispayed + ", payeddate="
				+ payeddate + ", items=" + items + "]";
	}
	
}
