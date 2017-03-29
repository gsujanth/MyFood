package com.myfood.serviceImpl;

import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import com.myfood.dao.CustomerDao;
import com.myfood.model.LoginBean;
import com.myfood.service.CustomerService;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestLoginServiceImpl {
	
	@Mock
    private CustomerDao customerDao;
	
	@InjectMocks
    private CustomerService custService = new CustomerServiceImpl();
	
	LoginBean login = new LoginBean();
	
	
	@Before
	public void setup() {
		login.setUserName("suyash");
		login.setPassword("123");
	}
	
	@Test
	public void testIsValidUser(){
		// define return value for the dao method getMenuByRestaurant
		when(customerDao.isValidUser(any(String.class),any(String.class))).thenReturn(true);
		
		// Execute the method being tested
		boolean loginMap = custService.isValidCustomer("suyash", "123");
				
		//Verify that DAO class is being with same parameters passed to service class
		verify(customerDao).isValidUser(Matchers.eq("suyash"),Matchers.eq("123"));
		
		assertNotNull(loginMap);
		assertTrue(loginMap);
	}
	
}
