package com.myfood.service;

import java.util.List;

import com.myfood.model.CartItem;
import com.myfood.model.OrderItem;
import com.myfood.model.Restaurant;

public interface OrderService {

	public boolean completeTransaction(String cardNumber, int cvv, int expiryMonth, int expiryYear, String cardName, double totalAmount);
	public int convertCartToOrder(List<CartItem> cartItems);
	public int storePaymentInfo(String cardNumber, String cardName, int orderId, double totalAmount);
	public int storeDeliveryInfo(int orderId, String customerName, long mobileNumber, String address, int pincode);
	public List<OrderItem> getAllOrders(int restaurantId);
}
