package com.myfood.service;

import java.util.List;

import com.myfood.model.CartItem;

public interface CartService {
	
	public int addItemToCart(int itemId, int customerId);
	public List<CartItem> getActiveCustomerCartByCustomerId(int customerId);
	public int getActiveCustomerCartSizeByCustomerId(int customerId);
	public double getTotalItemsCost(List<CartItem> cartItems);
	public void updateCartItem(int customerId, int itemId, int qunatity, double updatedPrice);
	public void deleteCartItem(int customerId, int itemId);

}
