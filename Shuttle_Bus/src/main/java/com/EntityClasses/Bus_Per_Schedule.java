package com.EntityClasses;

import java.util.Date;
import java.util.Set;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Bus_Per_Schedule {

	private String bus_per_schedule_id;
	private Schedule_Table schedule_id;
	private User_Master user_id;
	private Bus_Master bus_id;
	private int number_of_seats;
	
	@Temporal(TemporalType.TIME)
	private Date est_departure_time;
	
	@Temporal(TemporalType.TIME)
	private Date est_arrival_time;
	private int no_of_customer;
	private int no_of_student;
	private int no_of_staff;
	private int remaining_seats;
	private String customer_only;
	
	private Set<Passenger> passenger;
	private Bus_Report_Table bus_report_table;
	
	
	
	
	public Bus_Report_Table getBus_report_table() {
		return bus_report_table;
	}
	public void setBus_report_table(Bus_Report_Table bus_report_table) {
		this.bus_report_table = bus_report_table;
	}
	public String getBus_per_schedule_id() {
		return bus_per_schedule_id;
	}
	public void setBus_per_schedule_id(String bus_per_schedule_id) {
		this.bus_per_schedule_id = bus_per_schedule_id;
	}
	public Schedule_Table getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(Schedule_Table schedule_id) {
		this.schedule_id = schedule_id;
	}
	public User_Master getUser_id() {
		return user_id;
	}
	public void setUser_id(User_Master user_id) {
		this.user_id = user_id;
	}
	public Bus_Master getBus_id() {
		return bus_id;
	}
	public void setBus_id(Bus_Master bus_id) {
		this.bus_id = bus_id;
	}
	public int getNumber_of_seats() {
		return number_of_seats;
	}
	public void setNumber_of_seats(int number_of_seats) {
		this.number_of_seats = number_of_seats;
	}
	
	public Set<Passenger> getPassenger() {
		return passenger;
	}
	public void setPassenger(Set<Passenger> passenger) {
		this.passenger = passenger;
	}
	public Date getEst_departure_time() {
		return est_departure_time;
	}
	public void setEst_departure_time(Date est_departure_time) {
		this.est_departure_time = est_departure_time;
	}
	public Date getEst_arrival_time() {
		return est_arrival_time;
	}
	public void setEst_arrival_time(Date est_arrival_time) {
		this.est_arrival_time = est_arrival_time;
	}
	public int getNo_of_customer() {
		return no_of_customer;
	}
	public void setNo_of_customer(int no_of_customer) {
		this.no_of_customer = no_of_customer;
	}
	public int getNo_of_student() {
		return no_of_student;
	}
	public void setNo_of_student(int no_of_student) {
		this.no_of_student = no_of_student;
	}
	public int getNo_of_staff() {
		return no_of_staff;
	}
	public void setNo_of_staff(int no_of_staff) {
		this.no_of_staff = no_of_staff;
	}
	public String getCustomer_only() {
		return customer_only;
	}
	public void setCustomer_only(String customer_only) {
		this.customer_only = customer_only;
	}
	public int getRemaining_seats() {
		return remaining_seats;
	}
	public void setRemaining_seats(int remaining_seats) {
		this.remaining_seats = remaining_seats;
	}
	
	
}
