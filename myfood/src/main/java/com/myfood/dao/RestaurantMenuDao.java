package com.myfood.dao;

import java.util.List;
import com.myfood.model.MenuItem;

public interface RestaurantMenuDao {
	
	public List<MenuItem> getMenuByRestaurant(int restaurantId);
	
	public MenuItem getMenuByItemId(int itemId);//sujanth
	
	public int postMenuItemData(MenuItem menuitem);//sujanth
	
	public void removeMenuItem(int itemId) throws Exception;//sujanth
	
	public int getLastItemId();//sujanth
	
	public List<MenuItem> getMenuItems(int restaurantId);//sujanth

}
