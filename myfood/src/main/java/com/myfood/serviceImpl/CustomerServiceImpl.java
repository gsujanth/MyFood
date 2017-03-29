package com.myfood.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myfood.dao.CustomerDao;
import com.myfood.model.Customer;
import com.myfood.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDao customerDao;

	@Transactional
	public Customer fetchCustomerDataById(int customerId) {
		return customerDao.getCustomerById(customerId);
	}
	
	@Transactional
	public void updateCustomer(Customer customer) {
		customerDao.updateCustomer(customer);
	}

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	public int getLastCustomerId() {
		return customerDao.getLastCustomerId();
	}

	public int registerCustomer(Customer customer) {
		
		customer.setCustomerId((getLastCustomerId()+1));
		System.out.println("In service setting Customer Id:"+customer.getCustomerId());
		System.out.println("In service before Registering:");
		System.out.println(customer);
		System.out.println("---------------------------");
		return customerDao.registerCustomer(customer);
	}
	
	public boolean isValidCustomer(String username, String password)
	{
			return customerDao.isValidUser(username, password);
	}
	
	public Customer getCustomerByEmail(String email){
		return customerDao.getCustomerByEmail(email);
	}
	
}
