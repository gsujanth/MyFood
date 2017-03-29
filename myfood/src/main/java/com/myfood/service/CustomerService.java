package com.myfood.service;

import com.myfood.model.Customer;

public interface CustomerService {
	
	public Customer fetchCustomerDataById(int customerId);
	
	public void updateCustomer(Customer customer);
	public int getLastCustomerId();
	public int registerCustomer(Customer customer);
	public boolean isValidCustomer(String username, String password);
	
	public Customer getCustomerByEmail(String email);
	
}
