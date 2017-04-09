package com.myfood.daoImpl;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myfood.dao.OrderDao;
import com.myfood.model.OrderItem;

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
	
	
}
