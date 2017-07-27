package com.EntityClasses;

public class Emergency_Request {

	private int id;
	private User_Master user_id;
	private Schedule_Table schedule_id;
	private String reason;
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
	public Schedule_Table getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(Schedule_Table schedule_id) {
		this.schedule_id = schedule_id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
