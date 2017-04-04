package com.myfood.dao;

import java.util.List;
import com.myfood.model.MenuItem;

public interface RestaurantMenuDao {
	
	public List<MenuItem> getMenuByRestaurant(int restaurantId);
	
	public MenuItem getMenuByItemId(int itemId);

}
