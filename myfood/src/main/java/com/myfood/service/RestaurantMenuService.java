package com.myfood.service;

import java.util.List;
import java.util.Map;

import com.myfood.model.MenuItem;

public interface RestaurantMenuService {
	
	public Map<String, List<MenuItem>> getMenuByRestaurant(int restaurantId);

}
