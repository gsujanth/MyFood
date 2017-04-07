package com.myfood.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myfood.model.Customer;
import com.myfood.model.MenuItem;
import com.myfood.service.CustomerService;
import com.myfood.service.RestaurantMenuService;

@Controller
@SessionAttributes("customer")
public class RegisterController {


	@Autowired
	private CustomerService customerService;

	@RequestMapping(value="/RegistrationPage", method=RequestMethod.GET)
	public ModelAndView getCustomerData(){
		Customer customer = new Customer();
		ModelAndView model = new ModelAndView("register");
		model.addObject("customer", customer);
		return model;
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView registerCustomer(@ModelAttribute("customer") Customer customer, final RedirectAttributes redirectAttributes){
		System.out.println(customer);
		ModelAndView model = new ModelAndView("homeUser");
		if(customerService.getCustomerByEmail(customer.getEmail()) != null){
			redirectAttributes.addFlashAttribute("errorMsg", "Email Id already Registered.");
			model = new ModelAndView("redirect:/RegistrationPage/");
		}else{
			String errorMessage = validateCustomer(customer);
			if(errorMessage.length() == 0){	
				int customerId = customerService.registerCustomer(customer);
				customer.setCustomerId(customerId);
				System.out.println("CustomerId:"+customer.getCustomerId());
				redirectAttributes.addFlashAttribute("SuccessMsg", "Profile Saved Successfully.");
			}else{
				redirectAttributes.addFlashAttribute("errorMsg", errorMessage);
				model = new ModelAndView("redirect:/RegistrationPage/");
			}
		}

		//String redirectUrl = "redirect:/getUserProfile/" + customer.getCustomerId();
		//ModelAndView model = new ModelAndView("home");
		//model.addObject("customer", customer);
		return model;
	}
	
	private String validateCustomer(Customer customer){
		String errorMessage = "";
		if(customer.getEmail() == null || customer.getEmail().trim().length() == 0)
			errorMessage += "EmailId Missing.";
		if(customer.getFirstName() == null || customer.getFirstName().trim().length() == 0)
			errorMessage += "First Name Missing.";
		if(customer.getLastName() == null || customer.getLastName().trim().length() == 0)
			errorMessage += "Last Name Missing.";
		if(customer.getMobileNumber() == 0 || Long.toString(customer.getMobileNumber()).length() != 10)
			errorMessage += "Mobile Number Missing or invalid.";
		if(customer.getAddress() == null || customer.getAddress().trim().length() == 0)
			errorMessage += "Address Missing.";
		if(customer.getZipCode() == 0 || Long.toString(customer.getZipCode()).length() != 5)
			errorMessage += "Zipcode Missing or Invalid.";
		if(customer.getPassword() == null || customer.getPassword().trim().length() == 0)
			errorMessage += "Password Missing.";
		
		return errorMessage;
	}


	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}



}
