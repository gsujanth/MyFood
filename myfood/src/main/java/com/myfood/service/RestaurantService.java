package com.myfood.service;

import java.util.List;

import com.myfood.model.Restaurant;

public interface RestaurantService {

	public List<Restaurant> getRestaurantsByPincode(int pincode);
}
