package com.myfood.dao;

import com.myfood.model.OrderItem;

public interface OrderDao {

	public int getRecentOrderId();
	public int getRecentOrderIndexId();
	public void addOrderItem(OrderItem item);
}
