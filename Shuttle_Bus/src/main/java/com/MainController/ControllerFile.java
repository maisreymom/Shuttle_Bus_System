package com.MainController;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.DaoClasses.StudentDao;
import com.DaoClasses.Student_Implement;
import com.DaoClasses.Teacher_Implement;
import com.EntityClasses.Authentic;
import com.EntityClasses.Batch_Master;
import com.EntityClasses.Bus_Per_Schedule;
import com.EntityClasses.Bus_Report_Table;
import com.EntityClasses.Destination_Master;
import com.EntityClasses.Passenger;
import com.EntityClasses.Schedule_Table;
import com.EntityClasses.User_Master;
import com.ModelClasses.Donate;
import com.ModelClasses.Teacher;
import com.ServiceClasses.usersService;


@Controller

public class ControllerFile {
		
	@Autowired
	usersService usersService1;	
	
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public ModelAndView Login(){
		ModelAndView view =new ModelAndView("login");	
		return view;
		
	}
	
	@RequestMapping(value="/student", method = RequestMethod.GET)
	public ModelAndView getStudent(){
		ModelAndView view =new ModelAndView("student");
		return view;
	}
	
	@RequestMapping(value="/teacher", method = RequestMethod.GET)
	public ModelAndView getTeacher(){
		ModelAndView view =new ModelAndView("teacher");
		return view;	
	}	
	
	@RequestMapping(value="/driver", method = RequestMethod.GET)
	public ModelAndView getDriver(){
		ModelAndView view =new ModelAndView("driver");
		return view;
	}
	
	@RequestMapping(value="/login_service",method = RequestMethod.GET)
	public @ResponseBody String Login_service(@RequestBody Authentic aut){
		String email = aut.getEmail();
		String password = aut.getPassword();
		String url = "";
		if(email.equals("admin@gmail.com")){
			System.out.println(email);
			System.out.println(password);
			url= "admin";
			
		}
		else if(email.equals("teacher@gmail.com")){
			System.out.println(email);
			System.out.println(password);
			url= "teacher";
			
		}
		else if(email.equals("student@gmail.com")){
			System.out.println(email);
			System.out.println(password);
			url= "student";
			
		}
		else{
			System.out.println(email);
			System.out.println(password);
			url= "driver";
			
		}
		return url;
	}
	
	
	@RequestMapping(value="/mobileTeacher",method = RequestMethod.GET)
	public ModelAndView getMobileTeacher(){
		ModelAndView view =new ModelAndView("mobile");	
		return view;
		
	}
	
	
	@RequestMapping(value="/mobileDriver",method = RequestMethod.GET)
	public ModelAndView getMobileDriver(){
		ModelAndView view =new ModelAndView("mobileDriver");	
		return view;
		
	}
	
	@RequestMapping(value="/mobileSchedule",method = RequestMethod.GET)
	public ModelAndView getMobileSchedule(){
		ModelAndView view =new ModelAndView("mobileSchedule");	
		return view;
		
	}
	@RequestMapping(value="/mobileSchedule_Service",method = RequestMethod.GET)
	public @ResponseBody List<Map> getMobileSche_Service(){
		List<Map> bus_per = new ArrayList<Map>();
		List<Bus_Per_Schedule> schedule = new ArrayList<Bus_Per_Schedule>();
		Teacher_Implement sl= new Teacher_Implement();
		schedule = sl.getdriverSche(); 
		for(int i=0;i<schedule.size();i++){
        	Map<String,Object> sch = new HashMap<String,Object>();
        	sch.put("date_of_travel",schedule.get(i).getSchedule_id().getDate_of_travel());
        	sch.put("destination_name",schedule.get(i).getSchedule_id().getDestination_id().getDestination_name());
        	sch.put("bus_model",schedule.get(i).getBus_id().getBus_model());
        	sch.put("driver_name",schedule.get(i).getUser_id().getFullname());
        	sch.put("total_available_seats",schedule.get(i).getSchedule_id().getTotal_available_seats());
        	sch.put("customer_seats",schedule.get(i).getSchedule_id().getCustomer_seats());
        	sch.put("staff_seats",schedule.get(i).getSchedule_id().getStaff_seats());
        	sch.put("student_seats",schedule.get(i).getSchedule_id().getStudent_seats());
        	sch.put("est_departure_time",schedule.get(i).getEst_departure_time());
        	sch.put("est_arrival_time",schedule.get(i).getEst_arrival_time());
        	sch.put("bus_per_schedule_id",schedule.get(i).getBus_per_schedule_id());
        	bus_per.add(sch);
        }
		return bus_per;
	}
	
	
	
