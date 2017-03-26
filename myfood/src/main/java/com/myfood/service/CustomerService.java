package com.myfood.service;

import com.myfood.model.Customer;

public interface CustomerService {
	
	public Customer fetchCustomerDataById(int customerId);
	
	public void updateCustomer(Customer customer);

}
