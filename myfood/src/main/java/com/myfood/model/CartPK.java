package com.myfood.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CartPK implements Serializable{
	@Column(name="CartIndexId")
	private int cartIndexId;
	@Column(name = "CustomerId")
	private int customerId;
	
	public CartPK() {}
	
	public CartPK(int cartIndexId, int customerId) {
        this.cartIndexId = cartIndexId;
        this.customerId = customerId;
    }
	
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

	@Override
	public String toString() {
		return "CartPK [cartIndexId=" + cartIndexId + ", customerId=" + customerId + "]";
	}
	
	
    
}
