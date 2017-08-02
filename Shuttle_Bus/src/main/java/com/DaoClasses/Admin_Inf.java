package com.DaoClasses;

import java.util.List;
import java.util.Map;

import com.EntityClasses.Batch_Master;
import com.EntityClasses.Bus_Master;
import com.EntityClasses.Role_Master;
import com.EntityClasses.Schedule_Table;
import com.EntityClasses.User_Master;
import com.ModelClasses.Add_Bus;
import com.ModelClasses.Model_User;
import com.ModelClasses.Set_Schedule;
public interface Admin_Inf {
	public List<User_Master> User();
	public List<Bus_Master> Schedule_Bus();
	public List<Schedule_Table> Schedule_Report();
	public  boolean Add_Shuttle (Add_Bus add_bus);
	public List<Role_Master> Role();
	public List<Batch_Master> Batch();
	public  boolean Add_User (Model_User add);
	public  boolean Edit_Bus (Add_Bus add);
	public  boolean Delete_Bus (String delete_id);
	public  List<User_Master> Driver();
	public  boolean setSchedule (Set_Schedule[] set);
	public  boolean setBus(Map<String,List<String>> upd,String date,String dest);
	public List<Map<String,Object>> detailSchedule(String date_of_travel,String Destination_id);
}
