package com.myfood.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myfood.model.MenuItem;
import com.myfood.service.RestaurantMenuService;

@Scope("request")
@Controller
public class RestaurantMenuController {
	
	@Autowired
	private RestaurantMenuService restaurantMenuService;
	
	@RequestMapping(value="/menuList/{id}", method=RequestMethod.GET)
	public ModelAndView getRestaurantMenu(@PathVariable("id") int id){
		Map<String, List<MenuItem>> menu = restaurantMenuService.getMenuByRestaurant(id);
		ModelAndView model = new ModelAndView("restaurantMenuViewPage");
		model.addObject("menuMap", menu);
		
		return model;
	}
	
	public RestaurantMenuService getRestaurantMenuService() {
		return restaurantMenuService;
	}

	public void setRestaurantMenuService(RestaurantMenuService restaurantMenuService) {
		this.restaurantMenuService = restaurantMenuService;
	}

}
