package com.myfood.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.myfood.dao.CustomerDao;
import com.myfood.model.Customer;
import com.myfood.service.CustomerService;

@RunWith(MockitoJUnitRunner.class)
public class TestCustomerServiceImpl {
	
	@Mock
    private CustomerDao customerDao;
	
	@InjectMocks
    private CustomerService customerService = new CustomerServiceImpl();
	
	private Customer c = new Customer();
	
	@Before
	public void setup() {
		c.setCustomerId(101);
		c.setFirstName("John");
		c.setLastName("Doe");
		c.setEmail("jane.doe@example.com");
		c.setZipCode(27289);
	}
	
	@Test
	public void testFetchCustomerDataById(){
		// define return value for the dao method getMenuByRestaurant
		when(customerDao.getCustomerById(any(Integer.class))).thenReturn(c);
				
		// Execute the method being tested
		Customer cTest = customerService.fetchCustomerDataById(101);
		
		assertNotNull(cTest);
		assertEquals(cTest, c);
	}
	
	@Test
	public void testUpdateCustomer(){
		customerService.updateCustomer(c);
		//verify if the customerDao method updateCustomer was called with the customer object c
		verify(customerDao, times(1)).updateCustomer(c);
	}
	
	@Test
	public void testRegisterCustomer(){
		when(customerDao.registerCustomer(any(Customer.class))).thenReturn(101);
		int custId = customerService.registerCustomer(c);
		verify(customerDao).registerCustomer(Matchers.eq(c));
		
		assertNotNull(custId);
		assertEquals(101, custId);
	}

}
