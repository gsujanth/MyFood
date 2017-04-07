package com.myfood.dao;

import com.myfood.model.Customer;

public interface CustomerDao {
	
	public Customer getCustomerById(int customerId);
	public int getLastCustomerId();
	public int registerCustomer(Customer customer);
	public boolean isValidUser(String username, String password);
	public void updateCustomer(Customer customer);
	public Customer getCustomerByEmail(String email);
	
}
