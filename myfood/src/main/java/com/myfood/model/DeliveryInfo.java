package com.myfood.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "deliveryInfo")
public class DeliveryInfo {

	@Id
	@Column(name="deliveryInfoId")
	private int deliveryInfoId;
	
	@Column(name="orderId")
	private int orderId;
	
	@Column(name="name")
	private String customerName;
	
	@Column(name="mobileNumber")
	private long mobileNumber;
	
	@Column(name="address")
	private String address;
	
	@Column(name="pincode")
	private int pincode;

	public int getDeliveryInfoId() {
		return deliveryInfoId;
	}

	public void setDeliveryInfoId(int deliveryInfoId) {
		this.deliveryInfoId = deliveryInfoId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "DeliveryInfo [deliveryInfoId=" + deliveryInfoId + ", orderId=" + orderId + ", customerName="
				+ customerName + ", mobileNumber=" + mobileNumber + ", address=" + address + ", pincode=" + pincode
				+ "]";
	}
	
	
	
}
