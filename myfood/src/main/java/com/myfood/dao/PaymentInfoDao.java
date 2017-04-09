package com.myfood.dao;

import com.myfood.model.PaymentInfo;

public interface PaymentInfoDao {

	public int getRecentPaymentInfoId();
	public void addPaymentInfo(PaymentInfo item);
}
