package com.myfood.dao;

import java.util.List;
import com.myfood.model.MenuItem;

public interface RestaurantMenuDao {
	
	public List<MenuItem> getMenuByRestaurant(int restaurantId);
	
	public MenuItem getMenuByItemId(int itemId);
	
	public int postMenuItemData(MenuItem menuitem);
	
	public void removeMenuItem(int id) throws Exception;
	
	public int getLastItemId();

}
