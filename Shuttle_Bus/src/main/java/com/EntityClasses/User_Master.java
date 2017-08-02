package com.EntityClasses;

import java.util.Set;

public class User_Master {
	
	private String user_id;
	private String username;
	private String password;
	private String fullname;
	private String gender;
	private Batch_Master batch_id;
	private Role_Master role_id;
	private String email;
	private String phone_number;
	private int no_of_ticket;
	private int no_of_donate_ticket;
	private boolean status;
	private Set<Passenger> passenger;
	private Set<Ticket_Donation> ticket_donation ;
	private Set<Bus_Per_Schedule> bus_per_schedule;
	private Set<Emergency_Request> emergency_request;
	private Set<Permission> permission;
	
	public User_Master(){
		
	}
	public User_Master(String user_id){
		this.user_id = user_id;
	}
	
	
	public Set<Passenger> getPassenger() {
		return passenger;
	}
	public void setPassenger(Set<Passenger> passenger) {
		this.passenger = passenger;
	}
	public Set<Ticket_Donation> getTicket_donation() {
		return ticket_donation;
	}
	public void setTicket_donation(Set<Ticket_Donation> ticket_donation) {
		this.ticket_donation = ticket_donation;
	}
	public Set<Bus_Per_Schedule> getBus_per_schedule() {
		return bus_per_schedule;
	}
	public void setBus_per_schedule(Set<Bus_Per_Schedule> bus_per_schedule) {
		this.bus_per_schedule = bus_per_schedule;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Batch_Master getBatch_id() {
		return batch_id;
	}
	public void setBatch_id(Batch_Master batch_id) {
		this.batch_id = batch_id;
	}
	public Role_Master getRole_id() {
		return role_id;
	}
	public void setRole_id(Role_Master role_id) {
		this.role_id = role_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public int getNo_of_ticket() {
		return no_of_ticket;
	}
	public void setNo_of_ticket(int no_of_ticket) {
		this.no_of_ticket = no_of_ticket;
	}
	public int getNo_of_donate_ticket() {
		return no_of_donate_ticket;
	}
	public void setNo_of_donate_ticket(int no_of_donate_ticket) {
		this.no_of_donate_ticket = no_of_donate_ticket;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Set<Emergency_Request> getEmergency_request() {
		return emergency_request;
	}
	public void setEmergency_request(Set<Emergency_Request> emergency_request) {
		this.emergency_request = emergency_request;
	}
	public Set<Permission> getPermission() {
		return permission;
	}
	public void setPermission(Set<Permission> permission) {
		this.permission = permission;
	}
	
	
}
