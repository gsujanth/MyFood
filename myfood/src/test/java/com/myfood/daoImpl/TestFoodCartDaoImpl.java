package com.myfood.daoImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myfood.dao.FoodCartDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-beans.xml","classpath:datasource.xml" })
public class TestFoodCartDaoImpl {
	
	@Autowired
	private FoodCartDao foodCartDao;
	
	@Test
	public void testGetRecentCartId(){
		System.out.println(foodCartDao.getRecentCartId());
	}

}
