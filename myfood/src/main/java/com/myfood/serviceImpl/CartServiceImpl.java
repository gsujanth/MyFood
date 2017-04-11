package com.myfood.serviceImpl;

import java.util.List;

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
		foodCartDao.addItemToCart(customerId, item);
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
	
	public List<CartItem> getActiveCustomerCartByCustomerId(int customerId){
		return foodCartDao.getActiveCustomerCartByCustomerId(customerId);
	}
	
	public double getTotalItemsCost(List<CartItem> cartItems){
		double totalCost = 0;
		if(cartItems == null || cartItems.size() == 0)
			return totalCost; //returns cost as 0.
		for (CartItem cartItem : cartItems) {
			totalCost = totalCost + cartItem.getItemCost(); 
		}
		return totalCost;
	}

}
