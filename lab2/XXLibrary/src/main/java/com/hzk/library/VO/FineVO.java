/**
 * 
 */
package com.hzk.library.VO;

import java.util.Date;

import com.hzk.library.Entity.FineType;

/**
 * @author linzihan
 *
 */
public class FineVO {
	private int id;
	private String userName;
	private float amount;
	private FineType finetype;
	private boolean ispayed;
	private Date payeddate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public FineType getFinetype() {
		return finetype;
	}
	public void setFinetype(FineType finetype) {
		this.finetype = finetype;
	} 
	public boolean isIspayed() {
		return ispayed;
	}
	public void setIspayed(boolean ispayed) {
		this.ispayed = ispayed;
	}
	public Date getPayeddate() {
		return payeddate;
	}
	public void setPayeddate(Date payeddate) {
		this.payeddate = payeddate;
	}
	@Override
	public String toString() {
		return "FineVO [id=" + id + ", userName=" + userName + ", amount=" + amount + ", ispayed=" + ispayed
				+ ", payeddate=" + payeddate + "]";
	}
}
