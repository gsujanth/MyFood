package com.myfood.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PincodeMapping")
public class PincodeMapping {

	@Id
	@Column(name="PincodeMappingId")
	private int pincodeMappingId;
	
	@Column(name="restaurantId")
	private int restaurantId;
	
	@Column(name="pincode")
	private int pincode;

	public int getPincodeMappingId() {
		return pincodeMappingId;
	}

	public void setPincodeMappingId(int pincodeMappingId) {
		this.pincodeMappingId = pincodeMappingId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
	
}
