package com.myfood.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.myfood.model.Customer;
import com.myfood.model.Restaurant;
import com.myfood.service.RestaurantService;

@Controller
@SessionAttributes("userData")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	public RestaurantService getRestaurantService() {
		return restaurantService;
	}

	public void setRestaurantService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}
	
	
	@RequestMapping(value="/searchRestaurant", method=RequestMethod.POST)
	public ModelAndView getCustomerData(@RequestParam(value="zipCode") int pincodeFromForm){
		System.out.println("Load search Restaurant Page");
		System.out.println("PincodeFromForm:"+pincodeFromForm);
		/*if(pincodeFromForm != null)
			pincode = Integer.parseInt(pincodeFromForm);*/
		List<Restaurant> restaurantsList = restaurantService.getRestaurantsByPincode(pincodeFromForm);
		
		ModelAndView model = new ModelAndView("restaurantViewPage");
		model.addObject("restaurantList", restaurantsList);
		return model;
	}
	
	
}
