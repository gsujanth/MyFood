package com.myfood.service;

import java.util.List;
import java.util.Map;

import com.myfood.model.MenuItem;
import com.myfood.model.Restaurant;

public interface RestaurantMenuService {
	
	public Map<String, List<MenuItem>> getMenuByRestaurant(int restaurantId);
	
	public MenuItem getMenuByItemId(int itemId);//sujanth
	public int postMenuItemData(MenuItem menuitem);//sujanth
	public void removeMenuItem(int id) throws Exception;//sujanth

}
