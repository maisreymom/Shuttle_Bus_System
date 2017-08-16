package com.ModelClasses;

public class PermissionRequest {
	public String user_id;
	public String date_of_request;
	public String reason;
	public String status;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getDate_of_request() {
		return date_of_request;
	}
	public void setDate_of_request(String date_of_request) {
		this.date_of_request = date_of_request;
	}
	public String getReason(){
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
