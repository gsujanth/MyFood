package com.myfood.serviceImpl;

import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import com.myfood.dao.RestaurantMenuDao;
import com.myfood.model.MenuItem;
import com.myfood.service.RestaurantMenuService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class TestRestaurantMenuServiceImpl {
	
	@Mock
    private RestaurantMenuDao restaurantMenuDao;
	
	@InjectMocks
    private RestaurantMenuService rmService = new RestaurantMenuServiceImpl();
	
	private List<MenuItem> menu = new ArrayList<MenuItem>();
	
	@Before
	public void setup() {
		MenuItem item1 = new MenuItem();
		item1.setItemId(101);
		item1.setItemName("Burger");
		item1.setCategory("FastFood");
		item1.setCalories(500);
		item1.setCost(8.50d);
		
		MenuItem item2 = new MenuItem();
		item2.setItemId(102);
		item2.setItemName("French Fries");
		item2.setCategory("Starter");
		item2.setCalories(750);
		item2.setCost(4.75d);
		
		menu.add(item1);
		menu.add(item2);
	}
	
	@Test
	public void testGetMenuByRestaurant(){
		// define return value for the dao method getMenuByRestaurant
		when(restaurantMenuDao.getMenuByRestaurant(any(Integer.class))).thenReturn(menu);
		
		// Execute the method being tested
		Map<String, List<MenuItem>> menuByCategory = rmService.getMenuByRestaurant(1);
				
		//Verify that DAO class is being with same parameters passed to service class
		verify(restaurantMenuDao).getMenuByRestaurant(Matchers.eq(1));
		
		assertNotNull(menuByCategory);
		assertEquals(menuByCategory.keySet().size(), 2);
		assertTrue(menuByCategory.keySet().contains("Starter"));
	}
	
}
