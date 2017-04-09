package com.myfood.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myfood.model.CartItem;
import com.myfood.model.Customer;
import com.myfood.service.CartService;
import com.myfood.service.CustomerService;

@Controller
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/addMenuItem/{itemId}", method=RequestMethod.GET)
	public ModelAndView addItem(@PathVariable("itemId") int itemId, HttpSession session){
		int customerId = (Integer) session.getAttribute("customerId");
		int restaurantId = cartService.addItemToCart(itemId, customerId);
		String redirectUrl = "redirect:/menuList/" + restaurantId; 
		return new ModelAndView(redirectUrl);
	}
	
	@RequestMapping(value="/cart/checkOut", method=RequestMethod.GET)
	public ModelAndView checkOut(HttpSession session){
		if(session.getAttribute("customerId") == null){
			ModelAndView model = new ModelAndView("login");
			return model;
		}
		int customerId = (Integer) session.getAttribute("customerId");
		Customer customer = customerService.fetchCustomerDataById(customerId);
		List<CartItem> cartItems = cartService.getActiveCustomerCartByCustomerId(customerId);
		double totalItemsCost = cartService.getTotalItemsCost(cartItems);
		
		ModelAndView model = new ModelAndView("paymentPage");
		model.addObject("customerDetails", customer);
		model.addObject("cartItems", cartItems);
		model.addObject("cartSize", cartItems.size());
		model.addObject("totalItemsCost", totalItemsCost);
		return model;
	}
	

	public CartService getCartService() {
		return cartService;
	}

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	    
}
