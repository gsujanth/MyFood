package com.myfood.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "paymentInfo")
public class PaymentInfo {
	
	@Id
	@Column(name="paymentInfoId")
	private int paymentInfoId;
	
	@Column(name="orderId")
	private int orderId;
	
	@Column(name="cardNumber")	
	private String cardNumber;
	
	@Column(name="cardName")
	private String cardName;
	
	@Column(name="amount")
	private double amount;
	
	public int getPaymentInfoId() {
		return paymentInfoId;
	}


	public void setPaymentInfoId(int paymentInfoId) {
		this.paymentInfoId = paymentInfoId;
	}



	public int getOrderId() {
		return orderId;
	}



	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}



	public String getCardNumber() {
		return cardNumber;
	}



	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}



	public String getCardName() {
		return cardName;
	}



	public void setCardName(String cardName) {
		this.cardName = cardName;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	@Override
	public String toString() {
		return "PaymentInfo [paymentInfoId=" + paymentInfoId + ", orderId=" + orderId + ", cardNumber=" + cardNumber
				+ ", cardName=" + cardName + ", amount=" + amount + "]";
	}

}
