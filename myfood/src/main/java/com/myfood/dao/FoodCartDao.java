package com.myfood.dao;

import java.util.List;

import com.myfood.model.CartItem;

public interface FoodCartDao {
	
	public void addItemToCart(CartItem item);
	
	public int getRecentCartId();
	
	public List<CartItem> getActiveCustomerCartByCustomerId(int customerId);
	
	public void updateCartItem(CartItem cartItem);

}
