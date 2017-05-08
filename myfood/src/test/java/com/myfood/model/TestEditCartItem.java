package com.myfood.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestEditCartItem {
	
	private EditCartItem cartItem = new EditCartItem();
	
	@Before
	public void setup() {
		cartItem.setItemId(5);
		cartItem.setItemQunatity(3);
		cartItem.setUpdatedPrice(27d);
	}
	
	@Test
	public void testGetItemId(){
		assertEquals(5, cartItem.getItemId());
	}
	
	@Test
	public void testGetItemQunatity(){
		assertEquals(3, cartItem.getItemQunatity());
	}
	
	@Test
	public void testGetUpdatedPrice(){
		assertEquals(27d, cartItem.getUpdatedPrice(), 0d);
	}

}
