package com.DaoClasses;

import java.util.List;
import java.util.Map;

import com.EntityClasses.Bus_Per_Schedule;
import com.EntityClasses.Destination_Master;
import com.EntityClasses.Schedule_Table;
import com.ModelClasses.Booking;
import com.ModelClasses.NotificationModel;
import com.ModelClasses.PermissionRequest;

public interface TeacherDao {
	public List<Destination_Master> getDestinationId();
	public List<Schedule_Table> getSchdule();
	public Boolean BookService(Booking[] passen);
	public List<Map<String,Object>> getPassengerDetail(String sch_id);
	public List<Map<String,Object>> list_student_permission();
	public List<Map<String, Object>> permission_request_teacher(NotificationModel noti);
	public Boolean teacher_confirm_permission(PermissionRequest noti);
}
