/**
 * 
 */
package com.hzk.library.Entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * @author linzihan
 *
 */
@Entity
@Access(AccessType.FIELD)   //注释只有在变量上才生效。
public class User {
	@Id
	@GeneratedValue
	private String id;
	private String name;
	
	private String password;
	
	private String readerID;
	
	//在Get方法上定义ORM
	@Transient
	private String gender;
	
	private String Email;
	
	@Column(name="phonenumber")
	private String phoneNumber;
	
	private UserState state;
	
	@Column(name="checkoutamount")
	private int checkoutAmount;
	
	@ManyToOne 
    @JoinColumn(name="roleID") 
	private UserRole role;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the readerID
	 */
	public String getReaderID() {
		return readerID;
	}
	/**
	 * @param readerID the readerID to set
	 */
	public void setReaderID(String readerID) {
		this.readerID = readerID;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Access(AccessType.PROPERTY)
	@Column(name="gender")
	public int getGenderForDb(){
		if ("男".equals(this.gender)) {
			return 0;
		} else if ("女".equals(this.gender)) {
			return 1;
		} else return 2;
	}
	public void setGenderForDb(int gender ){
		switch (gender) {
		case 0:
			this.gender="男";
			break;
		case 1:
			this.gender="女";
			break;
		default:
			this.gender="未知";
			break;
		}		   
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return Email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		Email = email;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * @return the state
	 */
	public UserState getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(UserState state) {
		this.state = state;
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
	/**
	 * @return the role
	 */

	public UserRole getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(UserRole role) {
		this.role = role;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", readerID=" + readerID + ", gender="
				+ gender + ", Email=" + Email + ", phoneNumber=" + phoneNumber + ", state=" + state
				+ ", checkoutAmount=" + checkoutAmount + ", role=" + role + "]";
	}
	/**
	 * @param id
	 */
	public User(String id) {
		super();
		this.id = id;
	}
	
	/**
	 * 
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
