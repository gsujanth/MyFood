package com.myfood.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Restaurant")
public class Restaurant {

	@Id
	@Column(name="RestaurantId")
	private int restaurantId;
	
	@Column(name = "RestaurantName", nullable = false)
	private String restaurantName;
	
	@Column(name="Address")
	private String address;	
	
	@Column(name = "Pincode", nullable = false)
	private int pincode;
	
	@Column(name = "Cuisine", nullable = false)
	private String cuisine;

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
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

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	@Override
	public String toString() {
		return "Restaurant [RestaurantId=" + restaurantId + ", restaurantName=" + restaurantName + ", address="
				+ address + ", pincode=" + pincode + ", cuisine=" + cuisine + "]";
	}
	
	
}
