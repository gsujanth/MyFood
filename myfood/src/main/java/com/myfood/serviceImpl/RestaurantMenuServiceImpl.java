package com.myfood.serviceImpl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myfood.dao.RestaurantMenuDao;
import com.myfood.model.MenuItem;
import com.myfood.service.RestaurantMenuService;

@Service
public class RestaurantMenuServiceImpl implements RestaurantMenuService{
	
	@Autowired
	private RestaurantMenuDao restaurantMenuDao;

	@Transactional
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
	
	//sujanth
	public MenuItem getMenuByItemId(int itemId){
		return restaurantMenuDao.getMenuByItemId(itemId);
	}
	
	//sujanth
	public int postMenuItemData(MenuItem menuitem){
		menuitem.setItemId(getLastItemId()+1);
		menuitem.setFlag(true);
		System.out.println("In service setting Item Id:"+menuitem.getItemId());
		System.out.println(menuitem);
		System.out.println("---------------------------");
		return restaurantMenuDao.postMenuItemData(menuitem);
	}
	
	//sujanth
	public void removeMenuItem(int id) throws Exception{
		System.out.println("called service deleteRestaurnt");
		restaurantMenuDao.removeMenuItem(id);
	}
	
	//sujanth
	public int getLastItemId() {
		System.out.println("restaurantMenuDao.getLastRestaurantId()--"+restaurantMenuDao.getLastItemId());
		return restaurantMenuDao.getLastItemId();
	}

}
