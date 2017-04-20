package com.myfood.service;

import java.util.List;
import com.myfood.model.Restaurant;

public interface RestaurantService {

	public List<Restaurant> getRestaurantsByPincode(int pincode);
	public List<Restaurant> getAllRestaurants();
	public int registerRestaurant(Restaurant restaurant);
	public Restaurant getRestaurantByName(String name);
	public void deleteRestaurnt(int id) throws Exception;
	public int getResIdByRestaurantOwnerId(int id);
}
