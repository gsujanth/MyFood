package com.myfood.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menuitems")
public class MenuItem {
	
	@Id
	@Column(name="ItemId")
	private int itemId;
	
	@Column(name = "RestaurantId", nullable = false)
	private int restaurantId;
	
	@Column(name = "ItemName", nullable = false)
	private String itemName;
	
	@Column(name = "Category", nullable = false)
	private String category;
	
	@Column(name = "Cost", nullable = false)
	private double cost;
	
	@Column(name = "Calories", nullable = false)
	private int calories;
	
	@Column(name = "Flag", nullable = true)//sujanth
	private boolean flag;
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	} 
	
	//sujanth
	public boolean isFlag() {
		return flag;
	}
	
	//sujanth
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
