package com.EntityClasses;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class Bus_Report_Table {

	
	@Temporal(TemporalType.TIME)
	private Date actual_departure_time;
	@Temporal(TemporalType.TIME)
	private Date actual_arrival_time;
	
	
	private float total_time_spent;
	private float fuel_spent;
	private float distance;
	private float total_expense;
	private String extra_info;
	private Bus_Per_Schedule bus_per_schedule;
	private String bus_per_schedule_id;
	
	
	public String getBus_per_schedule_id() {
		return bus_per_schedule_id;
	}

	public void setBus_per_schedule_id(String bus_per_schedule_id) {
		this.bus_per_schedule_id = bus_per_schedule_id;
	}

	
	
	public Bus_Per_Schedule getBus_per_schedule() {
		return bus_per_schedule;
	}

	public void setBus_per_schedule(Bus_Per_Schedule bus_per_schedule) {
		this.bus_per_schedule = bus_per_schedule;
	}

	
	
	public Date getActual_departure_time() {
		return actual_departure_time;
	}

	public void setActual_departure_time(Date actual_departure_time) {
		this.actual_departure_time = actual_departure_time;
	}

	public Date getActual_arrival_time() {
		return actual_arrival_time;
	}

	public void setActual_arrival_time(Date actual_arrival_time) {
		this.actual_arrival_time = actual_arrival_time;
	}

	public float getTotal_time_spent() {
		return total_time_spent;
	}
	public void setTotal_time_spent(float total_time_spent) {
		this.total_time_spent = total_time_spent;
	}
	public float getFuel_spent() {
		return fuel_spent;
	}
	public void setFuel_spent(float fuel_spent) {
		this.fuel_spent = fuel_spent;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public float getTotal_expense() {
		return total_expense;
	}
	public void setTotal_expense(float total_expense) {
		this.total_expense = total_expense;
	}
	public String getExtra_info() {
		return extra_info;
	}
	public void setExtra_info(String extra_info) {
		this.extra_info = extra_info;
	}
	
}
