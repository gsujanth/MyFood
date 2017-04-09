package com.myfood.daoImpl;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myfood.dao.DeliveryInfoDao;
import com.myfood.model.DeliveryInfo;

@Repository("deliveryInfoDao")
public class DeliveryInfoDaoImpl implements DeliveryInfoDao {

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
	
	public int getRecentDeliveryInfoId() {
		try{
		DeliveryInfo deliveryInfo = (DeliveryInfo)getSession().createQuery("FROM DeliveryInfo f order by f.deliveryInfoId desc").setMaxResults(1)
				.uniqueResult();
		if(null == deliveryInfo)
			return 0;
		else
			return deliveryInfo.getDeliveryInfoId();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Transactional
	public void addDeliveryInfo(DeliveryInfo item) {
		try{
			System.out.println(item);
			getSession().save(item);	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
