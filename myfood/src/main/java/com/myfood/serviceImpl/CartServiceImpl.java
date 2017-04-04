package com.myfood.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myfood.dao.FoodCartDao;
import com.myfood.dao.RestaurantMenuDao;
import com.myfood.model.CartItem;
import com.myfood.model.CartPK;
import com.myfood.model.MenuItem;
import com.myfood.service.CartService;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private RestaurantMenuDao restaurantMenuDao;
	
	@Autowired
	private FoodCartDao foodCartDao;

	@Transactional
	public int addItemToCart(int itemId, int customerId) {
		MenuItem item = restaurantMenuDao.getMenuByItemId(itemId);
		CartItem cartItem = new CartItem();
		CartPK cartPK = new CartPK();
		cartPK.setCartIndexId(foodCartDao.getRecentCartId()+1);
		cartPK.setCustomerId(customerId);
		cartItem.setCartPK(cartPK);
		cartItem.setItemId(itemId);
		cartItem.setRestaurantId(item.getRestaurantId());
		cartItem.setItemName(item.getItemName());
		cartItem.setItemQuantity(1);
		cartItem.setItemCost(item.getCost());
		cartItem.setActiveFlag("Y");
		foodCartDao.addItemToCart(cartItem);
		return item.getRestaurantId();
	}

	public RestaurantMenuDao getRestaurantMenuDao() {
		return restaurantMenuDao;
	}

	public void setRestaurantMenuDao(RestaurantMenuDao restaurantMenuDao) {
		this.restaurantMenuDao = restaurantMenuDao;
	}

	public FoodCartDao getFoodCartDao() {
		return foodCartDao;
	}

	public void setFoodCartDao(FoodCartDao foodCartDao) {
		this.foodCartDao = foodCartDao;
	}

}
