package com.myfood.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OrderStatusMapping")
public class OrderStatusMapping {
	
	@Id
	@Column(name="StatusMappingId", nullable = false)
	private int statusMappingId;
	
	@Column(name = "StatusDesc", nullable = false)
	private String statusDesc;

	public int getStatusMappingId() {
		return statusMappingId;
	}

	public void setStatusMappingId(int statusMappingId) {
		this.statusMappingId = statusMappingId;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

}
