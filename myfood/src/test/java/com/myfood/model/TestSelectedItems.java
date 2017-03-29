package com.myfood.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestSelectedItems {
	
	private SelectedItems item1 = new SelectedItems();

	@Before
	public void setup() {
		item1.itemId=101;
		item1.restaurantId=1;
		item1.itemName="Burger";
		item1.itemCategory="FastFood";
		item1.itemCalories=500;
		item1.itemCost=8.50d;		
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
}