	@RequestMapping(value="/userInfo",method = RequestMethod.GET)
	public @ResponseBody List<Map> getUserInfo(){
		List<User_Master> user = new ArrayList<User_Master>();
		List<Map> userReturn = new ArrayList<Map>();
		Teacher_Implement sl= new Teacher_Implement();
		user=sl.getUserInfo();
		for(int i=0;i<user.size();i++){
        	Map<String,Object> users = new HashMap<String,Object>();
        	users.put("fullname", user.get(i).getFullname());
        	users.put("username", user.get(i).getUsername());
        	users.put("no_of_ticket", user.get(i).getNo_of_ticket());
        	userReturn.add(users);
        }
		return userReturn;
	}
	
	
	@RequestMapping(value="/selectService",method = RequestMethod.GET)
	public @ResponseBody List<Map> select_service(){
		List<Destination_Master> dest = new ArrayList<Destination_Master>();
		List<Map> destReturn = new ArrayList<Map>();
		Teacher_Implement sl= new Teacher_Implement();
		dest=sl.getDestinationId();
		for(int i=0;i<dest.size();i++){
        	Map<String,Object> dests = new HashMap<String,Object>();
        	dests.put("destination_name", dest.get(i).getDestination_name());
        	dests.put("destination_id", dest.get(i).getDestination_id());
        	destReturn.add(dests);
        }
		return destReturn;
	}
	
