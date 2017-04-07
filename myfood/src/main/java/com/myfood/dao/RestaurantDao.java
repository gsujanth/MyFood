package com.myfood.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.myfood.model.Restaurant;

public interface RestaurantDao {

	public List<Integer> getRestaurantIdByPincode(int pincode);
	public List<Restaurant> getRestaurantsByIds(List<Integer> restuarantIdList);
	public List<Restaurant> getAllRestaurants();
	public int getLastRestaurantId();
	public int registerRestaurant(Restaurant restaurant);
	public Restaurant getRestaurantByName(String name);
	public void deleteRestaurant(int id) throws Exception;
}
