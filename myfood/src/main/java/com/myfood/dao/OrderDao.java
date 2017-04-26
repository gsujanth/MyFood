package com.myfood.dao;

import java.util.List;
import com.myfood.model.OrderItem;
import com.myfood.model.OrderStatus;

public interface OrderDao {

	public int getRecentOrderId();
	public int getRecentOrderIndexId();
	public void addOrderItem(OrderItem item);
	public List<OrderItem> getAllOrders(int restaurantId);
	public int getCustomerIdByOrderId(int orderId);
	public List<OrderItem> getOrderDetailsByCustomerAndOrderId(int customerId, int orderId);
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
