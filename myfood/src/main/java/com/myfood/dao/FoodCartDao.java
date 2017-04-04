package com.myfood.dao;

import com.myfood.model.CartItem;

public interface FoodCartDao {
	
	public void addItemToCart(CartItem item);
	
	public int getRecentCartId();

}
