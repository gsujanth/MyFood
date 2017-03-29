package com.myfood.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myfood.dao.RestaurantDao;
import com.myfood.model.MenuItem;
import com.myfood.model.Restaurant;

@Repository("RestaurantDao")
public class RestaurantDaoImpl implements RestaurantDao{

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

	public List<Integer> getRestaurantIdByPincode(int pincode){

		List<Integer> restaurantIdList = null;
		try{
			restaurantIdList = getSession().createQuery("Select restaurantId FROM PincodeMapping where pincode=:pincode").
					setParameter("pincode", pincode).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return restaurantIdList;
	}

	public List<Restaurant> getRestaurantsByIds(List<Integer> restaurantIdList){

		List<Restaurant> restaurantList = null;
		if(restaurantIdList == null || restaurantIdList.size() == 0){
			return null;
		}
		else{
			try{
				restaurantList = getSession().createQuery("FROM Restaurant where restaurantId in (:restuarantIds)").
						setParameter("restuarantIds", restaurantIdList).list();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}

		return restaurantList;
	}

}
