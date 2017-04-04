package com.myfood.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestCartItem {
	
	private CartItem item1 = new CartItem();

	@Before
	public void setup() {
		item1.setItemId(101);
		item1.setRestaurantId(1);
		item1.setItemName("Burger");
		item1.setItemCost(8.50d);		
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
	
}
