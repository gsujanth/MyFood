package com.myfood.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderItem")
public class OrderItem {

	@Id
	@Column(name="orderIndexId")
	private int orderIndexId;
	
	@Column(name="orderId")
	private int orderId;
	
	@Column(name = "CustomerId")
	private int customerId;
	
	@Column(name = "ItemId", nullable = false)
	private int itemId;
			
	@Column(name = "RestaurantId", nullable = false)
	private int restaurantId;
	
	@Column(name = "ItemName", nullable = false)
	private String itemName;
	
	@Column(name = "itemQuantity", nullable = false)
	private int itemQuantity;
	
	@Column(name = "Cost", nullable = false)
	private double itemCost;
	
	@Column(name = "ActiveFlag", nullable = false)
	private String activeFlag;

	public int getOrderIndexId() {
		return orderIndexId;
	}

	public void setOrderIndexId(int orderIndexId) {
		this.orderIndexId = orderIndexId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

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

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public double getItemCost() {
		return itemCost;
	}

	public void setItemCost(double itemCost) {
		this.itemCost = itemCost;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	@Override
	public String toString() {
		return "OrderItem [orderIndexId=" + orderIndexId + ", orderId=" + orderId + ", customerId=" + customerId
				+ ", itemId=" + itemId + ", restaurantId=" + restaurantId + ", itemName=" + itemName + ", itemQuantity="
				+ itemQuantity + ", itemCost=" + itemCost + ", activeFlag=" + activeFlag + "]";
	}
	
}
