package com.myfood.daoImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;
import com.myfood.dao.RestaurantMenuDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-beans.xml","classpath:datasource.xml" })
public class TestRestaurantMenuDaoImpl {
	
	@Autowired
	private RestaurantMenuDao restaurantMenuDao;
	
	@Test
	public void testGetMenuByRestaurantPositiveScenario(){
		assertEquals(2, restaurantMenuDao.getMenuByRestaurant(1).size());
	}
	
	@Test
	public void testGetMenuByRestaurantNegativeScenario(){
		assertEquals(0, restaurantMenuDao.getMenuByRestaurant(-99).size());
	}

}
