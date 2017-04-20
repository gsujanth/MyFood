package com.myfood.dao;

import java.util.List;

import com.myfood.model.OrderItem;
import com.myfood.model.Restaurant;

public interface OrderDao {

	public int getRecentOrderId();
	public int getRecentOrderIndexId();
	public void addOrderItem(OrderItem item);
	public List<OrderItem> getAllOrders(int restaurantId);
}
