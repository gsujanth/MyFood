package com.myfood.daoImpl;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myfood.dao.FoodCartDao;
import com.myfood.model.CartItem;

@Repository("foodCartDao")
public class FoodCartDaoImpl implements FoodCartDao{
	
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
	public void addItemToCart(CartItem item) {
		getSession().save(item);
	}
	
	
	public int getRecentCartId() {
		CartItem cartItem = (CartItem)getSession().createQuery("FROM CartItem f order by f.cartPK.cartIndexId desc").setMaxResults(1)
				.uniqueResult();
		if(null == cartItem)
			return 0;
		else
			return cartItem.getCartPK().getCartIndexId();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
