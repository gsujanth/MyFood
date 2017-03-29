package com.myfood.dao;

import java.util.List;

import com.myfood.model.Restaurant;

public interface RestaurantDao {

	public List<Integer> getRestaurantIdByPincode(int pincode);
	public List<Restaurant> getRestaurantsByIds(List<Integer> restuarantIdList);
}
