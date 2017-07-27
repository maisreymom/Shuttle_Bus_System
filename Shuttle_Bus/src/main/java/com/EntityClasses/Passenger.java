package com.EntityClasses;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Passenger {
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_of_booking;
    @Temporal(TemporalType.DATE)
	private Date date_of_travel;
	
	private User_Master user_id;
	private Destination_Master destination_id;
	
	private String seat_number;
	private String ticket_qrcode;
	private Bus_Per_Schedule bus_per_schedule_id;
	
	
	public Passenger(){
		
	}
	
	public Passenger(Date date_of_booking,User_Master user_id,Destination_Master destination_id,Date date_of_travel){
		this.date_of_booking=date_of_booking;
		this.user_id=user_id;
		this.destination_id=destination_id;
		this.date_of_travel=date_of_travel;
	}
	
	
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
	public Destination_Master getDestination_id() {
		return destination_id;
	}
	public void setDestination_id(Destination_Master destination_id) {
		this.destination_id = destination_id;
	}
	
	public Date getDate_of_booking() {
		return date_of_booking;
	}

	public void setDate_of_booking(Date date_of_booking) {
		this.date_of_booking = date_of_booking;
	}

	public Date getDate_of_travel() {
		return date_of_travel;
	}

	public void setDate_of_travel(Date date_of_travel) {
		this.date_of_travel = date_of_travel;
	}

	public String getSeat_number() {
		return seat_number;
	}
	public void setSeat_number(String seat_number) {
		this.seat_number = seat_number;
	}
	public String getTicket_qrcode() {
		return ticket_qrcode;
	}
	public void setTicket_qrcode(String ticket_qrcode) {
		this.ticket_qrcode = ticket_qrcode;
	}
	

	public Bus_Per_Schedule getBus_per_schedule_id() {
		return bus_per_schedule_id;
	}
	public void setBus_per_schedule_id(Bus_Per_Schedule bus_per_schedule_id) {
		this.bus_per_schedule_id = bus_per_schedule_id;
	}

}
