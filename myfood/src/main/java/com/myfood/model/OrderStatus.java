package com.myfood.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderStatus")
public class OrderStatus {

	@Id
	@Column(name="order_status_id", nullable = false)
	private int orderStatusId;
	
	@Column(name="order_id", nullable = false)
	private int orderId;
	
	@Column(name = "restaurant_Id", nullable = false)
	private int restaurantId;
	
	@Column(name = "status")
	private String status;
			
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "created_on")
	private String createdOn;

	/**
	 * @return the orderStatusId
	 */
	public int getOrderStatusId() {
		return orderStatusId;
	}

	/**
	 * @param orderStatusId the orderStatusId to set
	 */
	public void setOrderStatusId(int orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the restaurantId
	 */
	public int getRestaurantId() {
		return restaurantId;
	}

	/**
	 * @param restaurantId the restaurantId to set
	 */
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the createdOn
	 */
	public String getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param string the createdOn to set
	 */
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderStatus [orderStatusId=" + orderStatusId + ", orderId=" + orderId + ", restaurantId=" + restaurantId
				+ ", status=" + status + ", comments=" + comments + ", createdOn=" + createdOn + "]";
	}
}
