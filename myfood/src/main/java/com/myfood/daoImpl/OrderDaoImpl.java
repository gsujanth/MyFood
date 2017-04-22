package com.myfood.daoImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myfood.dao.OrderDao;
import com.myfood.model.CartItem;
import com.myfood.model.Customer;
import com.myfood.model.OrderItem;
import com.myfood.model.OrderStatus;
import com.myfood.model.Restaurant;

@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		Session session;
		try{
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		return session;
    }
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public int getRecentOrderId() {
		OrderItem orderItem = (OrderItem)getSession().createQuery("FROM OrderItem order by orderId desc").setMaxResults(1)
				.uniqueResult();
		if(null == orderItem)
			return 0;
		else
			return orderItem.getOrderId();
	}
	
	public int getRecentOrderIndexId() {
		OrderItem orderItem = (OrderItem)getSession().createQuery("FROM OrderItem order by orderIndexId desc").setMaxResults(1)
				.uniqueResult();
		if(null == orderItem)
			return 0;
		else
			return orderItem.getOrderIndexId();
	}
	
	@Transactional
	public void addOrderItem(OrderItem item) {
		getSession().save(item);
	}
	
	
	/*public List<OrderItem> getAllOrders(int restaurantId) {
		List<OrderItem> ordersList=null;
		try {
			ordersList=getSession().createQuery("FROM OrderItem WHERE restaurantId=:Id and activeFlag='Y'").setParameter("Id",restaurantId).list();
			//ordersList =getSession().createQuery("select distinct orderId,customerId,restaurantId FROM OrderItem where restaurantId=:Id").setParameter("Id", restaurantId).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordersList;
	}	*/
	
	public List<OrderItem> getAllOrders(int restaurantId) {
		List<Object[]> ordersList1=null;
		List<OrderItem> ordersList=new ArrayList<OrderItem>();
		try {
			//ordersList=getSession().createQuery("FROM OrderItem WHERE restaurantId=:Id and activeFlag='Y'").setParameter("Id",restaurantId).list();
			ordersList1 =getSession().createQuery("select distinct orderId,customerId,restaurantId FROM OrderItem where restaurantId=:Id and activeFlag=:flag").setParameter("Id", restaurantId).setParameter("flag", "Y").list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Iterator iterator = ordersList1.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			OrderItem oi=new OrderItem();
			oi.setOrderId((Integer)objects[0]);
			oi.setCustomerId((Integer)objects[1]);
			oi.setRestaurantId((Integer)objects[2]);
			ordersList.add(oi);
		}
		return ordersList;
	}

	public int getCustomerIdByOrderId(int orderId) {
		System.out.println("orderId in dao--"+orderId);
		Query query = getSession().createQuery("from OrderItem where orderId=:id");
		query.setParameter("id", orderId);
		query.setMaxResults(1);
		OrderItem orderItem = (OrderItem)query.uniqueResult();
		if(orderItem == null)
			return 0;
		else
			return orderItem.getCustomerId();
	}



	public List<OrderItem> getOrderDetailsByCustomerAndOrderId(int customerId, int orderId) {
		List<Object[]> ordersList1=null;
		List<OrderItem> ordersList=new ArrayList<OrderItem>();
			ordersList1=getSession().createQuery("select distinct itemName, itemQuantity, itemCost FROM OrderItem where customerId=:Id and orderId=:orderId").setParameter("Id", customerId).setParameter("orderId", orderId).list();
			for (Iterator iterator = ordersList1.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
				OrderItem oi=new OrderItem();
				oi.setItemName((String)objects[0]);
				oi.setItemQuantity((Integer)objects[1]);
				oi.setItemCost((Double)objects[2]);
				oi.setOrderId(orderId);
				ordersList.add(oi);
			}
			return ordersList;
	}

	public void cancelOrder(int orderId) throws Exception {
		System.out.println("in dao impl--"+orderId);
		Session session = getSession();
		Transaction tx = getSession().getTransaction();
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderId(orderId);
		try{
			tx = session.beginTransaction();
			Query query = (Query) session.createQuery("update OrderItem set activeFlag=:flag where orderId=:id");
			query.setParameter("flag", "N");
			query.setParameter("id", orderId);
			query.executeUpdate();
			tx.commit();
			session.update(orderItem);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}

	public int getRestaurantIdByOrderId(int orderId) {
		System.out.println("orderId in dao--"+orderId);
		Query query = getSession().createQuery("from OrderItem where orderId=:id");
		query.setParameter("id", orderId);
		query.setMaxResults(1);
		OrderItem orderItem = (OrderItem)query.uniqueResult();
		if(orderItem == null)
			return 0;
		else
			return orderItem.getRestaurantId();
		
	}

	public void insertIntoOrderStatusTable(OrderStatus orderStatus, int orderId, String comments) {
		Session session = getSession();
		try{
			Transaction tx = session.beginTransaction();
			session.save(orderStatus);
			tx.commit();	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		System.out.println("In DAO-register-restaurant:"+orderStatus);
	}

	public void insertIntoOrderStatusOnConfirm(OrderStatus orderStatus, int orderId) {
		Session session = getSession();
		try{
			Transaction tx = session.beginTransaction();
			session.save(orderStatus);
			tx.commit();	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		System.out.println("In DAO-register-restaurant:"+orderStatus);
	}


}

//ordersList =getSession().createQuery("FROM OrderItem where ActiveFlag='Y' and RestaurantId=:Id").setParameter("Id", restaurantId).list();
