/**
 * 
 */
package com.hzk.library.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author linzihan
 *
 */
@Entity
@Table(name="role")
public class UserRole {
	@Id
	@GeneratedValue
	private int ID;
	
	@Column(name="rolename")
	private String roleName;
	
	private String remark;
	
	@Column(name="ifmanager")
	private boolean isManager;
	
	@Column(name="checkoutperiod")
	private int checkoutPeriod;
	
	@Column(name="checkoutamount")
	private int checkoutAmount;

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
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the ifManager
	 */
	public boolean getIfManager() {
		return isManager;
	}

	/**
	 * @param ifManager the ifManager to set
	 */
	public void setIfManager(Boolean ifManager) {
		this.isManager = ifManager;
	}

	/**
	 * @return the checkoutPeriod
	 */
	public int getCheckoutPeriod() {
		return checkoutPeriod;
	}

	/**
	 * @param checkoutPeriod the checkoutPeriod to set
	 */
	public void setCheckoutPeriod(int checkoutPeriod) {
		this.checkoutPeriod = checkoutPeriod;
	}

	/**
	 * @return the checkoutAmount
	 */
	public int getCheckoutAmount() {
		return checkoutAmount;
	}

	/**
	 * @param checkoutAmount the checkoutAmount to set
	 */
	public void setCheckoutAmount(int checkoutAmount) {
		this.checkoutAmount = checkoutAmount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Role [ID=" + ID + ", roleName=" + roleName + ", remark=" + remark + ", isManager=" + isManager
				+ ", checkoutPeriod=" + checkoutPeriod + ", checkoutAmount=" + checkoutAmount + "]";
	}

	
}
