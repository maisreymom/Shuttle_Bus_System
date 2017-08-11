package com.DaoClasses;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.EntityClasses.Batch_Master;
import com.EntityClasses.Exchange_Seat;
import com.EntityClasses.Passenger;
import com.EntityClasses.Permission;
import com.EntityClasses.Schedule_Table;
import com.EntityClasses.Ticket_Donation;
import com.EntityClasses.User_Master;
import com.ModelClasses.Donate;
import com.ModelClasses.EmergencyBooking;
import com.ModelClasses.ExchangeSeat;
import com.ModelClasses.NotificationModel;
import com.ModelClasses.PermissionRequest;


public interface StudentDao {
	public List<Batch_Master> Batch();
	public List<User_Master> Bat_User(Donate donate) ;
	public Boolean donate(Donate bat);
	public Boolean exchange(ExchangeSeat exch);
	public Boolean check_exchange(String user_id);
	public Boolean emergencyBooking(EmergencyBooking book);
	public Boolean permission_request(PermissionRequest pr);
	public List<Schedule_Table> checkSchdule(String user_id);
	public List<User_Master> getUserInfo(String user_id);
	public List<Map<String, Object>> booking_notification(NotificationModel noti);
	public List<Map<String, Object>> donate_notification(NotificationModel noti);
	public List<Map<String, Object>> exchange_seat_notification(NotificationModel noti);
	public List<Map<String, Object>> permission_notification(NotificationModel noti);
	public List<Schedule_Table> checkSchedule(String des_id, Date date_travel);
}
