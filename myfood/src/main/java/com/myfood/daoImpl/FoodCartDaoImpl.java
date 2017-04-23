package com.myfood.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myfood.dao.FoodCartDao;
import com.myfood.model.CartItem;
import com.myfood.model.CartPK;
import com.myfood.model.MenuItem;

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
	public void addItemToCart(int customerId, MenuItem item) {
		//Check if item exists: If not exists add an entry else update quantity
		CartItem existingItem = this.getCartForCustomerMenuItem(customerId, item.getItemId());
		if(null == existingItem){
			CartItem cartItem = new CartItem();
			CartPK cartPK = new CartPK();
			cartPK.setCartIndexId(this.getRecentCartId()+1);
			cartPK.setCustomerId(customerId);
			cartItem.setCartPK(cartPK);
			cartItem.setItemId(item.getItemId());
			cartItem.setRestaurantId(item.getRestaurantId());
			cartItem.setItemName(item.getItemName());
			cartItem.setItemQuantity(1);
			cartItem.setItemCost(item.getCost());
			cartItem.setActiveFlag("Y");
			getSession().save(cartItem);
		}else{
			int newQuantity = existingItem.getItemQuantity() + 1;
			existingItem.setItemQuantity(newQuantity);
			existingItem.setItemCost(item.getCost() * newQuantity);
			getSession().saveOrUpdate(existingItem);
		}
	}
	
	
	public int getRecentCartId() {
		CartItem cartItem = (CartItem)getSession().createQuery("FROM CartItem f order by f.cartPK.cartIndexId desc").setMaxResults(1)
				.uniqueResult();
		if(null == cartItem)
			return 0;
		else
			return cartItem.getCartPK().getCartIndexId();
	}
	
	@SuppressWarnings("unchecked")
	public CartItem getCartForCustomerMenuItem(int customerId, int itemId){
		CartItem cartItem = (CartItem)getSession().createQuery("FROM CartItem where activeFlag = 'Y' and itemId=:itemId and cartPK.customerId=:customerId").
				setParameter("itemId", itemId).setParameter("customerId", customerId).setMaxResults(1).uniqueResult();
		return cartItem;
	}
	
	@SuppressWarnings("unchecked")
	public List<CartItem> getActiveCustomerCartByCustomerId(int customerId){
		List<CartItem> cartList = null;
		try{
			cartList = getSession().createQuery("FROM CartItem where activeFlag = 'Y' and  cartPK.customerId=:customerId").
					setParameter("customerId", customerId).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return cartList;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public void updateCartItem(CartItem cartItem) {
		getSession().update(cartItem);
	}
	
	public void updateCartQunatity(int customerId, int itemId, int qunatity, double updatedPrice) {
		CartItem existingItem = this.getCartForCustomerMenuItem(customerId, itemId);
		existingItem.setItemQuantity(qunatity);
		existingItem.setItemCost(updatedPrice);
		getSession().update(existingItem);
	}
}
