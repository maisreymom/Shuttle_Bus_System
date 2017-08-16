package com.ModelClasses;

public class BatchUpdate {
	
	public String batch_id;
	public int batch_number;
	public String date_of_leaving;
	public String date_of_returning;
	public String deadline_of_booking;
	
	public String getBatch_id() {
		return batch_id;
	}
	public void setBatch_id(String batch_id) {
		this.batch_id = batch_id;
	}
	public int getBatch_number() {
		return batch_number;
	}
	public void setBatch_number(int batch_number) {
		this.batch_number = batch_number;
	}
	public String getDate_of_leaving() {
		return date_of_leaving;
	}
	public void setDate_of_leaving(String date_of_leaving) {
		this.date_of_leaving = date_of_leaving;
	}
	public String getDate_of_returning() {
		return date_of_returning;
	}
	public void setDate_of_returning(String date_of_returning) {
		this.date_of_returning = date_of_returning;
	}
	public String getDeadline_of_booking() {
		return deadline_of_booking;
	}
	public void setDeadline_of_booking(String deadline_of_booking) {
		this.deadline_of_booking = deadline_of_booking;
	}

}
