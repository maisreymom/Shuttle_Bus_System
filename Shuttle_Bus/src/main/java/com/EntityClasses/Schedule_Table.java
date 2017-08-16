package com.EntityClasses;

import java.util.Date;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Schedule_Table {

	private String schedule_id;
    @Temporal(TemporalType.DATE)
	private Date date_of_travel;
	
	private Destination_Master destination_id;

	private int total_available_seats;
	private int customer_seats;
	private int student_seats;
	private int staff_seats;
	private int remaining_seats;
	
	private Set<Bus_Per_Schedule> bus_per_schedule;
	private Set<Emergency_Request> emergency_request;
	private Set<Exchange_Seat> exchange_seat;
	
	public Schedule_Table(){
		
	}
	public Schedule_Table(String schedule_id) {
		super();
		this.schedule_id = schedule_id;
	}
	public String getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(String schedule_id) {
		this.schedule_id = schedule_id;
	}
	
	public Date getDate_of_travel() {
		return date_of_travel;
	}

	public void setDate_of_travel(Date date_of_travel) {
		this.date_of_travel = date_of_travel;
	}

	public Destination_Master getDestination_id() {
		return destination_id;
	}
	public void setDestination_id(Destination_Master destination_id) {
		this.destination_id = destination_id;
	}
	public int getTotal_available_seats() {
		return total_available_seats;
	}
	public void setTotal_available_seats(int total_available_seats) {
		this.total_available_seats = total_available_seats;
	}
	public int getCustomer_seats() {
		return customer_seats;
	}
	public void setCustomer_seats(int customer_seats) {
		this.customer_seats = customer_seats;
	}
	public int getStudent_seats() {
		return student_seats;
	}
	public void setStudent_seats(int student_seats) {
		this.student_seats = student_seats;
	}
	public int getStaff_seats() {
		return staff_seats;
	}
	public void setStaff_seats(int staff_seats) {
		this.staff_seats = staff_seats;
	}
	public int getRemaining_seats() {
		return remaining_seats;
	}
	public void setRemaining_seats(int remaining_seats) {
		this.remaining_seats = remaining_seats;
	}
	
	public Set<Bus_Per_Schedule> getBus_per_schedule() {
		return bus_per_schedule;
	}
	public void setBus_per_schedule(Set<Bus_Per_Schedule> bus_per_schedule) {
		this.bus_per_schedule = bus_per_schedule;
	}

	public Set<Emergency_Request> getEmergency_request() {
		return emergency_request;
	}

	public void setEmergency_request(Set<Emergency_Request> emergency_request) {
		this.emergency_request = emergency_request;
	}
	public Set<Exchange_Seat> getExchange_seat() {
		return exchange_seat;
	}
	public void setExchange_seat(Set<Exchange_Seat> exchange_seat) {
		this.exchange_seat = exchange_seat;
	}
	
}
