package com.myfood.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestMenuItem {
	
	private MenuItem item1 = new MenuItem();
	
	@Before
	public void setup() {
		item1.setItemId(101);
		item1.setRestaurantId(1);
		item1.setItemName("Burger");
		item1.setCategory("FastFood");
		item1.setCalories(500);
		item1.setCost(8.50d);
		item1.setFlag(false);
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
	public void testGetCategory(){
		assertEquals(item1.getCategory(), "FastFood");
	}
	
	@Test
	public void testGetCost(){
		assertNotNull(item1.getCost());
	}
	
	@Test
	public void testGetCalories(){
		assertEquals(item1.getCalories(), 500);
	}
	
	//sujanth
	@Test
	public void testIsFlag(){
		assertEquals(item1.isFlag(),false);
	}
	
}
