package com.myfood.model;

public class SelectedItems {
	int itemId;
	int restaurantId;
	String itemName;
	String itemCategory;
	double itemCost;
	int itemCalories;
	int itemQuantity;
	
	public SelectedItems(){
		
	}
	
	public SelectedItems(int itemId,int restaurantId,String itemName,String itemCategory,double itemCost,int itemCalories,int itemQuantity){
		this.itemId=itemId;
		this.restaurantId=restaurantId;
		this.itemName=itemName;
		this.itemCategory=itemCategory;
		this.itemCost=itemCost;
		this.itemCalories=itemCalories;
		this.itemQuantity=itemQuantity;
	}
	
	public int getItemId() {
		return itemId;
	}
	
	public int getRestaurantId() {
		return restaurantId;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public String getCategory() {
		return itemCategory;
	}
	
	public double getCost() {
		return itemCost;
	}
	
	public int getCalories() {
		return itemCalories;
	}
	
	public int getQuantity() {
		return itemQuantity;
	}
	
}
