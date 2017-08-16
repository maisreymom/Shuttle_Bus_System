package com.ModelClasses;

import java.util.List;

public class Set_Schedule {
	
	private String bus_id;
	private String driver_name;
	private int total_seats;
	private List<String> passenger_id;
	private int customer;
	private int staff;
	private int student;
	private int remaining_seats;
	private String date_of_travel;
	private String destination_id;
	private String est_arrival;
	private String est_departure;
	private String customer_only;
	public String getEst_arrival() {
		return est_arrival;
	}
	public void setEst_arrival(String est_arrival) {
		this.est_arrival = est_arrival;
	}
	public String getEst_departure() {
		return est_departure;
	}
	public void setEst_departure(String est_departure) {
		this.est_departure = est_departure;
	}
	public String getDate_of_travel() {
		return date_of_travel;
	}
	public void setDate_of_travel(String date_of_travel) {
		this.date_of_travel = date_of_travel;
	}
	public String getDestination_id() {
		return destination_id;
	}
	public void setDestination_id(String destination_id) {
		this.destination_id = destination_id;
	}
	
	public int getRemaining_seats() {
		return remaining_seats;
	}
	public void setRemaining_seats(int remaining_seats) {
		this.remaining_seats = remaining_seats;
	}
	public int getCustomer() {
		return customer;
	}
	public void setCustomer(int customer) {
		this.customer = customer;
	}
	public int getStaff() {
		return staff;
	}
	public void setStaff(int staff) {
		this.staff = staff;
	}
	public int getStudent() {
		return student;
	}
	public void setStudent(int student) {
		this.student = student;
	}
	
	public List<String> getPassenger_id() {
		return passenger_id;
	}
	public void setPassenger_id(List<String> passenger_id) {
		this.passenger_id = passenger_id;
	}
	public String getBus_id() {
		return bus_id;
	}
	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}
	public String getDriver_name() {
		return driver_name;
	}
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	public int getTotal_seats() {
		return total_seats;
	}
	public void setTotal_seats(int total_seats) {
		this.total_seats = total_seats;
	}
	public String getCustomer_only() {
		return customer_only;
	}
	public void setCustomer_only(String customer_only) {
		this.customer_only = customer_only;
	}
	
	
	
}
