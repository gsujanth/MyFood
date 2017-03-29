package com.myfood.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myfood.dao.RestaurantDao;
import com.myfood.model.Restaurant;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-beans.xml","classpath:datasource.xml" })
public class TestRestaurantDaoImpl {

	
	@Autowired
	private RestaurantDao restaurantDao;
	
	@Test
	public void testGetRestaurantIdsByPincode(){
		List<Integer> list = restaurantDao.getRestaurantIdByPincode(28262);
		Assert.assertEquals(2, list.size());
		list = restaurantDao.getRestaurantIdByPincode(12345);
		Assert.assertEquals(0, list.size());
		list = restaurantDao.getRestaurantIdByPincode(123);
		Assert.assertEquals(0, list.size());
		//Assert.assertNull(list);
		/*for (Integer integer : list) {
			System.out.println(list);
		}*/
	}
	
	@Test
	public void testGetRestaurantsByIds(){
		//List<Integer> list2 =  restaurantDao.getRestaurantIdByPincode(28262);
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(1);
		list2.add(2);
		List<Restaurant> list = restaurantDao.getRestaurantsByIds(list2);
		/*for (Restaurant restaurant : list) {
			System.out.println(restaurant);
		}*/
		Assert.assertEquals(2, list.size());
		
		list2 = new ArrayList<Integer>();
		list2.add(1);
		list = restaurantDao.getRestaurantsByIds(list2);
		Assert.assertEquals(1, list.size());
		
		list2 = new ArrayList<Integer>();
		list = restaurantDao.getRestaurantsByIds(list2);
		Assert.assertNull(list);
	}
	
}
