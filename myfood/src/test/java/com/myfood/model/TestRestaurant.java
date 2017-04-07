package com.myfood.model;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.myfood.model.Restaurant;

public class TestRestaurant {
	
	private Restaurant restaurant = new Restaurant();

	@Before
	public void setUp() throws Exception {
		restaurant.setRestaurantId(101);
		restaurant.setRestaurantName("PATHWAY TO INDIA");
		restaurant.setAddress("9547, UT Drive, Charlotte, NC");
		restaurant.setPincode(28262);
		restaurant.setCuisine("Indian");
		restaurant.setFlag(true);
	}

	@Test
	public void testGetRestaurantId() {
		assertEquals(restaurant.getRestaurantId(), 101);
	}
	
	@Test
	public void testGetRestaurantName() {
		assertEquals(restaurant.getRestaurantName(), "PATHWAY TO INDIA");
	}
	
	@Test
	public void testGetAddress() {
		assertEquals(restaurant.getAddress(), "9547, UT Drive, Charlotte, NC");
	}
	
	@Test
	public void testGetPincode() {
		assertEquals(restaurant.getPincode(), 28262);
	}
	
	@Test
	public void testGetCusine() {
		assertEquals(restaurant.getCuisine(), "Indian");
	}
	
	@Test
	public void testFlag() {
		assertTrue(restaurant.isFlag());
	}

}
