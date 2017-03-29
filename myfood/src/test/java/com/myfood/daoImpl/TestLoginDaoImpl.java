package com.myfood.daoImpl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myfood.dao.CustomerDao;
import com.myfood.model.LoginBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-beans.xml","classpath:datasource.xml" })

public class TestLoginDaoImpl {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Test
	public void testLoginDao(){
		
		LoginBean login = new LoginBean();
		login.setUserName("suyash");
		login.setPassword("123456");

		//Asserting if the method is returning true after matching username and password
		Assert.assertFalse(customerDao.isValidUser(login.getUserName(),login.getPassword()));
		
		
		login = new LoginBean();
		login.setUserName("test");
		login.setPassword("123");

		//Asserting if the method is returning true after matching username and password
		Assert.assertTrue(customerDao.isValidUser(login.getUserName(),login.getPassword()));
		return;
	}
	
	
}
