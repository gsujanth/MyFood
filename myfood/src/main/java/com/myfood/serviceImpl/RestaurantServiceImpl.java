package com.myfood.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.myfood.dao.RestaurantDao;
import com.myfood.model.Restaurant;
import com.myfood.service.RestaurantService;

public class RestaurantServiceImpl implements RestaurantService{

	@Autowired
	private RestaurantDao restaurantDao;
	
	
	public RestaurantDao getRestaurantDao() {
		return restaurantDao;
	}

	public void setRestaurantDao(RestaurantDao restaurantDao) {
		this.restaurantDao = restaurantDao;
	}
	
	public List<Restaurant> getRestaurantsByPincode(int pincode){
		List<Integer> restaurantIds = restaurantDao.getRestaurantIdByPincode(pincode);
		List<Restaurant> restaurantList = null;
		if(restaurantIds == null || restaurantIds.size() == 0){
			return null;
		}
		else{
			restaurantList = restaurantDao.getRestaurantsByIds(restaurantIds);
		}
		return restaurantList;
	}
}
