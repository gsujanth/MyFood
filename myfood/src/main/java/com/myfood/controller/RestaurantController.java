package com.myfood.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myfood.model.Customer;
import com.myfood.model.MenuItem;
import com.myfood.model.Restaurant;
import com.myfood.service.RestaurantService;

@Controller
@SessionAttributes("restaurant")
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
	
	@RequestMapping(value="/ViewRestaurants", method=RequestMethod.GET)
	public ModelAndView getRestaurants(){
		List<Restaurant> restaurantsList = restaurantService.getAllRestaurants();
		System.out.println("in get--"+restaurantsList);
		ModelAndView modelOne = new ModelAndView("ViewRestaurants");
		modelOne.addObject("restaurantList", restaurantsList);
		return modelOne;
	}
	
	@RequestMapping(value="/ViewRestaurants/{restaurantId}", method=RequestMethod.GET)
	public ModelAndView viewAndDeleteRestaurant(@PathVariable("restaurantId") int id, final RedirectAttributes redirectAttributes) throws Exception{
		System.out.println("id of rest in controller--"+id);
		restaurantService.deleteRestaurnt(id);
		//ModelAndView model = new ModelAndView("homeAdmin");
		redirectAttributes.addFlashAttribute("SuccessMsg", "Restaurant Deleted Successfully.");
		return new ModelAndView("redirect:/ViewRestaurants/");
	}
	
	@RequestMapping(value="/AddRestaurant", method=RequestMethod.GET)
	public ModelAndView getRestaurantData(){
		Restaurant restaurant = new Restaurant();
		ModelAndView model = new ModelAndView("AddRestaurant");
		model.addObject("restaurant", restaurant);
		return model;
	}
	
	@RequestMapping(value="/AddRestaurant", method=RequestMethod.POST)
	public ModelAndView registerRestaurant(@ModelAttribute("restaurant") Restaurant restaurant, final RedirectAttributes redirectAttributes){
		System.out.println("restaurantService.getRestaurantByName(restaurant.getRestaurantName())--"+restaurantService.getRestaurantByName(restaurant.getRestaurantName()));
		if(restaurantService.getRestaurantByName(restaurant.getRestaurantName())!=null){
			redirectAttributes.addFlashAttribute("errorMsg", "Restaurant already Registered.");
		}
		else{
			int restaurantId = restaurantService.registerRestaurant(restaurant);
			restaurant.setRestaurantId(restaurantId);
			restaurant.setFlag(true);
			System.out.println(""+restaurant.getRestaurantId());
			System.out.println(""+restaurant.isFlag());
			redirectAttributes.addFlashAttribute("SuccessMsg", "Restaurant Saved Successfully.");
		} 
		return new ModelAndView("redirect:/AddRestaurant/");
	}
	
}
