package com.myfood.serviceImpl;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.myfood.dao.FoodCartDao;
import com.myfood.dao.RestaurantMenuDao;
import com.myfood.model.CartItem;
import com.myfood.model.CartPK;
import com.myfood.model.MenuItem;
import com.myfood.service.CartService;

@RunWith(MockitoJUnitRunner.class)
public class TestCartServiceImpl {
	
	@Mock
    private FoodCartDao foodCartDao;
	
	@Mock
    private RestaurantMenuDao restaurantMenuDao;
	
	@InjectMocks
    private CartService cartService = new CartServiceImpl();
	
	@Test
	public void testAddItemToCart(){
		MenuItem item1 = new MenuItem();
		item1.setItemId(101);
		item1.setRestaurantId(1);
		item1.setItemName("Burger");
		item1.setCategory("FastFood");
		item1.setCalories(500);
		item1.setCost(8.50d);
		int customerId = 102;
		when(restaurantMenuDao.getMenuByItemId(any(Integer.class))).thenReturn(item1);
		int restaurantId = cartService.addItemToCart(101, customerId);	
		verify(foodCartDao, times(1)).addItemToCart(customerId,item1);
		assertEquals(restaurantId,1);
	}
	
	@Test
	public void testGetActiveCustomerCartByCustomerId(){
		List<CartItem> cartItems = new ArrayList<CartItem>();
		CartItem item1 = new CartItem();
		CartPK cartPK = new CartPK(1, 102);
		item1.setCartPK(cartPK);
		item1.setItemId(101);
		item1.setRestaurantId(1);
		item1.setItemName("Burger");
		item1.setItemQuantity(1);
		item1.setItemCost(8.50d);	
		item1.setActiveFlag("Y");
		
		cartItems.add(item1);
		when(foodCartDao.getActiveCustomerCartByCustomerId(any(Integer.class))).thenReturn(cartItems);
		List<CartItem> cartFromMethodcall = cartService.getActiveCustomerCartByCustomerId(101);
		assertEquals(cartFromMethodcall,cartItems);
		verify(foodCartDao, times(1)).getActiveCustomerCartByCustomerId(101);
	}
	
	@Test
	public void testUpdateCartItem(){
		cartService.updateCartItem(101, 5, 2, 13d);
		verify(foodCartDao, times(1)).updateCartQunatity(101, 5, 2, 13d);
	}

}
