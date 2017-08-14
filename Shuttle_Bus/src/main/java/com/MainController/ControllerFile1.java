package com.MainController;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.DaoClasses.Admin_Imp;
import com.DaoClasses.Admin_Inf;
import com.DaoClasses.TeacherDao;
import com.DaoClasses.Teacher_Implement;
import com.EntityClasses.Authentic;
import com.EntityClasses.Batch_Master;
import com.EntityClasses.Bus_Master;
import com.EntityClasses.Bus_Per_Schedule;
import com.EntityClasses.Bus_Report_Table;
import com.EntityClasses.Destination_Master;
import com.EntityClasses.Passenger;
import com.EntityClasses.Role_Master;
import com.EntityClasses.Schedule_Table;
import com.EntityClasses.User_Master;

import com.ModelClasses.BatchUpdate;
import com.ModelClasses.Destination;

import com.ModelClasses.Date_Dest;

import com.ModelClasses.Model_User;
import com.ModelClasses.Report_Date;
import com.ModelClasses.Set_Schedule;
import com.ModelClasses.Booking;
import com.ModelClasses.Add_Bus;
import com.ServiceClasses.usersService;


@SuppressWarnings("unused")
@Controller

public class ControllerFile1{
		
	@Autowired
	usersService usersService1;	
	
	@RequestMapping(value="/admin",method = RequestMethod.GET)
	public ModelAndView Admin(){
		ModelAndView view =new ModelAndView("admin");	
		return view;
		
	}

	@RequestMapping(value="/user",method = RequestMethod.GET)
	public  @ResponseBody List<Map<String,Object>> User(){
			
		List<User_Master> user = new ArrayList<User_Master>();
		Admin_Inf admin = new Admin_Imp();
		user = admin.User();
		List<Map<String,Object>> userList = new ArrayList<Map<String,Object>>();
		
		
		for(int i=0;i<user.size();i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("full_name", user.get(i).getFullname());
			map.put("batch", user.get(i).getBatch_id().getBatch_number());
			map.put("role", user.get(i).getRole_id().getRole_name());
			map.put("phone", user.get(i).getPhone_number());
			map.put("email", user.get(i).getEmail());
			map.put("gender", user.get(i).getGender());
			map.put("password", user.get(i).getPassword());
			userList.add(map);
			
		}
			return userList;
	}
	
	@RequestMapping(value="/schedule_bus",method = RequestMethod.GET)
	public @ResponseBody List<Map<String,Object>> Schedule_Bus(){
			
		List<Bus_Master> bus = new ArrayList<Bus_Master>();
		Admin_Inf admin = new Admin_Imp();
		bus = admin.Schedule_Bus();
		List<Map<String,Object>> schedule_busList = new ArrayList<Map<String,Object>>();
		
		
		for(int i=0;i<bus.size();i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("no_seats", bus.get(i).getNo_of_seat());
			map.put("bus_model", bus.get(i).getBus_model());
			map.put("image", bus.get(i).getBus_image());
			map.put("plate_number", bus.get(i).getPlate_number());
			map.put("bus_id", bus.get(i).getBus_id());
			schedule_busList.add(map);
			
		}
			return schedule_busList;
	}
	
