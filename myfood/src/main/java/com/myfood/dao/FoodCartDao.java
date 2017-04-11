package com.myfood.dao;

import java.util.List;

import com.myfood.model.CartItem;
import com.myfood.model.MenuItem;

public interface FoodCartDao {
	
	public void addItemToCart(int customerId, MenuItem item);
	
	public int getRecentCartId();
	
	public List<CartItem> getActiveCustomerCartByCustomerId(int customerId);
	
	public void updateCartItem(CartItem cartItem);
	
	public CartItem getCartForCustomerMenuItem(int customerId, int itemId);

}
