package com.myfood.daoImpl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myfood.dao.FoodCartDao;
import com.myfood.model.CartItem;
import com.myfood.model.CartPK;
import com.myfood.model.MenuItem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-beans.xml","classpath:test-datasource.xml" })
public class TestFoodCartDaoImpl {
	
	@Autowired
	private FoodCartDao foodCartDao;
	
	@Test
	public void testGetRecentCartId(){
		assertNotNull(foodCartDao.getRecentCartId());
	}
	
	@Test
	public void testGetActiveCartByCustomerId(){
		int customerId = 102;
		List<CartItem> cartList = foodCartDao.getActiveCustomerCartByCustomerId(customerId);
		assertNotNull(cartList.size());
	}
	
	@Test
	public void testGetQuantityForCustomerItem(){
		int customerId = 102;
		CartItem item = foodCartDao.getCartForCustomerMenuItem(customerId, 7);
		assertEquals(1,item.getItemQuantity());
	}
	
	@Test
	public void testAddItemToCart(){
		int customerId = 102;
		MenuItem item = new MenuItem();
		item.setItemId(5);
		item.setRestaurantId(1);
		item.setItemName("Chicken Biryani");
		item.setCost(6.75d);
		foodCartDao.addItemToCart(customerId, item);
	}
	
	@Test
	public void testUpdateCartQunatity(){
		foodCartDao.updateCartQunatity(102, 5, 2, 13.5d);
		CartItem item = foodCartDao.getCartForCustomerMenuItem(102,5);
		assertEquals(item.getItemQuantity(), 2);
		assertEquals(item.getItemCost(), 13.5d, 0);
	}

}
