package com.myfood.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FoodCart")
public class CartItem {
	
	@EmbeddedId
    private CartPK cartPK;
	
	@Column(name = "ItemId", nullable = false)
	int itemId;
			
	@Column(name = "RestaurantId", nullable = false)
	int restaurantId;
	
	@Column(name = "ItemName", nullable = false)
	String itemName;
	
	@Column(name = "Quantity", nullable = false)
	int itemQuantity;
	
	@Column(name = "Cost", nullable = false)
	double itemCost;
	
	@Column(name = "ActiveFlag", nullable = false)
	String activeFlag;
	
	@Embeddable
	public class CartPK implements Serializable{
		@Column(name="CartIndexId")
		private int cartIndexId;
		@Column(name = "CustomerId")
		private int customerId;
		
		public int getCartIndexId() {
			return cartIndexId;
		}
		public void setCartIndexId(int cartIndexId) {
			this.cartIndexId = cartIndexId;
		}
		public int getCustomerId() {
			return customerId;
		}
		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}
	    
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

	public CartPK getCartPK() {
		return cartPK;
	}

	public void setCartPK(CartPK cartPK) {
		this.cartPK = cartPK;
	}
}
