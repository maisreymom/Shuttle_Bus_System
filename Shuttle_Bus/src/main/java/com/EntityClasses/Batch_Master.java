package com.EntityClasses;

import java.util.Date;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Batch_Master {
	private String batch_id;
	private int Batch_number;
	@Temporal(TemporalType.DATE)
	private Date date_of_returning;
	
	@Temporal(TemporalType.DATE)
	private Date date_of_leaving;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date deadline_of_booking;
	
	private String status;
	
	
	public String getBatch_id() {
		return batch_id;
	}
	public void setBatch_id(String batch_id) {
		this.batch_id = batch_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private Set<User_Master> user_master;

	public Set<User_Master> getUser_master() {
		return user_master;
	}
	public void setUser_master(Set<User_Master> user_master) {
		this.user_master = user_master;
	}

	public int getBatch_number() {
		return Batch_number;
	}
	public void setBatch_number(int batch_number) {
		Batch_number = batch_number;
	}
	
	public Date getDate_of_returning() {
		return date_of_returning;
	}
	public void setDate_of_returning(Date date_of_returning) {
		this.date_of_returning = date_of_returning;
	}
	public Date getDate_of_leaving() {
		return date_of_leaving;
	}
	public void setDate_of_leaving(Date date_of_leaving) {
		this.date_of_leaving = date_of_leaving;
	}
	
	public Date getDeadline_of_booking() {
		return deadline_of_booking;
	}
	public void setDeadline_of_booking(Date deadline_of_booking) {
		this.deadline_of_booking = deadline_of_booking;
	}

	
	
}
