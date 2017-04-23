package com.myfood.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myfood.model.CartItem;
import com.myfood.model.Customer;
import com.myfood.model.OrderItem;
import com.myfood.model.OrderStatus;
import com.myfood.service.CartService;
import com.myfood.service.CustomerService;
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
	
	@Autowired
	private CustomerService customerService;
	
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
		System.out.println("inside viewOrders controller");
		ModelAndView modelOne;
		if(session.getAttribute("customerId") != null 
				&& session.getAttribute("userRole") != null 
				&& session.getAttribute("userRole").toString().equalsIgnoreCase("restaurantowner")
				){
		customerId = (Integer) session.getAttribute("customerId");
		System.out.println("customerId--"+customerId);
		restId=restaurantService.getResIdByRestaurantOwnerId(customerId);
		System.out.println("restId--"+restId);
		List<OrderItem> ordersList = orderService.getAllOrders(restId);
		System.out.println("in get--"+ordersList);
		modelOne = new ModelAndView("viewOrders");
		modelOne.addObject("ordersList", ordersList);
		}else{
			modelOne = new ModelAndView("redirect:/views/login.jsp");
		}
		return modelOne;
	}
	
	@RequestMapping(value="/orderConfirmation/{orderId}", method=RequestMethod.GET)
	public ModelAndView confirmOrCancelOrderNew(HttpSession session, @PathVariable("orderId") int id){
		ModelAndView model;
		if(session.getAttribute("customerId") != null 
				&& session.getAttribute("userRole") != null 
				&& session.getAttribute("userRole").toString().equalsIgnoreCase("restaurantowner")
				){
			int customerId = orderService.getCustomerIdByOrderId(id);
			System.out.println("customerId in contoller--"+customerId);
			Customer customer = customerService.fetchCustomerDataById(customerId);
			List<OrderItem> orderItem = orderService.getOrderDetailsByCustomerAndOrderId(customerId, id);
			double totalItemsCost = orderService.getTotalCostOfItems(orderItem);
					
			model = new ModelAndView("orderConfirmation");
			model.addObject("customerDetails", customer);
			model.addObject("cartItems", orderItem);
			model.addObject("cartSize", orderItem.size());
			model.addObject("totalItemsCost", totalItemsCost);
			model.addObject("orderId",id);
		}else{
			model = new ModelAndView("redirect:/views/login.jsp");
		}
		return model;
	}
	
	@RequestMapping(value="/cancelOrder/{orderId}/{comments}", method=RequestMethod.GET)
	public ModelAndView cancelOrder(HttpSession session, @PathVariable("orderId") int id, @PathVariable("comments") String comments) throws Exception{
		ModelAndView model;
		if(session.getAttribute("customerId") != null 
				&& session.getAttribute("userRole") != null 
				&& session.getAttribute("userRole").toString().equalsIgnoreCase("restaurantowner")
				){
			
			//cancel the order
			orderService.cancelOrder(id);
			
			//insert values into order status table
			OrderStatus orderStatus = new OrderStatus();
			orderService.insertIntoOrderStatusTable(orderStatus, id, comments);
			System.out.println("successfully inserted into orderstatus..");
			
			int customerId = orderService.getCustomerIdByOrderId(id);
			Customer customer = customerService.fetchCustomerDataById(customerId);
			List<OrderItem> orderItem = orderService.getOrderDetailsByCustomerAndOrderId(customerId, id);
			double totalItemsCost = orderService.getTotalCostOfItems(orderItem);
					
			model = new ModelAndView("cancelOrder");
			model.addObject("customerDetails", customer);
			model.addObject("cartItems", orderItem);
			model.addObject("cartSize", orderItem.size());
			model.addObject("totalItemsCost", totalItemsCost);
			model.addObject("orderId",id);
			model.addObject("comments", comments);
		}else{
			model = new ModelAndView("redirect:/views/login.jsp");
		}
		return model;
	}
	
	@RequestMapping(value="/confirmOrder/{orderId}/{hh}/{mm}", method=RequestMethod.GET)
	public ModelAndView confirmOrder(HttpSession session, @PathVariable("orderId") int id, @PathVariable("hh") String hh, @PathVariable("mm") String mm) throws Exception{
		ModelAndView model;
		if(session.getAttribute("customerId") != null 
				&& session.getAttribute("userRole") != null 
				&& session.getAttribute("userRole").toString().equalsIgnoreCase("restaurantowner")
				){
			
			//insert values into order status table
			OrderStatus orderStatus = new OrderStatus();
			orderService.insertIntoOrderStatusOnConfirm(orderStatus, id);
			System.out.println("successfully inserted into orderstatus..");
			
			int customerId = orderService.getCustomerIdByOrderId(id);
			Customer customer = customerService.fetchCustomerDataById(customerId);
			List<OrderItem> orderItem = orderService.getOrderDetailsByCustomerAndOrderId(customerId, id);
			double totalItemsCost = orderService.getTotalCostOfItems(orderItem);
					
			model = new ModelAndView("confirmOrder");
			model.addObject("customerDetails", customer);
			model.addObject("cartItems", orderItem);
			model.addObject("cartSize", orderItem.size());
			model.addObject("totalItemsCost", totalItemsCost);
			model.addObject("orderId",id);
			model.addObject("hh", hh);
			model.addObject("mm",mm);
		}else{
			model = new ModelAndView("redirect:/views/login.jsp");
		}
		return model;
	}

	@RequestMapping(value="/viewOrders", method=RequestMethod.POST)
	public ModelAndView returnToViewOrders(HttpSession session) throws Exception{
		ModelAndView model;
		if(session.getAttribute("customerId") != null 
				&& session.getAttribute("userRole") != null 
				&& session.getAttribute("userRole").toString().equalsIgnoreCase("restaurantowner")
				){
			System.out.println("inside controller");
			model = new ModelAndView("viewOrders");
		}else{
			model = new ModelAndView("redirect:/views/login.jsp");
		}
		return model;
	}
	
	@RequestMapping(value="/viewConfirmedOrders", method=RequestMethod.GET)
	public ModelAndView getConfirmedOrders(HttpSession session){
		int customerId = (Integer) session.getAttribute("customerId");
		int restaurantId=restaurantService.getResIdByRestaurantOwnerId(customerId);
		List<OrderStatus> confirmedOrders = orderService.getConfirmedOrdersByRestaurant(restaurantId);
		ModelAndView model = new ModelAndView("ConfirmedOrdersPage");
		model.addObject("confirmedOrders", confirmedOrders);
		model.addObject("orderStatusList", orderService.getOrderStatusList());
		session.setAttribute("restaurantId", restaurantId);
		return model;
	}
	
	@RequestMapping(value="/updateStatus/{orderId}/{status}", method=RequestMethod.GET)
	public ModelAndView updateStatus(@PathVariable("orderId") int orderId, @PathVariable("status") String status, 
			final RedirectAttributes redirectAttributes, HttpSession session){
		orderService.updateOrderStatus(orderId, status);
		String redirectUrl = "redirect:/viewConfirmedOrders"; 
		return new ModelAndView(redirectUrl);
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
