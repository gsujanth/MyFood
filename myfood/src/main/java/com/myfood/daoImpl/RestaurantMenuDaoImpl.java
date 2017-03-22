package com.myfood.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myfood.dao.RestaurantMenuDao;
import com.myfood.model.MenuItem;

@Repository("restaurantMenuDao")
public class RestaurantMenuDaoImpl implements RestaurantMenuDao{
	
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

	@Transactional
	public List<MenuItem> getMenuByRestaurant(int restaurantId) {
		List<MenuItem> items = getSession().createQuery("FROM MenuItem m WHERE m.restaurantId=:id").
				setParameter("id", restaurantId).list();
		return items;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
