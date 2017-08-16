package com.ServiceClasses;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DaoClasses.Admin_Inf;
import com.EntityClasses.Batch_Master;
import com.EntityClasses.Role_Master;
import com.EntityClasses.User_Master;
import com.ModelClasses.Add_Bus;
import com.ModelClasses.EMR;
import com.ModelClasses.Model_User;
import com.ModelClasses.Report_Date;
import com.ModelClasses.Set_Schedule;
@Service
public class AdminServiceImp implements AdminServiceInf {
	@Autowired
	private Admin_Inf admin_inf;
	
	public List<Map<String,Object>> User(){
		return admin_inf.User();
	}
	public List<Map<String,Object>> Schedule_Bus(){
		return admin_inf.Schedule_Bus();
	}
	public List<Map<String,Object>> Schedule_Report(Report_Date rep){
		return admin_inf.Schedule_Report(rep);
	}
	public List<Map<String,Object>> Schedule(){
		return admin_inf.Schedule();
	}
	public  boolean Add_Shuttle (Add_Bus add_bus){
		return admin_inf.Add_Shuttle(add_bus);
	}
	public List<Role_Master> Role(){
		return admin_inf.Role();
	}
	public List<Batch_Master> Batch(){
		return admin_inf.Batch();
	}
	public  boolean Add_User (Model_User add){
		return admin_inf.Add_User(add);
	}
	public  boolean Edit_Bus (Add_Bus add){
		return admin_inf.Edit_Bus(add);
	}
	public  boolean Delete_Bus (String delete_id){
		return admin_inf.Delete_Bus(delete_id);
	}
	public  List<User_Master> Driver(){
		return admin_inf.Driver();
	}
	public  List<Map<String,Object>> showSchedule(){
		return admin_inf.showSchedule();
	}
	public  boolean setSchedule (Set_Schedule[] set){
		return admin_inf.setSchedule(set);
	}
	public boolean editSchedule (Set_Schedule[] edit){
		return admin_inf.editSchedule(edit);
	}
	
	public List<Map<String,Object>> detailSchedule(String date_of_travel,String Destination_id){
		return admin_inf.detailSchedule(date_of_travel, Destination_id);
	}
	public List<Map<String,Object>> showEmergency(){
		return admin_inf.showEmergency();
	}
	public  boolean rejectEmergency(List<String> reject){
		return admin_inf.rejectEmergency(reject);
	}
	public  boolean setEmergency(EMR[] confirm){
		return admin_inf.setEmergency(confirm);
	}

		
}
