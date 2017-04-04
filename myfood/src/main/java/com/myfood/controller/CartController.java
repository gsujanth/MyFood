package com.myfood.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myfood.service.CartService;

@Controller
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping(value="/addMenuItem/{itemId}", method=RequestMethod.GET)
	public ModelAndView addItem(@PathVariable("itemId") int itemId, HttpSession session){
		int customerId = (Integer) session.getAttribute("customerId");
		int restaurantId = cartService.addItemToCart(itemId, customerId);
		String redirectUrl = "redirect:/menuList/" + restaurantId; 
		return new ModelAndView(redirectUrl);
	}

	public CartService getCartService() {
		return cartService;
	}

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	    
}
