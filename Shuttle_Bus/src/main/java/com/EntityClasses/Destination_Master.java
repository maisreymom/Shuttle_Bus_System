package com.EntityClasses;

import java.util.Date;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Destination_Master{
	
	private String destination_id;
	private String destination_name;
	@Temporal(TemporalType.TIME)
	private Date departure_time;
	@Temporal(TemporalType.TIME)
	private Date arrival_time;
	
	private Set<Passenger> passenger;
	
	private Set<Schedule_Table> schedule_table;
	
	public Destination_Master(){
		
	}
	public Destination_Master(String destination_id ){
		this.destination_id = destination_id;
	}
	
	
	
	
	public String getDestination_id() {
		return destination_id;
	}
	public void setDestination_id(String destination_id) {
		this.destination_id = destination_id;
	}
	public String getDestination_name() {
		return destination_name;
	}
	public void setDestination_name(String destination_name) {
		this.destination_name = destination_name;
	}
	
	
	public Date getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(Date departure_time) {
		this.departure_time = departure_time;
	}
	public Date getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(Date arrival_time) {
		this.arrival_time = arrival_time;
	}
	public Set<Schedule_Table> getSchedule_table() {
		return schedule_table;
	}
	public void setSchedule_table(Set<Schedule_Table> schedule_table) {
		this.schedule_table = schedule_table;
	}
	
	public Set<Passenger> getPassenger() {
		return passenger;
	}
	public void setPassenger(Set<Passenger> passenger) {
		this.passenger = passenger;
	}

}
