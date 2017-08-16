package com.ServiceClasses;

import java.util.List;
import java.util.Map;

import com.EntityClasses.Batch_Master;
import com.EntityClasses.Role_Master;
import com.EntityClasses.User_Master;
import com.ModelClasses.Add_Bus;
import com.ModelClasses.EMR;
import com.ModelClasses.Model_User;
import com.ModelClasses.Report_Date;
import com.ModelClasses.Set_Schedule;

public interface AdminServiceInf {
	public List<Map<String,Object>> User();
	public List<Map<String,Object>> Schedule_Bus();
	public List<Map<String,Object>> Schedule_Report(Report_Date rep);
	public List<Map<String,Object>> Schedule();
	public  boolean Add_Shuttle (Add_Bus add_bus);
	public List<Role_Master> Role();
	public List<Batch_Master> Batch();
	public  boolean Add_User (Model_User add);
	public  boolean Edit_Bus (Add_Bus add);
	public  boolean Delete_Bus (String delete_id);
	public  List<User_Master> Driver();
	public  List<Map<String,Object>> showSchedule();
	public  boolean setSchedule (Set_Schedule[] set);
	public boolean editSchedule (Set_Schedule[] edit);
	
	public List<Map<String,Object>> detailSchedule(String date_of_travel,String Destination_id);
	public List<Map<String,Object>> showEmergency();
	public  boolean rejectEmergency(List<String> reject);
	public  boolean setEmergency(EMR[] confirm);
}
