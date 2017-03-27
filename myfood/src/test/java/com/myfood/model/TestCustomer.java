package com.myfood.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestCustomer {
	
	private Customer c = new Customer();
	
	@Before
	public void setup() {
		c.setCustomerId(101);
		c.setFirstName("John");
		c.setLastName("Doe");
		c.setEmail("jane.doe@example.com");
		c.setMobileNumber(8489768980L);
		c.setAddress("230 Barton Dr.");
		c.setZipCode(27289);
	}
	
	@Test
	public void testGetCustomerId(){
		assertEquals(c.getCustomerId(), 101);
	}
	
	@Test
	public void testGetFirstName(){
		assertEquals(c.getFirstName(),"John");
	}
	
	@Test
	public void testGetLastName(){
		assertEquals(c.getLastName(), "Doe");
	}
	
	@Test
	public void testGetMobileNumber(){
		assertEquals(c.getMobileNumber(), 8489768980L);
	}
	
	@Test
	public void testGetEmail(){
		assertEquals(c.getEmail(), "jane.doe@example.com");
	}
	
	@Test
	public void testGetAddress(){
		assertEquals(c.getAddress(), "230 Barton Dr.");
	}
	
	@Test
	public void testGetZipCode(){
		assertEquals(c.getZipCode(), 27289);
	}

}