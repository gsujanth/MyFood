package com.myfood.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.myfood.dao.RestaurantDao;
import com.myfood.model.MenuItem;
import com.myfood.model.Restaurant;

@Repository("RestaurantDao")
@Component
public class RestaurantDaoImpl implements RestaurantDao {
	

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		Session session;
		try {
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

	public List<Integer> getRestaurantIdByPincode(int pincode) {

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

	public List<Restaurant> getRestaurantsByIds(List<Integer> restaurantIdList) {

		List<Restaurant> restaurantList = null;
		if (restaurantIdList == null || restaurantIdList.size() == 0) {
			return null;
		} else {
			try {
				restaurantList = getSession().createQuery("FROM Restaurant where flag=1 and restaurantId in (:restuarantIds)")
						.setParameter("restuarantIds", restaurantIdList).list();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return restaurantList;
	}

	public List<Restaurant> getAllRestaurants() {
		List<Restaurant> restaurantList=null;
		try {
			restaurantList=getSession().createQuery("FROM Restaurant where Flag=1").list();
		//restaurantList=getSession().createQuery("FROM Restaurant where Flag in ('True', 'true')").list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return restaurantList;
	}

	public int registerRestaurant(Restaurant restaurant) {
		Session session = getSession();
		try{
			Transaction tx = session.beginTransaction();
			session.save(restaurant);
			tx.commit();	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		System.out.println("In DAO-register-restaurant:"+restaurant);
		return restaurant.getRestaurantId();		
	}

	public int getLastRestaurantId() {
		System.out.println("In DAO");
		Query query = getSession().createQuery("FROM Restaurant order by RestaurantId desc");
		query.setMaxResults(1);
		Restaurant rest = (Restaurant)query.uniqueResult();
		System.out.println("last restaurant--"+rest);
		if(rest == null)
			return 0;
		else
			return rest.getRestaurantId();
	}

	public Restaurant getRestaurantByName(String name) {
		System.out.println("In DAO");
		System.out.println("name is DAO--"+name);
		Restaurant rest = null;
		try{
		Query query = getSession().createQuery("FROM Restaurant WHERE flag=1 and RestaurantName=:name").setParameter("name",name);
		query.setMaxResults(1);
		rest = (Restaurant)query.uniqueResult();
		System.out.println("getRestaurantByName--"+rest);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rest;
	}

	
	public void deleteRestaurant(int id) throws Exception {
		System.out.println("in dao impl--"+id);
		Session session = getSession();
		Transaction tx = getSession().getTransaction();
		Restaurant rest = new Restaurant();
		rest.setRestaurantId(id);
		try{
			tx = session.beginTransaction();
			Query query = (Query) session.createQuery("update Restaurant set Flag=:flag where RestaurantId=:id");
			query.setParameter("flag", 0);
			query.setParameter("id", id);
			query.executeUpdate();
			tx.commit();
			session.update(rest);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	public int getResIdByRestaurantOwnerId(int id){
		System.out.println("In DAO");
		System.out.println("name is DAO--"+id);
		int restId=0;
		try{
		Query query = getSession().createQuery("Select restaurantId FROM RestaurantOwnerMapping WHERE restaurantOwnerId=:id").setParameter("id",id);
		query.setMaxResults(1);
		restId = (Integer)query.uniqueResult();
		System.out.println("getRestaurantByName--"+restId);
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return restId;
	}
}
