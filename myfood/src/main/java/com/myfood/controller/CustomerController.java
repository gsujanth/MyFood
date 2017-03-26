package com.myfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myfood.model.Customer;
import com.myfood.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/getUserProfile/{userId}", method=RequestMethod.GET)
	public ModelAndView getCustomerData(@PathVariable("userId") int userId){
		Customer customer = customerService.fetchCustomerDataById(userId);
		ModelAndView model = new ModelAndView("editProfile");
		model.addObject("customer", customer);
		return model;
	}
	
	@RequestMapping(value="/editUserProfile", method=RequestMethod.POST)
	public ModelAndView saveCustomerData(@ModelAttribute("customer") Customer customer, final RedirectAttributes redirectAttributes){
		System.out.println(customer.getCustomerId());
		customerService.updateCustomer(customer);
		redirectAttributes.addFlashAttribute("SuccessMsg", "Profile Saved Successfully.");
		String redirectUrl = "redirect:/getUserProfile/" + customer.getCustomerId(); 
		return new ModelAndView(redirectUrl);
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

}
