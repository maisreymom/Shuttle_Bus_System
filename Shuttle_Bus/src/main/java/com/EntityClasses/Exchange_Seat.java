package com.EntityClasses;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Exchange_Seat {
	private int id;
	private Schedule_Table schedule_id;
	private User_Master exchange_to;
	private User_Master exchange_from;
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_at;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_at;
	private String notification;
	
	
	public String getNotification() {
		return notification;
	}
	public void setNotification(String notification) {
		this.notification = notification;
	}
	public Exchange_Seat() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Schedule_Table getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(Schedule_Table schedule_id) {
		this.schedule_id = schedule_id;
	}
	public User_Master getExchange_to() {
		return exchange_to;
	}
	public void setExchange_to(User_Master exchange_to) {
		this.exchange_to = exchange_to;
	}
	public User_Master getExchange_from() {
		return exchange_from;
	}
	public void setExchange_from(User_Master exchange_from) {
		this.exchange_from = exchange_from;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
}
