package com.myfood.service;

import java.util.List;

import com.myfood.model.CartItem;

public interface OrderService {

	public boolean completeTransaction(String cardNumber, int cvv, int expiryMonth, int expiryYear, String cardName, double totalAmount);
	public int convertCartToOrder(List<CartItem> cartItems);
	public int storePaymentInfo(String cardNumber, String cardName, int orderId, double totalAmount);
	public int storeDeliveryInfo(int orderId, String customerName, long mobileNumber, String address, int pincode);
}
