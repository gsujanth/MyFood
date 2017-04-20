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
		} else {
			restaurantList = restaurantDao.getRestaurantsByIds(restaurantIds);
		}
		return restaurantList;
	}

	public List<Restaurant> getAllRestaurants() {
		List<Restaurant> restaurantList = restaurantDao.getAllRestaurants();
		if (restaurantList == null || restaurantList.size() == 0) {
			return null;
		} else {
			restaurantList = restaurantDao.getAllRestaurants();
		}
		return restaurantList;
	}
	
	public int getLastRestaurantId() {
		System.out.println("restaurantDao.getLastRestaurantId()--"+restaurantDao.getLastRestaurantId());
		return restaurantDao.getLastRestaurantId();
	}

	public int registerRestaurant(Restaurant restaurant) {
		restaurant.setRestaurantId(getLastRestaurantId()+1);
		restaurant.setFlag(true);
		System.out.println("In service setting Restaurant Id:"+restaurant.getRestaurantId());
		System.out.println(restaurant);
		System.out.println("---------------------------");
		return restaurantDao.registerRestaurant(restaurant);
	}

	public Restaurant getRestaurantByName(String name) {
		return restaurantDao.getRestaurantByName(name);
	}

	public void deleteRestaurnt(int id) throws Exception {
		System.out.println("called service deleteRestaurnt");
		restaurantDao.deleteRestaurant(id);
	}
	
	public int getResIdByRestaurantOwnerId(int id){
		return restaurantDao.getResIdByRestaurantOwnerId(id);
	}
}
