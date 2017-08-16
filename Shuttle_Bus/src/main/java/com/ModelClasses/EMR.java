package com.ModelClasses;

import java.util.List;

public class EMR {
	private List<String> user_id;
	private String schedule_id;
	
	
	public List<String> getUser_id() {
		return user_id;
	}
	public void setUser_id(List<String> user_id) {
		this.user_id = user_id;
	}
	public String getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(String schedule_id) {
		this.schedule_id = schedule_id;
	}
	
}
