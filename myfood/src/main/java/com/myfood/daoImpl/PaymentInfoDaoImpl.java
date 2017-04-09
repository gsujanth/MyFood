package com.myfood.daoImpl;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myfood.dao.PaymentInfoDao;
import com.myfood.model.PaymentInfo;

@Repository("paymentInfoDao")
public class PaymentInfoDaoImpl implements PaymentInfoDao {

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

	public int getRecentPaymentInfoId() {
		PaymentInfo paymentInfo = (PaymentInfo)getSession().createQuery("FROM PaymentInfo f order by f.paymentInfoId desc").setMaxResults(1)
				.uniqueResult();
		if(null == paymentInfo)
			return 0;
		else
			return paymentInfo.getPaymentInfoId();
	}
	
	@Transactional
	public void addPaymentInfo(PaymentInfo item) {
		getSession().save(item);
	}
}
