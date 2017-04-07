package com.myfood.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
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
		Assert.assertEquals(2, list.size());
		
		list2 = new ArrayList<Integer>();
		list2.add(1);
		list = restaurantDao.getRestaurantsByIds(list2);
		Assert.assertEquals(1, list.size());
		
		list2 = new ArrayList<Integer>();
		list = restaurantDao.getRestaurantsByIds(list2);
		Assert.assertNull(list);
	}
	
	@Test
	public void testGetAllRestaurants(){
		
		List<Restaurant> restaurantList= new ArrayList<Restaurant>();
		
		Restaurant restaurant1 = new Restaurant();
		restaurant1.setRestaurantId(1);
		restaurant1.setRestaurantName("PATHWAY TO INDIA");
		restaurant1.setAddress("9547, UT Drive, Charlotte, NC");
		restaurant1.setPincode(28262);
		restaurant1.setCuisine("Indian");
		restaurant1.setFlag(true);
		
		Restaurant restaurant2 = new Restaurant();
		restaurant2.setRestaurantId(1);
		restaurant2.setRestaurantName("PATHWAY TO INDIA");
		restaurant2.setAddress("9547, UT Drive, Charlotte, NC");
		restaurant2.setPincode(28262);
		restaurant2.setCuisine("Indian");
		restaurant2.setFlag(true);
		
		restaurantList.add(restaurant1);
		restaurantList.add(restaurant2);
		
		Assert.assertEquals(2,restaurantList.size());
		Assert.assertNotNull(restaurantList);
	}
	
	@Test
	public void testRegisterRestaurant(){
		
		Restaurant restaurant = new Restaurant();
		
		restaurant.setRestaurantId(105);
		restaurant.setRestaurantName("PATHWAY TO INDIA");
		restaurant.setAddress("9547, UT Drive, Charlotte, NC");
		restaurant.setPincode(28262);
		restaurant.setCuisine("Indian");
		restaurant.setFlag(true);
		restaurantDao.registerRestaurant(restaurant);
		
		Assert.assertEquals(105, restaurant.getRestaurantId());
		Assert.assertEquals("PATHWAY TO INDIA", restaurant.getRestaurantName());
		Assert.assertEquals("9547, UT Drive, Charlotte, NC", restaurant.getAddress());
		Assert.assertEquals(28262, restaurant.getPincode());
		Assert.assertEquals("Indian", restaurant.getCuisine());
		Assert.assertTrue(restaurant.isFlag());
		return;
	}
	
	@Test
	public void testGetLastRestaurantById(){
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantId(105);
		Assert.assertEquals(105, restaurant.getRestaurantId());
	}
	
	@Test
	public void testGetRestaurantByName(){
		
		Restaurant restaurant = restaurantDao.getRestaurantByName("PATHWAY TO INDIA");
		Assert.assertEquals(101, restaurant.getRestaurantId());
		Assert.assertEquals("PATHWAY TO INDIA", restaurant.getRestaurantName());
		Assert.assertEquals("9547, UT Drive, Charlotte, NC", restaurant.getAddress());
		Assert.assertEquals(28262, restaurant.getPincode());
		Assert.assertEquals("Indian", restaurant.getCuisine());
		Assert.assertTrue(restaurant.isFlag());
	}
	
	@Test
	public void testDeleteRestaurant() throws Exception{
		restaurantDao.deleteRestaurant(20);
		Restaurant restaurant = new Restaurant();
		restaurant.setFlag(false);
		Assert.assertFalse(restaurant.isFlag());
		
	}
	
}
