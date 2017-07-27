package com.EntityClasses;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Permission {
	private int id;
	private User_Master user_id;
	@Temporal(TemporalType.DATE)
	private Date date_of_request;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User_Master getUser_id() {
		return user_id;
	}
	public void setUser_id(User_Master user_id) {
		this.user_id = user_id;
	}
	
	public Date getDate_of_request() {
		return date_of_request;
	}
	public void setDate_of_request(Date date_of_request) {
		this.date_of_request = date_of_request;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
