package com.duyngoc.model;

import java.util.Date;

public class Order {
	private Long id;
	private Long userId;
	private Long apartmentId;
	private boolean approved;
	private Date date;
	public Order(){}
	
	public Order(Long userId,Long appartmentId){
		this.userId=userId;
		this.apartmentId=appartmentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(Long apartmentId) {
		this.apartmentId = apartmentId;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
}
