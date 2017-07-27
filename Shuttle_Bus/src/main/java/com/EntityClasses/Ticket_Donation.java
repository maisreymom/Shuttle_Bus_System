package com.EntityClasses;

public class Ticket_Donation {

	
	private int id;
	private User_Master user_id;
	private String donate_to;
	private String receive_from;
	
	
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
	public String getDonate_to() {
		return donate_to;
	}
	public void setDonate_to(String donate_to) {
		this.donate_to = donate_to;
	}
	public String getReceive_from() {
		return receive_from;
	}
	public void setReceive_from(String receive_from) {
		this.receive_from = receive_from;
	}
	
}
