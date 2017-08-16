package com.EntityClasses;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Ticket_Donation {

	
	private int id;
	private int no_of_ticket;
	@Temporal(TemporalType.DATE)
	private Date created_at;
	@Temporal(TemporalType.DATE)
	private Date updated_at;
	private User_Master donate_to;
	private User_Master receive_from;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNo_of_ticket() {
		return no_of_ticket;
	}
	public void setNo_of_ticket(int no_of_ticket) {
		this.no_of_ticket = no_of_ticket;
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
	public User_Master getDonate_to() {
		return donate_to;
	}
	public void setDonate_to(User_Master donate_to) {
		this.donate_to = donate_to;
	}
	public User_Master getReceive_from() {
		return receive_from;
	}
	public void setReceive_from(User_Master receive_from) {
		this.receive_from = receive_from;
	}
}
