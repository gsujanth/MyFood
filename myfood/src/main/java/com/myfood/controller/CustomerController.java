package com.myfood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerController {
	
	@RequestMapping(value="/editUserProfile/{userId}", method=RequestMethod.GET)
	public ModelAndView getRestaurantMenu(@PathVariable("userId") int userId){
		System.out.println("User id received "+ userId);
		ModelAndView model = new ModelAndView("editProfile");
		return model;
	}

}
