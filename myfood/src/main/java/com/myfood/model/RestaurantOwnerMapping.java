package com.myfood.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restaurantownermapping")
public class RestaurantOwnerMapping {

	@Id
	@Column(name="restaurantOwnerMappingId")
	private int restaurantOwnerMappingId;
	
	@Column(name="restaurantId")
	private int restaurantId;
	
	@Column(name="restaurantOwnerId")
	private int restaurantOwnerId;

	public int getRestaurantOwnerMappingId() {
		return restaurantOwnerMappingId;
	}

	public void setRestaurantOwnerMappingId(int restaurantOwnerMappingId) {
		this.restaurantOwnerMappingId = restaurantOwnerMappingId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public int getRestaurantOwnerId() {
		return restaurantOwnerId;
	}

	public void setRestaurantOwnerEmail(int restaurantOwnerId) {
		this.restaurantOwnerId = restaurantOwnerId;
	}
	
	
}
