package com.myfood.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myfood.dao.DeliveryInfoDao;
import com.myfood.dao.FoodCartDao;
import com.myfood.dao.OrderDao;
import com.myfood.dao.PaymentInfoDao;
import com.myfood.model.CartItem;
import com.myfood.model.DeliveryInfo;
import com.myfood.model.OrderItem;
import com.myfood.model.OrderStatus;
import com.myfood.model.PaymentInfo;
import com.myfood.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	FoodCartDao foodCartDao;
	
	@Autowired
	PaymentInfoDao paymentInfoDao;
	
	@Autowired
	DeliveryInfoDao deliveryInfoDao;
	
	public DeliveryInfoDao getDeliveryInfoDao() {
		return deliveryInfoDao;
	}

	public void setDeliveryInfoDao(DeliveryInfoDao deliveryInfoDao) {
		this.deliveryInfoDao = deliveryInfoDao;
	}

	public FoodCartDao getFoodCartDao() {
		return foodCartDao;
	}

	public void setFoodCartDao(FoodCartDao foodCartDao) {
		this.foodCartDao = foodCartDao;
	}

	public PaymentInfoDao getPaymentInfoDao() {
		return paymentInfoDao;
	}

	public void setPaymentInfoDao(PaymentInfoDao paymentInfoDao) {
		this.paymentInfoDao = paymentInfoDao;
	}

	public boolean completeTransaction(String cardNumber, int cvv, int expiryMonth, int expiryYear, String cardName, double totalAmount){
		return true;
	}
	
	public int convertCartToOrder(List<CartItem> cartItems){
		
		int orderId = 0;
		int orderIndexId = 0;
		orderId = orderDao.getRecentOrderId();
		orderIndexId = orderDao.getRecentOrderIndexId();
		int i = 1;
		
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		OrderItem orderItem = new OrderItem();
		for (CartItem cartItem : cartItems) {
			orderItem = new OrderItem();
			orderItem.setOrderIndexId(orderIndexId+i);
			i++;
			orderItem.setOrderId(orderId+1);
			orderItem.setCustomerId(cartItem.getCartPK().getCustomerId());
			orderItem.setItemId(cartItem.getItemId());
			orderItem.setRestaurantId(cartItem.getRestaurantId());
			orderItem.setItemName(cartItem.getItemName());
			orderItem.setItemQuantity(cartItem.getItemQuantity());
			orderItem.setItemCost(cartItem.getItemCost());
			orderItem.setActiveFlag("Y");
			
			cartItem.setActiveFlag("N");
			
			orderItems.add(orderItem);
		}
		
		try{
			for (OrderItem orderItem2 : orderItems) {
				orderDao.addOrderItem(orderItem2);
			}
			for (CartItem cartItem : cartItems) {
				foodCartDao.updateCartItem(cartItem);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		
		return orderId+1;//orderId
	}
	
	public int storePaymentInfo(String cardNumber, String cardName, int orderId, double totalAmount){
		PaymentInfo paymentInfo = new PaymentInfo();
		String newCardNumber = cardNumber.substring(0,4)+"-xxxx-xxxx-"+cardNumber.substring(12,16);
		
		int latestPaymentInfoId = 0;
		latestPaymentInfoId = paymentInfoDao.getRecentPaymentInfoId();
		
		paymentInfo.setPaymentInfoId(latestPaymentInfoId+1);
		paymentInfo.setOrderId(orderId);
		
		paymentInfo.setCardNumber(newCardNumber);
		paymentInfo.setCardName(cardName);
		paymentInfo.setAmount(totalAmount);
		
		paymentInfoDao.addPaymentInfo(paymentInfo);
		
		return latestPaymentInfoId+1;
	}
	
	public int storeDeliveryInfo(int orderId, String customerName, long mobileNumber, String address, int pincode){
		DeliveryInfo deliveryInfo = new DeliveryInfo();
		
		int latestDeliveryInfoId = 0;
		latestDeliveryInfoId = deliveryInfoDao.getRecentDeliveryInfoId();
		
		deliveryInfo.setDeliveryInfoId(latestDeliveryInfoId+1);
		deliveryInfo.setOrderId(orderId);
		deliveryInfo.setCustomerName(customerName);
		deliveryInfo.setMobileNumber(mobileNumber);
		deliveryInfo.setAddress(address);
		deliveryInfo.setPincode(pincode);
		
		System.out.println("DeliveryInfo Id: "+latestDeliveryInfoId+1);
		
		deliveryInfoDao.addDeliveryInfo(deliveryInfo);
		
		return latestDeliveryInfoId+1;
		
	}
	
	
	/*public List<OrderItem> getAllOrders(int restaurantId) {
		List<OrderItem> ordersList = orderDao.getAllOrders(restaurantId);
		if (ordersList == null || ordersList.size() == 0) {
			return null;
		} else {
			ordersList = orderDao.getAllOrders(restaurantId);
		}
		return ordersList;
	}*/
	
	public List<OrderItem> getAllOrders(int restaurantId) {
		List<OrderItem> ordersList = orderDao.getAllOrders(restaurantId);
		if (ordersList == null || ordersList.size() == 0) {
			return null;
		} else {
			ordersList = orderDao.getAllOrders(restaurantId);
		}
		return ordersList;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public int getCustomerIdByOrderId(int orderId) {
		return orderDao.getCustomerIdByOrderId(orderId);
	}

	public List<OrderItem> getOrderDetailsByCustomerAndOrderId(int customerId, int orderId) {
		return orderDao.getOrderDetailsByCustomerAndOrderId(customerId, orderId);
	}
	
	public double getTotalCostOfItems(List<OrderItem> orderItems){
		double totalCost = 0;
		if(orderItems == null || orderItems.size() == 0)
			return totalCost; //returns cost as 0.
		for (OrderItem orderItem : orderItems) {
			totalCost = totalCost + orderItem.getItemCost(); 
		}
		return totalCost;
	}

	public void cancelOrder(int orderId) throws Exception {
		System.out.println("inside service cancel order");
		orderDao.cancelOrder(orderId);
		
	}

	public int getRestaurantIdByOrderId(int orderId) {
		return orderDao.getRestaurantIdByOrderId(orderId);
	}

	public void insertIntoOrderStatusTable(OrderStatus orderStatus, int orderId, String comments) {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		orderStatus.setOrderId(orderId);
		orderStatus.setComments(comments);
		orderStatus.setCreatedOn(timeStamp);
		orderStatus.setRestaurantId(getRestaurantIdByOrderId(orderId));
		orderStatus.setStatus("Cancelled");
		orderDao.insertIntoOrderStatusTable(orderStatus, orderId, comments);
	}

	public void insertIntoOrderStatusOnConfirm(OrderStatus orderStatus, int orderId) {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		orderStatus.setOrderId(orderId);
		orderStatus.setComments("NA");
		orderStatus.setCreatedOn(timeStamp);
		orderStatus.setRestaurantId(getRestaurantIdByOrderId(orderId));
		orderStatus.setStatus("Confirmed");
		orderDao.insertIntoOrderStatusOnConfirm(orderStatus, orderId);	
	}
	
	public List<OrderStatus> getConfirmedOrdersByRestaurant(int restaurantId){
		return orderDao.getConfirmedOrdersByRestaurant(restaurantId);
	}
	
	public List<String> getOrderStatusList(){
		return orderDao.getOrderStatusList();
	}
	
	@Transactional
	public void updateOrderStatus(int orderId, String status){
		orderDao.updateOrderStatus(orderId, status);
	}
		
}
