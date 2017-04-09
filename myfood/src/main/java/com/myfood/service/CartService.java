package com.myfood.service;

import java.util.List;

import com.myfood.model.CartItem;

public interface CartService {
	
	public int addItemToCart(int itemId, int customerId);
	public List<CartItem> getActiveCustomerCartByCustomerId(int customerId);
	public double getTotalItemsCost(List<CartItem> cartItems);

}
