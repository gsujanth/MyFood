package com.myfood.serviceImpl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.myfood.dao.RestaurantMenuDao;
import com.myfood.model.MenuItem;
import com.myfood.service.RestaurantMenuService;

public class RestaurantMenuServiceImpl implements RestaurantMenuService{
	
	@Autowired
	private RestaurantMenuDao restaurantMenuDao;

	public Map<String, List<MenuItem>> getMenuByRestaurant(int restaurantId) {
		List<MenuItem> itemsList = restaurantMenuDao.getMenuByRestaurant(restaurantId);
		Map<String, List<MenuItem>> itemsByCategories = new HashMap<String, List<MenuItem>>();
		for(MenuItem item : itemsList){
			String category = item.getCategory();
			if(null == itemsByCategories.get(category))
			{
				List<MenuItem> itemsListByCategory = new ArrayList<MenuItem>();
				itemsListByCategory.add(item);
				itemsByCategories.put(category, itemsListByCategory);
			}
			else
			{
				List<MenuItem> itemsListByCategory = itemsByCategories.get(category);
				itemsListByCategory.add(item);
				itemsByCategories.put(category, itemsListByCategory);
			}
		}
		return itemsByCategories;
	}

	public RestaurantMenuDao getRestaurantMenuDao() {
		return restaurantMenuDao;
	}

	public void setRestaurantMenuDao(RestaurantMenuDao restaurantMenuDao) {
		this.restaurantMenuDao = restaurantMenuDao;
	}

}
