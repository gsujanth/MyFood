package com.myfood.daoImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.myfood.dao.CustomerDao;
import com.myfood.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-beans.xml","classpath:datasource.xml" })
public class TestCustomerDaoImpl {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Test
	public void testGetCustomerById(){
		Customer customer = customerDao.getCustomerById(101);
		assertNotNull(customer);
	}
	
	@Test
	@Transactional
	public void testUpdateCustomer(){
		Customer c = new Customer();
		c.setCustomerId(101);
		c.setFirstName("John");
		c.setLastName("Doe");
		c.setMobileNumber(8489768980L);
		c.setEmail("jane.doe@example.com");
		c.setAddress("230 Barton Dr.");
		c.setZipCode(12987);
		c.setPassword("abcd");
		customerDao.updateCustomer(c);
		assertEquals(customerDao.getCustomerById(101).getLastName(),"Doe");		
	}
	
}
