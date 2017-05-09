package com.myfood.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myfood.model.MenuItem;
import com.myfood.model.Restaurant;
import com.myfood.service.RestaurantMenuService;
import com.myfood.service.RestaurantService;

@Scope("request")
@Controller
public class RestaurantMenuController {
	
	@Autowired
	private RestaurantMenuService restaurantMenuService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@RequestMapping(value="/menuList/{id}", method=RequestMethod.GET)
	public ModelAndView getRestaurantMenu(@PathVariable("id") int id){
		Map<String, List<MenuItem>> menu = restaurantMenuService.getMenuByRestaurant(id);
		ModelAndView model = new ModelAndView("restaurantMenuViewPage");
		model.addObject("menuMap", menu);
		
		return model;
	}
	
	@RequestMapping(value="/addMenuItem", method=RequestMethod.GET)//sujanth
	public ModelAndView getMenuItemData(){
		MenuItem menuitem = new MenuItem();
		ModelAndView model = new ModelAndView("addMenuItem");
		model.addObject("menuitem", menuitem);
		return model;
	}
	
	@RequestMapping(value="/addMenuItem", method=RequestMethod.POST)//sujanth
	public ModelAndView postMenuItemData(@ModelAttribute("menuitem") MenuItem menuitem, final RedirectAttributes redirectAttributes
			, HttpSession session){
		System.out.println("restaurantMenuService.getMenuByItemId(menuitem.getItemId())--"+restaurantMenuService.getMenuByItemId(menuitem.getItemId()));
		if(restaurantMenuService.getMenuByItemId(menuitem.getItemId())!=null){
			redirectAttributes.addFlashAttribute("errorMsg", "Menu Item already Added.");
		}
		else{
			//System.out.println("Restaurant ID:"+session.getAttribute("restaurantId").toString());
			menuitem.setRestaurantId(Integer.parseInt(session.getAttribute("restaurantId").toString()));
			int itemId = restaurantMenuService.postMenuItemData(menuitem);
			menuitem.setItemId(itemId);
			menuitem.setFlag(true);
			System.out.println(""+menuitem.getItemId());
			System.out.println(""+menuitem.isFlag());
			redirectAttributes.addFlashAttribute("SuccessMsg", "Menu Item Saved Successfully.");
		} 
		return new ModelAndView("redirect:/addMenuItem/");
	}
	
	@RequestMapping(value="/removeMenuItem/{itemId}", method=RequestMethod.GET)//sujanth
	public ModelAndView removeMenuItem(@PathVariable("itemId") int itemId, final RedirectAttributes redirectAttributes,HttpSession session,HttpServletRequest request) throws Exception{
		System.out.println("id of menu item in controller--"+itemId);
		restaurantMenuService.removeMenuItem(itemId);
		redirectAttributes.addFlashAttribute("SuccessMsg", "Menu Item Removed Successfully.");
		return new ModelAndView("redirect:/viewMenuItems/");
	}
	
	public RestaurantMenuService getRestaurantMenuService() {
		return restaurantMenuService;
	}

	public void setRestaurantMenuService(RestaurantMenuService restaurantMenuService) {
		this.restaurantMenuService = restaurantMenuService;
	}
	
	@RequestMapping(value="/viewMenuItems", method=RequestMethod.GET)//sujanth
	public ModelAndView getMyOrderDetails(final RedirectAttributes redirectAttributes, HttpSession session){
		int customerId=0;
		int restId=0;
		System.out.println("inside viewMenuItems controller");
		ModelAndView modelOne;
		if(session.getAttribute("customerId") != null 
				&& session.getAttribute("userRole") != null 
				&& session.getAttribute("userRole").toString().equalsIgnoreCase("restaurantowner")
				){
		customerId = (Integer) session.getAttribute("customerId");
		System.out.println("customerId--"+customerId);
		restId=restaurantService.getResIdByRestaurantOwnerId(customerId);
		System.out.println("restId--"+restId);
		List<MenuItem> resMenuItems = restaurantMenuService.getMenuItems(restId);
		System.out.println("in get--"+resMenuItems);
		modelOne = new ModelAndView("viewMenuItems");
		modelOne.addObject("resMenuItems", resMenuItems);
		}else{
			modelOne = new ModelAndView("redirect:/views/login.jsp");
		}
		return modelOne;
	}

}

