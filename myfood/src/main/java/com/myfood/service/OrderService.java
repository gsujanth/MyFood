package com.myfood.service;

import java.util.List;

import com.myfood.model.CartItem;
import com.myfood.model.OrderItem;
import com.myfood.model.OrderStatus;

public interface OrderService {

	public boolean completeTransaction(String cardNumber, int cvv, int expiryMonth, int expiryYear, String cardName, double totalAmount);
	public int convertCartToOrder(List<CartItem> cartItems);
	public int storePaymentInfo(String cardNumber, String cardName, int orderId, double totalAmount);
	public int storeDeliveryInfo(int orderId, String customerName, long mobileNumber, String address, int pincode);
	public List<OrderItem> getAllOrders(int restaurantId);
	public int getCustomerIdByOrderId(int orderId);
	public List<OrderItem> getOrderDetailsByCustomerAndOrderId(int customerId, int orderId);
	public double getTotalCostOfItems(List<OrderItem> orderItems);
	public void cancelOrder(int orderId) throws Exception;
	public int getRestaurantIdByOrderId(int orderId);
	public void insertIntoOrderStatusTable(OrderStatus orderStatus, int orderId, String comments);
	public void insertIntoOrderStatusOnConfirm(OrderStatus orderStatus, int orderId, String hh, String mm);
	public List<OrderStatus> getConfirmedOrdersByRestaurant(int restaurantId);
	public List<String> getOrderStatusList();
	public void updateOrderStatus(int orderId, String status);
	public List<String> getAllMyOrders(int customerId);
	public List<OrderStatus> getOrderTrackingDetails(int orderId);
}
