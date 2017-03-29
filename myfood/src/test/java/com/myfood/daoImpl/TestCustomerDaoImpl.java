package com.myfood.daoImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
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
	
	@Test
	public void testRegisterCustomer(){
		
		Customer cust = new Customer();
		
		cust.setCustomerId(2);
		cust.setFirstName("A");
		cust.setLastName("B");
		cust.setEmail("abc@gmail.com");
		cust.setMobileNumber(1234567890);
		cust.setAddress("test");
		cust.setPassword("abc");
		cust.setZipCode(12345);
		customerDao.registerCustomer(cust);
		
		Assert.assertEquals(2, cust.getCustomerId());
		Assert.assertEquals("A", cust.getFirstName());
		Assert.assertEquals("B", cust.getLastName());
		Assert.assertEquals("abc@gmail.com", cust.getEmail());
		Assert.assertEquals(1234567890, cust.getMobileNumber());
		Assert.assertEquals("test", cust.getAddress());
		Assert.assertEquals(12345, cust.getZipCode());
		//System.out.println("testRegisterCustomer");
		return;
	}
	
	
	
	@Test
	public void testGetCustomerByEmail(){
		
		Customer cust = customerDao.getCustomerByEmail("abc@gmail.com");
		Assert.assertEquals(2, cust.getCustomerId());
		Assert.assertEquals("A", cust.getFirstName());
		Assert.assertEquals("B", cust.getLastName());
		Assert.assertEquals("abc@gmail.com", cust.getEmail());
		Assert.assertEquals(1234567890, cust.getMobileNumber());
		Assert.assertEquals("test", cust.getAddress());
		Assert.assertEquals(12345, cust.getZipCode());
		//System.out.println("testGetCustomerByEmail");
		return;
	}
	
}
