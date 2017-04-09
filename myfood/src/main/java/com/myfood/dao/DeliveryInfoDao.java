package com.myfood.dao;

import com.myfood.model.DeliveryInfo;

public interface DeliveryInfoDao {

	public int getRecentDeliveryInfoId();
	public void addDeliveryInfo(DeliveryInfo item);
}
