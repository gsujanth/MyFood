package com.myfood.daoImpl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myfood.dao.OrderDao;
import com.myfood.model.OrderStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-beans.xml","classpath:datasource.xml" })
public class TestOrderDaoImpl {
	
	@Autowired
	private OrderDao orderDao;

	@Test
	public void testGetActiveCartByCustomerId(){
		
		int n = orderDao.getRecentOrderId();
		System.out.println(n);
	}
	
	@Test
	public void testGetOrderStatusList(){
		assertEquals(orderDao.getOrderStatusList().size(),5);
	}
	
	@Test
	public void testGetConfirmedOrdersByRestaurant(){
		List<OrderStatus> orderStatus = orderDao.getConfirmedOrdersByRestaurant(2);
		assertEquals(orderStatus.size(),1);
	}
}
