package com.myfood.daoImpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import com.myfood.dao.CustomerDao;
import com.myfood.model.Customer;

@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao{
	
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

	public Customer getCustomerById(int customerId) {
		Customer customer = (Customer)getSession().get(Customer.class, customerId);
		return customer;
	}
	
	public void updateCustomer(Customer customer) {
		getSession().update(customer);
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public int getLastCustomerId() {
		System.out.println("In DAO");
		Query query = getSession().createQuery("FROM Customer order by customerId desc");
		query.setMaxResults(1);
		Customer cust = (Customer)query.uniqueResult();
		System.out.println(cust);
		if(cust == null)
			return 0;
		else
			return cust.getCustomerId();
	}

	public int registerCustomer(Customer customer) {
		Session session = getSession();
		try{
			Transaction tx = session.beginTransaction();
			//getSession().persist(customer);
			session.save(customer);
			tx.commit();	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		//getSession().close();
		System.out.println("In DAO-registerCustomer:"+customer);
		return customer.getCustomerId();		
	}


	public boolean isValidUser(String email, String password)
	{
		try{
			int n = ((Long)getSession().createQuery("select count(*) FROM Customer WHERE email=:id and password=:pass").
					setParameter("id",email).setParameter("pass",password).uniqueResult()).intValue();

			if (n == 0)
				return false;
			else
				return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Customer getCustomerByEmail(String email){
		System.out.println("In DAO");
		Customer cust = null;
		try{
		Query query = getSession().createQuery("FROM Customer WHERE email=:id").setParameter("id",email);
		query.setMaxResults(1);
		cust = (Customer)query.uniqueResult();
		System.out.println(cust);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return cust;
	}
	
}
