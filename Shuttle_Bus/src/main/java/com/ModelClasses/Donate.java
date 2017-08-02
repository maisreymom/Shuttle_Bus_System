package com.ModelClasses;

public class Donate {
	public String receive_from;
	public String donate_to;
	public int no_ticket;
	
	public String batch_id;
	public String user_id;
	
	public String getBatch_id() {
		return batch_id;
	}
	public void setBatch_id(String batch_id) {
		this.batch_id = batch_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getNo_ticket() {
		return no_ticket;
	}
	public void setNo_ticket(int no_ticket) {
		this.no_ticket = no_ticket;
	}
	public String getReceive_from() {
		return receive_from;
	}
	public void setReceive_from(String receive_from) {
		this.receive_from = receive_from;
	}
	public String getDonate_to() {
		return donate_to;
	}
	public void setDonate_to(String donate_to) {
		this.donate_to = donate_to;
	}
}
