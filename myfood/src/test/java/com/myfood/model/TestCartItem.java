package com.myfood.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestCartItem {
	
	private CartItem item1 = new CartItem();

	@Before
	public void setup() {
		CartPK cartPK = new CartPK(1, 102);
		item1.setCartPK(cartPK);
		item1.setItemId(101);
		item1.setRestaurantId(1);
		item1.setItemName("Burger");
		item1.setItemQuantity(1);
		item1.setItemCost(8.50d);	
		item1.setActiveFlag("Y");
	}
	
	@Test
	public void testGetCartPK(){
		assertEquals(item1.getCartPK().getCartIndexId(),1);
		assertEquals(item1.getCartPK().getCustomerId(),102);
	}
	
	@Test
	public void testGetItemId(){
		assertEquals(item1.getItemId(), 101);
	}
	
	@Test
	public void testGetRestaurantId(){
		assertEquals(item1.getRestaurantId(), 1);
	}
	
	@Test
	public void testGetItemName(){
		assertEquals(item1.getItemName(), "Burger");
	}
			
	@Test
	public void testGetCost(){
		assertNotNull(item1.getItemCost());
	}
	
	@Test
	public void testGetActiveFlag(){
		assertEquals(item1.getActiveFlag(),"Y");
	}
	
}