	//Check Leave Confirm
	@RequestMapping(value="/checkConfirm",method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getCheckConfirm(@RequestBody String bus_id){
		System.out.println("Hello");
		Map<String,Object> bus_per = new HashMap<String,Object>();
		List<Bus_Report_Table> bus = new ArrayList<Bus_Report_Table>();
		Teacher_Implement sl= new Teacher_Implement();
		bus=sl.checkConfim(bus_id);
		System.out.println(bus);
		bus_per.put("actual_departure_time",bus.get(0).getActual_departure_time());
		bus_per.put("actual_arrival_time",bus.get(0).getActual_arrival_time());
		System.out.println(bus_per);
		return bus_per;
	}
	@RequestMapping(value="/leaveConfirm",method = RequestMethod.POST)
	public @ResponseBody String getLeaveConfirm(@RequestBody String bus_id){
			Teacher_Implement sl= new Teacher_Implement();
			String bus_set=sl.leaveConfim(bus_id);
			return bus_set;
	}
	
	@RequestMapping(value="/arriveConfirm",method = RequestMethod.POST)
	public @ResponseBody String getArrivedConfirm(@RequestBody String bus_id){
			Teacher_Implement sl= new Teacher_Implement();
			String bus_set=sl.arrivedConfim(bus_id);
			return bus_set;
	}
	

	@RequestMapping(value="/scheduleData",method = RequestMethod.POST)
	public @ResponseBody List<Map> getscheduleData(){
		List<Map> scheReturn = new ArrayList<Map>();
		List<Schedule_Table> schedule = new ArrayList<Schedule_Table>();
		Teacher_Implement sl= new Teacher_Implement();
		schedule = sl.getSchdule(); 
		for(int i=0;i<schedule.size();i++){
        	Map<String,Object> sch = new HashMap<String,Object>();
        	sch.put("date_of_travel",schedule.get(i).getDate_of_travel());
        	sch.put("destination_name",schedule.get(i).getDestination_id().getDestination_name());
        	sch.put("total_available_seats",schedule.get(i).getTotal_available_seats());
        	sch.put("customer_seats",schedule.get(i).getCustomer_seats());
        	sch.put("staff_seats",schedule.get(i).getStaff_seats());
        	sch.put("student_seats",schedule.get(i).getStudent_seats());
        	sch.put("remaining_seats",schedule.get(i).getRemaining_seats());
        	sch.put("schedule_id",schedule.get(i).getSchedule_id());
        	scheReturn.add(sch);
        }
		System.out.println(scheReturn);
		return scheReturn;
	}
	

	@RequestMapping(value="/passengerDetail",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getpassengerDetail(@RequestBody String sch_id){
		List<Bus_Per_Schedule> bus = new ArrayList<Bus_Per_Schedule>();
		Teacher_Implement sl= new Teacher_Implement();
		bus = sl.getPassengerDetail(sch_id); 
		List<Map<String,Object>> passPerDriver = new ArrayList<Map<String, Object>>();
		
		Set<Passenger> pa=new HashSet<Passenger>(); 
		for(int i=0;i<bus.size();i++){
			List<Map> passPer = new ArrayList<Map>();
			pa = bus.get(i).getPassenger();
			for(Passenger b:pa)
			{
				Map<String,Object> sch = new HashMap<String,Object>();
	        	sch.put("passenger_name",b.getUser_id().getFullname());
	        	sch.put("user_id",b.getUser_id().getUser_id());
	        	sch.put("batch",b.getUser_id().getBatch_id().getBatch_number());
	        	sch.put("role",b.getUser_id().getRole_id().getRole_name());
	        	sch.put("seat_number",b.getSeat_number());
	        	passPer.add(sch);	
			}
			Map<String, Object> driver = new HashMap<String,Object>();
			driver.put("bus_driver", bus.get(i).getUser_id().getFullname());
			driver.put("bus_model", bus.get(i).getBus_id().getBus_model());
			driver.put("departure_time", bus.get(i).getEst_departure_time());
			driver.put("arrival_time", bus.get(i).getEst_arrival_time());
			driver.put("passenger", passPer);
			passPer.removeAll(pa);
			passPerDriver.add(driver);
			
		}
		return passPerDriver;
		}
	

	@RequestMapping(value="/booking",method = RequestMethod.POST)
	public @ResponseBody Boolean getBooking(@RequestBody Teacher[] book){
		Teacher_Implement sl= new Teacher_Implement();
		sl.BookService(book);
		return true;
	}
	
	
	@RequestMapping(value="/driverSchedule",method = RequestMethod.GET)
	public @ResponseBody List<Map> getDriverSchedule(){
		List<Map> bus_per = new ArrayList<Map>();
		List<Bus_Per_Schedule> schedule = new ArrayList<Bus_Per_Schedule>();
		Teacher_Implement sl= new Teacher_Implement();
		schedule = sl.getdriverSche(); 
		for(int i=0;i<schedule.size();i++){
        	Map<String,Object> sch = new HashMap<String,Object>();
        	sch.put("date_of_travel",schedule.get(i).getSchedule_id().getDate_of_travel());
        	sch.put("destination_name",schedule.get(i).getSchedule_id().getDestination_id().getDestination_name());
        	sch.put("bus_model",schedule.get(i).getBus_id().getBus_model());
        	sch.put("driver_name",schedule.get(i).getUser_id().getFullname());
        	sch.put("total_available_seats",schedule.get(i).getSchedule_id().getTotal_available_seats());
        	sch.put("customer_seats",schedule.get(i).getSchedule_id().getCustomer_seats());
        	sch.put("staff_seats",schedule.get(i).getSchedule_id().getStaff_seats());
        	sch.put("student_seats",schedule.get(i).getSchedule_id().getStudent_seats());
        	sch.put("est_departure_time",schedule.get(i).getEst_departure_time());
        	sch.put("est_arrival_time",schedule.get(i).getEst_arrival_time());
        	sch.put("bus_per_schedule_id",schedule.get(i).getBus_per_schedule_id());
        	bus_per.add(sch);
        }
		return bus_per;
	}
	
	
	//Use for mobile teacher/student detail also 
	@RequestMapping(value="/driverGetDetail",method = RequestMethod.POST)
	public @ResponseBody List<Map> driverGetDetail(@RequestBody String bus_id){
		System.out.println(bus_id);
		List<Map> passReturn = new ArrayList<Map>();
		List<Bus_Per_Schedule> bus = new ArrayList<Bus_Per_Schedule>();
		Teacher_Implement sl= new Teacher_Implement();
		bus = sl.getDetail(bus_id); 
		Set<Passenger> pa=new HashSet<Passenger>(); 
		for(int i=0;i<bus.size();i++){
			pa = bus.get(i).getPassenger();
			for(Passenger b:pa){
				Map<String,Object> sch = new HashMap<String,Object>();
	        	sch.put("passenger_name",b.getUser_id().getFullname());
	        	sch.put("batch",b.getUser_id().getBatch_id().getBatch_number());
	        	sch.put("role",b.getUser_id().getRole_id().getRole_name());
	        	sch.put("seat_number",b.getSeat_number());
	        	passReturn.add(sch);
			}
		}
		System.out.println(passReturn);
		return passReturn;
		}
	
	@RequestMapping(value="/req_donate",method = RequestMethod.GET)
	public @ResponseBody List<Map> get_req_donate(){
		List<Batch_Master> bat = new ArrayList<Batch_Master>();
		List<Map> batReturn = new ArrayList<Map>();
		StudentDao sl= new Student_Implement();
		bat=sl.Batch();
		for(int i=0;i<bat.size();i++){
        	Map<String,Object> dests = new HashMap<String,Object>();
        	dests.put("batch_id", bat.get(i).getBatch_id());
        	dests.put("batch_number", bat.get(i).getBatch_number());
        	batReturn.add(dests);
        }
		System.out.println(batReturn);
		return batReturn;
	}
	
	@RequestMapping(value="/req_donate_username",method = RequestMethod.POST)
	public @ResponseBody List<Map<String,Object>> get_req_donate_username(@RequestBody Donate donate){
		List<User_Master> user = new ArrayList<User_Master>();
		StudentDao admin = new Student_Implement();
		user = admin.Bat_User(donate);
		List<Map<String,Object>> userList = new ArrayList<Map<String,Object>>();
		for(int i=0;i<user.size();i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("user_id", user.get(i).getUser_id());
			map.put("username", user.get(i).getUsername());
			userList.add(map);	
		}
		return userList;
			
	}
	@RequestMapping(value="/start_donate",method = RequestMethod.POST)
	public @ResponseBody Boolean get_start_donate(@RequestBody Donate donate){
		System.out.println(donate.getDonate_to());
		System.out.println(donate.getReceive_from());
		System.out.println(donate.getNo_ticket());
		StudentDao stu = new Student_Implement();
		Boolean ret = stu.donate(donate);
		return ret;
			
	}

	
	
	/*
	@RequestMapping(value="/device",method = RequestMethod.GET)
	public ModelAndView Device( HttpServletRequest request){
			ModelAndView view =new ModelAndView("device");
		if(request.getHeader("User-Agent").indexOf("Mobile") != -1) {
		   view.addObject("device", "mobile");
		  } else {
		   view.addObject("device", "pc");
		  }
		return view;
	}
	@RequestMapping(value="/driver",method = RequestMethod.GET)
	public ModelAndView Driver(){
		ModelAndView view =new ModelAndView("driver");
		view.addObject("name", "world");
		return view;
	}*/

	
	
	
}
	





	

	
	






	