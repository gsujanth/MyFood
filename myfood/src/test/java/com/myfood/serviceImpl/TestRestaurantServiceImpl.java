package com.myfood.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.myfood.dao.RestaurantDao;
import com.myfood.model.MenuItem;
import com.myfood.model.Restaurant;
import com.myfood.service.RestaurantService;

@RunWith(MockitoJUnitRunner.class)
public class TestRestaurantServiceImpl {

	@Mock
	RestaurantDao restaurantDao;
	
	@InjectMocks
	RestaurantService restaurantService = new RestaurantServiceImpl();
	
	private List<Integer> restaurantIds = new ArrayList<Integer>();
	private List<Restaurant> restaurants = new ArrayList<Restaurant>();
	
	@Before
	public void setup() {
		
		restaurantIds.add(1);
		restaurantIds.add(2);
		
		Restaurant rest1 = new Restaurant();
		rest1.setRestaurantId(1);
		rest1.setRestaurantName("A");
		rest1.setAddress("Charlotte");
		rest1.setPincode(28262);
		rest1.setCuisine("Indian");
		
		Restaurant rest2 = new Restaurant();
		rest2.setRestaurantId(2);
		rest2.setRestaurantName("B");
		rest2.setAddress("Charlotte");
		rest2.setPincode(28262);
		rest2.setCuisine("Italian");
		
		restaurants.add(rest1);
		restaurants.add(rest2);
	}
	
	@Test
	public void testGetRestaurantByPincode(){
		
		when(restaurantDao.getRestaurantIdByPincode(any(Integer.class))).thenReturn(restaurantIds);
		when(restaurantDao.getRestaurantsByIds(any(List.class))).thenReturn(restaurants);
		
		List<Restaurant> restaurant = restaurantService.getRestaurantsByPincode(28262);
		
		verify(restaurantDao).getRestaurantIdByPincode(Matchers.eq(28262));
		verify(restaurantDao).getRestaurantsByIds(Matchers.eq(restaurantIds));
		
		assertNotNull(restaurant);
		assertEquals(2, restaurant.size());
		assertEquals("A", restaurant.get(0).getRestaurantName());
	}
	
	
}
