package com.myfood.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myfood.dao.OrderDao;
import com.myfood.model.OrderItem;
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
	
	
	public List<OrderItem> getAllOrders(int restaurantId) {
		List<OrderItem> ordersList=null;
		try {
			ordersList=getSession().createQuery("FROM OrderItem WHERE restaurantId=:Id and activeFlag='Y'").setParameter("Id",restaurantId).list();
			//ordersList =getSession().createQuery("select distinct orderId,customerId,restaurantId FROM OrderItem where restaurantId=:Id").setParameter("Id", restaurantId).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordersList;
	}	
}

//ordersList =getSession().createQuery("FROM OrderItem where ActiveFlag='Y' and RestaurantId=:Id").setParameter("Id", restaurantId).list();
