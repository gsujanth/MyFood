package com.myfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myfood.service.CustomerService;

@Controller
@SessionAttributes("customerId")
public class LoginController {
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ModelAndView navigateToHome(){
		return new ModelAndView("home");
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView validateLogin(@RequestParam(value="email") String email, @RequestParam(value="password") String password, final RedirectAttributes redirectAttributes){
		System.out.println("Login Page Submit");
		System.out.println("Email :"+email+" ; Password:"+password);
		boolean isValidUser = customerService.isValidCustomer(email, password);
		ModelAndView model;
		String redirect = "";
		if(isValidUser){
			redirect = "home";
			model = new ModelAndView(redirect);
			model.addObject("customerId", customerService.getCustomerByEmail(email).getCustomerId());
		}
		else{
			redirect = "redirect:/views/login.jsp";
			model = new ModelAndView(redirect);
			model.addObject("errorMsg", "Login Failed.");
		}
		//redirectAttributes.addFlashAttribute("errorMsg", "Login Failed.");
		return model;
	}
	
}