	@RequestMapping(value="/schedule_report",method = RequestMethod.POST)
	public @ResponseBody List<Map<String,Object>> Schedule(@RequestBody Report_Date rep){
		System.out.println(rep.getDate_from()+rep.getDate_to());
		List<Schedule_Table> report = new ArrayList<Schedule_Table>();
		Admin_Inf admin = new Admin_Imp();
		report = admin.Schedule_Report();
		List<Map<String,Object>> report_List = new ArrayList<Map<String,Object>>();
		
		
		for(int i=0;i<report.size();i++){
			
			Map<String,Object> map = new HashMap<String,Object>();
			List<Map<String,Object>> driver_list = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> bus_list = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> time_list = new ArrayList<Map<String,Object>>();
			map.put("date", report.get(i).getDate_of_travel());
			map.put("destination", report.get(i).getDestination_id().getDestination_name());
			map.put("total_seats", report.get(i).getTotal_available_seats());
			map.put("student", report.get(i).getStudent_seats());
			map.put("staff", report.get(i).getStaff_seats());
			map.put("customer", report.get(i).getCustomer_seats());
			
			for(Bus_Per_Schedule driver : report.get(i).getBus_per_schedule()){
				Map<String,Object> driver_name = new HashMap<String,Object>();
				driver_name.put("driver_name", driver.getUser_id().getFullname());
				driver_list.add(driver_name);
			}
			
			map.put("driver", driver_list);
			
			for(Bus_Per_Schedule bus : report.get(i).getBus_per_schedule()){
				Map<String,Object> bus_model = new HashMap<String,Object>();
				bus_model.put("bus_model", bus.getBus_id().getBus_model());
				bus_list.add(bus_model);
			}	
			
			map.put("bus", bus_list);
			
			
			map.put("total_time", time_list);
			
			
			report_List.add(map);
			//two_List.clear();
			
		}
			return report_List;
	}

	
	@RequestMapping(value="/add_bus",method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> Add_Bus(@RequestBody Add_Bus add){
				System.out.println(add.getTotal_seats());
				boolean status;
				Map<String,Object> mp = new HashMap<String,Object>();
				Admin_Inf admin = new Admin_Imp();
				status = admin.Add_Shuttle(add);
				mp.put("status", status);
				return mp;
	}
	
	
	
	@RequestMapping(value="/role",method = RequestMethod.GET)
	public @ResponseBody List<Map<String,Object>> Role(){
			
		List<Role_Master> bus = new ArrayList<Role_Master>();
		Admin_Inf admin = new Admin_Imp();
		bus = admin.Role();
		List<Map<String,Object>> roleList = new ArrayList<Map<String,Object>>();
		
		
		for(int i=0;i<bus.size();i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("role_id", bus.get(i).getRole_id());
			map.put("role_name", bus.get(i).getRole_name());
			
			roleList.add(map);
			
		}
			return roleList;
	}
	
	@RequestMapping(value="/batch",method = RequestMethod.GET)
	public @ResponseBody List<Map<String,Object>> Batch(){
			
		List<Batch_Master> bus = new ArrayList<Batch_Master>();
		Admin_Inf admin = new Admin_Imp();
		bus = admin.Batch();
		List<Map<String,Object>> batchList = new ArrayList<Map<String,Object>>();
		
		
		for(int i=0;i<bus.size();i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("batch_id", bus.get(i).getBatch_id());
			map.put("batch_number", bus.get(i).getBatch_number());
			
			batchList.add(map);
			
		}
			return batchList;
	}
	
	@RequestMapping(value="/add_user",method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> Add_User(@RequestBody Model_User add){
				
				boolean status;
				Map<String,Object> mp = new HashMap<String,Object>();
				Admin_Inf admin = new Admin_Imp();
				status = admin.Add_User(add);
				mp.put("status", status);
				System.out.println(add.getEmail()+add.getBatch()+add.getRole());
				return mp;
				
	}
	
	@RequestMapping(value="/edit_bus",method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> Bus_Edit(@RequestBody Add_Bus add){
				
				boolean status;
				Map<String,Object> mp = new HashMap<String,Object>();
				Admin_Inf admin = new Admin_Imp();
				status = admin.Edit_Bus(add);
				mp.put("status", status);
				
				return mp;
				
	}
	
	@RequestMapping(value="/delete_bus",method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> Delete_Edit(@RequestBody String delete_id){
				System.out.println(delete_id);
				boolean status;
				Map<String,Object> mp = new HashMap<String,Object>();
				Admin_Inf admin = new Admin_Imp();
				status = admin.Delete_Bus(delete_id);
				mp.put("status", status);
				
				return mp;
				
	}
	
	@RequestMapping(value="/driver_list",method = RequestMethod.GET)
	public @ResponseBody List<Map<String,Object>> Driver(){
			
		List<User_Master> driver = new ArrayList<User_Master>();
		Admin_Inf admin = new Admin_Imp();
		driver = admin.Driver();
		List<Map<String,Object>> driverlist = new ArrayList<Map<String,Object>>();
		
		
		for(int i=0;i<driver.size();i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("user_id", driver.get(i).getUser_id());
			map.put("driver_name", driver.get(i).getFullname());
			
			driverlist.add(map);
			
		}
			return driverlist;
	}
	@RequestMapping(value="/set_schedule_passenger",method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> setSchedule(@RequestBody Set_Schedule[] set){
				System.out.println(set[0].getDate_of_travel());
				String schedule_id;
				boolean status=false;
				Map<String,Object> mp = new HashMap<String,Object>();
				Admin_Inf admin = new Admin_Imp();
				
				status = admin.setSchedule(set);
				//if(!schedule_id.equals("null")){
					//status = admin.setBus(set,"o1tv2d7si23oiln");
					//System.out.println(schedule_id);
				//}
				
				mp.put("status", status);
				
				return mp;
				
	}
	
	@RequestMapping(value="/show_schedule",method = RequestMethod.GET)
	public @ResponseBody List<Map<String,Object>> showSchedule(){
				Admin_Imp ad = new Admin_Imp();
				
				
				return ad.showSchedule();
				
	}
	

	@RequestMapping(value="/detail_pass_schedule",method = RequestMethod.POST)
	public @ResponseBody List<Map<String,Object>> detailPassSchedule(@RequestBody Date_Dest obj){
				Admin_Imp ad = new Admin_Imp();
				System.out.println(obj.getDate_of_travel());
				
				return ad.detailSchedule(obj.getDate_of_travel(), obj.getDestination_id());
				
	}
	
	


	
	
	@RequestMapping(value="/get_destionation",method = RequestMethod.GET)
	public @ResponseBody List<Map<String,Object>> getDestination(){
		List<Destination_Master> bus = new ArrayList<Destination_Master>();
		Admin_Inf admin = new Admin_Imp();
		bus = admin.getDes();
		List<Map<String,Object>> desList = new ArrayList<Map<String,Object>>();
		for(int i=0;i<bus.size();i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("destination_name", bus.get(i).getDestination_name());
			map.put("destination_id", bus.get(i).getDestination_id());
			desList.add(map);
			
		}
		return desList;
	}
	@RequestMapping(value="/add_destination",method = RequestMethod.POST)
	public @ResponseBody Boolean addDestination(@RequestBody String new_des){
		System.out.println(new_des);
		Admin_Inf admin = new Admin_Imp();
		Boolean ret=admin.addDestin(new_des);
		return ret;
	}
	
	@RequestMapping(value="/update_destination",method = RequestMethod.POST)
	public @ResponseBody String updateDes(@RequestBody Destination des){
		String desUpdate;
		System.out.println(des);
		Admin_Inf admin = new Admin_Imp();
		admin.desUpdate(des);
		return null;
	}
	
	@RequestMapping(value="/delete_destination",method = RequestMethod.POST)
	public @ResponseBody Boolean deleteDes(@RequestBody String des){
		Boolean ret;
		System.out.println(des);
		Admin_Inf admin = new Admin_Imp();
		ret=admin.desDelete(des);
		return ret;
	}
	
	@RequestMapping(value="/get_batch",method = RequestMethod.GET)
	public @ResponseBody List<Map<String,Object>> getBatch(){
		List<Batch_Master> batch = new ArrayList<Batch_Master>();
		Admin_Inf admin = new Admin_Imp();
		batch = admin.get_batch();
		List<Map<String,Object>> desList = new ArrayList<Map<String,Object>>();
		for(int i=0;i<batch.size();i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("batch_id", batch.get(i).getBatch_id());
			map.put("batch_number", batch.get(i).getBatch_number());
			map.put("date_of_leaving", batch.get(i).getDate_of_leaving());
			map.put("date_of_returning", batch.get(i).getDate_of_returning());
			map.put("deadline_of_booking", batch.get(i).getDeadline_of_booking());
			map.put("status", batch.get(i).getStatus());
			desList.add(map);	
		}
		System.out.println(desList);
		System.out.println(desList);
		return desList;
	}
	
	@RequestMapping(value="/update_batch",method = RequestMethod.POST)
	public @ResponseBody Boolean updateBatch(@RequestBody BatchUpdate bat){
		System.out.println(bat.getBatch_id());
		Admin_Inf admin = new Admin_Imp();
		Boolean ret=admin.batUpdate(bat);
		return ret;
	}
	@RequestMapping(value="/delete_batch",method = RequestMethod.POST)
	public @ResponseBody Boolean delete_batch(@RequestBody String bat){
		Boolean ret;
		Admin_Inf admin = new Admin_Imp();
		ret=admin.deleteBatch(bat);
		return ret;
	}
	@RequestMapping(value="/add_batch",method = RequestMethod.POST)
	public @ResponseBody Boolean addBatch(@RequestBody BatchUpdate bat){
		Admin_Inf admin = new Admin_Imp();
		Boolean ret=admin.addBatch(bat);
		return ret;
	}
	
	@Scheduled(cron="0 0 0 * * *")
    public void updateEmployeeInventory(){
        System.out.println("Started cron job");
        Admin_Inf admin = new Admin_Imp();
		admin.autoUpdateBatchData();
    }
}
	







	