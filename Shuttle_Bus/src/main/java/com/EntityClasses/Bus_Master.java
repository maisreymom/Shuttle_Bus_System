package com.EntityClasses;


import java.util.Set;

public class Bus_Master {

	private String bus_id;
	private String bus_model;
	
	private String plate_number;
	private int no_of_seat;
	
	private String bus_image;
	private String status;
	private Set<Bus_Per_Schedule> bus_per_schedule;
	
	public String getBus_id() {
		return bus_id;
	}
	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}
	
	public String getPlate_number() {
		return plate_number;
	}
	public void setPlate_number(String plate_number) {
		this.plate_number = plate_number;
	}
	
	public String getBus_model() {
		return bus_model;
	}
	public void setBus_model(String bus_model) {
		this.bus_model = bus_model;
	}
	public int getNo_of_seat() {
		return no_of_seat;
	}
	public void setNo_of_seat(int no_of_seat) {
		this.no_of_seat = no_of_seat;
	}

	public String getBus_image() {
		return bus_image;
	}
	public void setBus_image(String bus_image) {
		this.bus_image = bus_image;
	}

	public Set<Bus_Per_Schedule> getBus_per_schedule() {
		return bus_per_schedule;
	}
	public void setBus_per_schedule(Set<Bus_Per_Schedule> bus_per_schedule) {
		this.bus_per_schedule = bus_per_schedule;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
