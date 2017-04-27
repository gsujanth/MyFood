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
	
	@RequestMapping(value="/addMenuItem", method=RequestMethod.GET)//sujanth
	public ModelAndView getMenuItemData(){
		MenuItem menuitem = new MenuItem();
		ModelAndView model = new ModelAndView("addMenuItem");
		model.addObject("menuitem", menuitem);
		return model;
	}
	
	@RequestMapping(value="/addMenuItem", method=RequestMethod.POST)//sujanth
	public ModelAndView postMenuItemData(@ModelAttribute("menuitem") MenuItem menuitem, final RedirectAttributes redirectAttributes){
		System.out.println("restaurantMenuService.getMenuByItemId(menuitem.getItemId())--"+restaurantMenuService.getMenuByItemId(menuitem.getItemId()));
		if(restaurantMenuService.getMenuByItemId(menuitem.getItemId())!=null){
			redirectAttributes.addFlashAttribute("errorMsg", "Menu Item already Added.");
		}
		else{
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
	public ModelAndView removeMenuItem(@PathVariable("itemId") int id, final RedirectAttributes redirectAttributes,HttpSession session,HttpServletRequest request) throws Exception{
		/*int resId = Integer.parseInt(request.getParameter("restaurantId"));
		session.setAttribute("restaurantId", resId);
		Map<String, List<MenuItem>> menu = restaurantMenuService.getMenuByRestaurant(resId);
		ModelAndView model = new ModelAndView("removeMenuItem");
		model.addObject("menuMap", menu);*/
		System.out.println("id of menu item in controller--"+id);
		restaurantMenuService.removeMenuItem(1);
		redirectAttributes.addFlashAttribute("SuccessMsg", "Menu Item Removed Successfully.");
		return new ModelAndView("redirect:/removeMenuItem/");
	}
	
	public RestaurantMenuService getRestaurantMenuService() {
		return restaurantMenuService;
	}

	public void setRestaurantMenuService(RestaurantMenuService restaurantMenuService) {
		this.restaurantMenuService = restaurantMenuService;
	}

}

//sujanth
