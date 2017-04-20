package com.myfood.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myfood.model.CartItem;
import com.myfood.model.OrderItem;
import com.myfood.model.Restaurant;
import com.myfood.service.CartService;
import com.myfood.service.OrderService;
import com.myfood.service.RestaurantService;

@Controller
public class OrderController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@RequestMapping(value="/paymentConfirmation", method=RequestMethod.POST)
	public ModelAndView paymentConfirmation(HttpSession session,
			@RequestParam(value="cardNumber") String cardNumber,
			@RequestParam(value="cVV") int cvv,
			@RequestParam(value="expiryMonth") int expiryMonth,
			@RequestParam(value="expiryYear") int expiryYear,
			@RequestParam(value="cardName") String cardName,
			@RequestParam(value="customerName") String customerName ,
			@RequestParam(value="mobileNumber") long mobileNumber ,
			@RequestParam(value="address") String address ,
			@RequestParam(value="zipCode") int pincodeFromForm,
			@RequestParam(value="totalAmount") double totalAmount
			){
		System.out.println(cardNumber+"|"+cvv+"|"+expiryMonth+"|"+expiryYear+"|"+cardName+"|"+customerName+"|"+mobileNumber+"|"+address+"|"+pincodeFromForm+"|"+totalAmount);
		
		if(session.getAttribute("customerId") == null){
			ModelAndView model = new ModelAndView("login");
			return model;
		}
		int customerId = (Integer) session.getAttribute("customerId");
		
		double totalCostFromDB = 0;
		List<CartItem> cartItems = cartService.getActiveCustomerCartByCustomerId(customerId);
		for (CartItem cartItem : cartItems) {
			totalCostFromDB = totalCostFromDB + cartItem.getItemCost();
		}
		if(totalAmount != totalCostFromDB){
			
			System.out.println("Invalid Transaction");
			ModelAndView model = new ModelAndView("home");
			model.addObject("paymentFailed", "Invalid Transaction");
			return model;
		}
		
		boolean isTransactionComplete = orderService.completeTransaction(cardNumber, cvv, expiryMonth, expiryYear, cardName,totalAmount);
		
		if(isTransactionComplete){
			int orderId = orderService.convertCartToOrder(cartItems);
			int paymentInfoId = orderService.storePaymentInfo(cardNumber, cardName, orderId, totalAmount);
			int deliveryInfoId = orderService.storeDeliveryInfo(orderId, customerName, mobileNumber, address, pincodeFromForm);
			
			if(orderId != 0){
				ModelAndView model = new ModelAndView("paymentConfirmation");
				model.addObject("cartItems", cartItems);
				model.addObject("cartSize", cartItems.size());
				model.addObject("totalItemsCost", totalAmount);
				model.addObject("OrderConfirmationId", orderId);
				model.addObject("PaymentConfirmationId", paymentInfoId);
				return model;
			}
		}
		
		System.out.println("Payment failed. please try again.");
		ModelAndView model = new ModelAndView("home");
		model.addObject("paymentFailed", "Payment Failed. Please try again");
		return null;
	}
	
	@RequestMapping(value="/viewOrders", method=RequestMethod.GET)
	public ModelAndView getOrders(HttpSession session){
		int customerId=0;
		int restId=0;
		ModelAndView modelOne;
		if(session.getAttribute("customerId") != null 
				&& session.getAttribute("userRole") != null 
				&& session.getAttribute("userRole").toString().equalsIgnoreCase("restaurantowner")
				){
		customerId = (Integer) session.getAttribute("customerId");
		System.out.println("customerId--"+customerId);
		restId=restaurantService.getResIdByRestaurantOwnerId(customerId);
		System.out.println("restId--"+restId);
		//List<OrderItem> ordersList = orderService.getAllOrders(restId);
		List<OrderItem> ordersList = orderService.getAllOrders(restId);
		System.out.println("in get--"+ordersList);
		modelOne = new ModelAndView("viewOrders");
		modelOne.addObject("ordersList", ordersList);
		}else{
			modelOne = new ModelAndView("redirect:/views/login.jsp");
		}
		return modelOne;
	} 

	public CartService getCartService() {
		return cartService;
	}

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

}
