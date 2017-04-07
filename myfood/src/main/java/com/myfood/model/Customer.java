package com.myfood.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer {
	
	@Id
	@Column(name="CustomerId")
	private int customerId;
	
	@Column(name = "FirstName", nullable = true)
	private String firstName;
	
	@Column(name = "LastName", nullable = true)
	private String lastName;
	
	@Column(name = "MobileNumber", nullable = true)
	private long mobileNumber;
	
	@Column(name = "Email", nullable = true)
	private String email;
	
	@Column(name = "Address", nullable = true)
	private String address;
	
	@Column(name = "ZIPCode", nullable = true)
	private int zipCode;
	
	@Column(name = "Password", nullable = true)
	private String password;
	
	@Column(name = "Role", nullable = true)
	private String role;

	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
