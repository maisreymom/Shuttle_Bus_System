package com.MainController;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



















import com.DaoClasses.Diver_Impl;
import com.DaoClasses.Driver_Inf;
import com.DaoClasses.StudentDao;
import com.DaoClasses.Student_Implement;
import com.DaoClasses.TeacherDao;
import com.DaoClasses.Teacher_Implement;
import com.EntityClasses.Authentic;
import com.EntityClasses.Batch_Master;
import com.EntityClasses.Bus_Per_Schedule;
import com.EntityClasses.Bus_Report_Table;
import com.EntityClasses.Destination_Master;
import com.EntityClasses.Exchange_Seat;
import com.EntityClasses.Passenger;
import com.EntityClasses.Permission;
import com.EntityClasses.Schedule_Table;
import com.EntityClasses.Ticket_Donation;
import com.EntityClasses.User_Master;
import com.ModelClasses.Cancel_Ticket;
import com.ModelClasses.Donate;
import com.ModelClasses.EmergencyBooking;
import com.ModelClasses.ExchangeSeat;
import com.ModelClasses.NotificationModel;
import com.ModelClasses.Booking;
import com.ServiceClasses.usersService;
import com.ModelClasses.PermissionRequest;


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
	
	//Driver Schedule for Mobile
	@RequestMapping(value="/mobileSchedule_Service",method = RequestMethod.GET)
	public @ResponseBody List<Map<String,Object>> getMobileSche_Service(){
		List<Map<String,Object>> bus_per = new ArrayList<Map<String,Object>>();
		List<Bus_Per_Schedule> schedule = new ArrayList<Bus_Per_Schedule>();
		Driver_Inf sl= new Diver_Impl();
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
	
	
	//student, teacher and driver
	@RequestMapping(value="/user_info",method = RequestMethod.POST)
	public @ResponseBody List<Map<String,Object>> getUserInfo(@RequestBody String user_id){
		List<Map<String,Object>> userReturn = new ArrayList<Map<String,Object>>();
		StudentDao sl= new Student_Implement();
		userReturn=sl.getUserInfo(user_id);
		
		return userReturn;
	}
	
	//teacher and student
	@RequestMapping(value="/selectService",method = RequestMethod.GET)
	public @ResponseBody List<Map<String,Object>> select_service(){
		List<Destination_Master> dest = new ArrayList<Destination_Master>();
		List<Map<String,Object>> destReturn = new ArrayList<Map<String,Object>>();
		TeacherDao sl= new Teacher_Implement();
		dest=sl.getDestinationId();
		for(int i=0;i<dest.size();i++){
        	Map<String,Object> dests = new HashMap<String,Object>();
        	dests.put("destination_name", dest.get(i).getDestination_name());
        	dests.put("destination_id", dest.get(i).getDestination_id());
        	destReturn.add(dests);
        }
		return destReturn;
	}
	
	//Check Confirm driver
	@RequestMapping(value="/checkConfirm",method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getCheckConfirm(@RequestBody String bus_id){
		Map<String,Object> bus_per = new HashMap<String,Object>();
		Driver_Inf sl= new Diver_Impl();
		bus_per=sl.checkConfim(bus_id);
		return bus_per;
	}
	//Check Leave Confirm driver
	@RequestMapping(value="/leaveConfirm",method = RequestMethod.POST)
	public @ResponseBody String getLeaveConfirm(@RequestBody String bus_id){
			Driver_Inf sl= new Diver_Impl();
			String bus_set=sl.leaveConfim(bus_id);
			return bus_set;
	}
	//Check arrived Confirm driver
	@RequestMapping(value="/arriveConfirm",method = RequestMethod.POST)
	public @ResponseBody String getArrivedConfirm(@RequestBody String bus_id){
			Driver_Inf sl= new Diver_Impl();
			String bus_set=sl.arrivedConfim(bus_id);
			return bus_set;
	}
	
	//Schedule same for both Student and Teacher
	@RequestMapping(value="/scheduleData",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getscheduleData(){
		List<Map<String, Object>> scheReturn = new ArrayList<Map<String, Object>>();
		List<Schedule_Table> schedule = new ArrayList<Schedule_Table>();
		TeacherDao sl= new Teacher_Implement();
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
		return scheReturn;
	}
	
	//Schedule same for both Student and Teacher
	@RequestMapping(value="/passengerDetail",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getpassengerDetail(@RequestBody String sch_id){
		List<Map<String,Object>> passPerDriver = new ArrayList<Map<String, Object>>();
		TeacherDao sl= new Teacher_Implement();
		passPerDriver = sl.getPassengerDetail(sch_id); 
		return passPerDriver;
		}
	
	//Booking for both student and teacher
	@RequestMapping(value="/booking",method = RequestMethod.POST)
	public @ResponseBody Boolean getBooking(@RequestBody Booking[] book){
		TeacherDao sl= new Teacher_Implement();
		sl.BookService(book);
		return true;
	}
	
	//driver schedule for computer
	@RequestMapping(value="/driverSchedule",method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getDriverSchedule(){
		System.out.println("Hello");
		List<Map<String, Object>> bus_per = new ArrayList<Map<String, Object>>();
		List<Bus_Per_Schedule> schedule = new ArrayList<Bus_Per_Schedule>();
		Driver_Inf sl= new Diver_Impl();
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
	
	
	//Use for Driver
	@RequestMapping(value="/driverGetDetail",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> driverGetDetail(@RequestBody String bus_id){
		List<Map<String, Object>> passReturn = new ArrayList<Map<String, Object>>();
		Driver_Inf sl= new Diver_Impl();
		passReturn = sl.getDetail(bus_id); 
		return passReturn;
		}
	
	
	@RequestMapping(value="/req_donate",method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> get_req_donate(){
		List<Batch_Master> bat = new ArrayList<Batch_Master>();
		List<Map<String, Object>> batReturn = new ArrayList<Map<String, Object>>();
		StudentDao sl= new Student_Implement();
		bat=sl.Batch();
		for(int i=0;i<bat.size();i++){
        	Map<String,Object> dests = new HashMap<String,Object>();
        	dests.put("batch_id", bat.get(i).getBatch_id());
        	dests.put("batch_number", bat.get(i).getBatch_number());
        	batReturn.add(dests);
        }
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
		StudentDao stu = new Student_Implement();
		Boolean ret = stu.donate(donate);
		return ret;
			
	}
	
	@RequestMapping(value="/start_exchange_seat",method = RequestMethod.POST)
	public @ResponseBody Boolean get_start_exchange_seat(@RequestBody ExchangeSeat exch){
		StudentDao stu = new Student_Implement();
		Boolean ret = stu.exchange(exch);
		return ret;
			
	}
	
	@RequestMapping(value="/list_student_permission",method = RequestMethod.GET)
	public @ResponseBody List<Map<String,Object>> list_student_permission(){
		System.out.println("Student Permission");
		TeacherDao stu = new Teacher_Implement();
		List<Map<String,Object>> ret = stu.list_student_permission();
		return ret;	
	}
	
	@RequestMapping(value="/check_exchange",method = RequestMethod.POST)
	public @ResponseBody Boolean check_exchange(@RequestBody String user_id){
		StudentDao stu = new Student_Implement();
		Boolean ret = stu.check_exchange(user_id);
		return ret;	
	}
	
	@RequestMapping(value="/checkEmergency",method = RequestMethod.POST)
	public @ResponseBody List<Map<String,Object>> checkEmergency(@RequestBody String user_id){
		List<Map<String,Object>> scheReturn = new ArrayList<Map<String,Object>>();
		List<Schedule_Table> schedule = new ArrayList<Schedule_Table>();
		StudentDao sl= new Student_Implement();
		schedule = sl.checkSchduleEmer(user_id); 
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
		return scheReturn;
	}
	
	
	@RequestMapping(value="/emergency_booking",method = RequestMethod.POST)
	public @ResponseBody Boolean emergency_booking(@RequestBody EmergencyBooking book){
		StudentDao stu = new Student_Implement();
		Boolean ret = stu.emergencyBooking(book);
		return ret;
			
	}
	
	@RequestMapping(value="/permission_request",method = RequestMethod.POST)
	public @ResponseBody Boolean permission_request(@RequestBody PermissionRequest pr){
		StudentDao stu = new Student_Implement();
		Boolean ret = stu.permission_request(pr);
		return ret;
			
	}
	
	@RequestMapping(value="/cancel_ticket",method = RequestMethod.POST)
	public @ResponseBody Boolean cancel_ticket(@RequestBody Cancel_Ticket cancel){
		StudentDao stu = new Student_Implement();
		Boolean ret=stu.cancel_ticket(cancel);
		return ret;	
	}
	
	@RequestMapping(value="/student_book_notification",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> bookNotification(@RequestBody NotificationModel noti){
		StudentDao stu = new Student_Implement();
		List<Map<String, Object>> pass=stu.booking_notification(noti);
		System.out.println(pass.size());
		return pass;	
	}
	
	@RequestMapping(value="/donate_notification",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> donate_notification(@RequestBody NotificationModel noti){
		StudentDao stu = new Student_Implement();
		List<Map<String, Object>> donate=stu.donate_notification(noti);
		System.out.println(donate);
		return donate;	
	}
	
	@RequestMapping(value="/emergency_notification",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> emergency_notification(@RequestBody NotificationModel noti){
		StudentDao stu = new Student_Implement();
		List<Map<String, Object>> emer_book=stu.emergency_notification(noti);
		System.out.println(emer_book);
		return emer_book;	
	}
	
	@RequestMapping(value="/exchange_seat_notification",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> exchange_seat_notification(@RequestBody NotificationModel noti){
		StudentDao stu = new Student_Implement();
		List<Map<String, Object>> exch_seat=stu.exchange_seat_notification(noti);
		return exch_seat;	
	}
	@RequestMapping(value="/student_permission",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> student_permission(@RequestBody NotificationModel noti){
		StudentDao stu = new Student_Implement();
		List<Map<String, Object>> permission=stu.permission_notification(noti);
		return permission;	
	}
	//for teach notification
	@RequestMapping(value="/permission_teacher_noti",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> permission_teacher_noti(@RequestBody NotificationModel noti){
		TeacherDao tea = new Teacher_Implement();
		List<Map<String, Object>>  ret = tea.permission_request_teacher(noti);
		return ret;
			
	}
	//for teach notification
		@RequestMapping(value="/teacher_confirm_permission",method = RequestMethod.POST)
		public @ResponseBody Boolean teacher_confirm_permission(@RequestBody PermissionRequest per){
			TeacherDao tea = new Teacher_Implement();
			Boolean  ret = tea.teacher_confirm_permission(per);
			return ret;
				
		}
		
		
		//driver schedule for computer
		@RequestMapping(value="/myschedule_driver",method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> myschedule_driver(@RequestBody String driver_id){
			System.out.println("Hello");
			List<Map<String, Object>> bus_per = new ArrayList<Map<String, Object>>();
			List<Bus_Per_Schedule> schedule = new ArrayList<Bus_Per_Schedule>();
			Driver_Inf sl= new Diver_Impl();
			schedule = sl.myschedule_driver(driver_id); 
			for(int i=0;i<schedule.size();i++){
	        	Map<String,Object> sch = new HashMap<String,Object>();
	        	sch.put("date_of_travel",schedule.get(i).getSchedule_id().getDate_of_travel());
	        	sch.put("destination_name",schedule.get(i).getSchedule_id().getDestination_id().getDestination_name());
	        	sch.put("bus_model",schedule.get(i).getBus_id().getBus_model());
	        	sch.put("driver_name",schedule.get(i).getUser_id().getFullname());
	        	sch.put("est_departure_time",schedule.get(i).getEst_departure_time());
	        	sch.put("est_arrival_time",schedule.get(i).getEst_arrival_time());
	        	sch.put("bus_per_schedule_id",schedule.get(i).getBus_per_schedule_id());
	        	bus_per.add(sch);
	        }
			return bus_per;
		}
	/*
	@RequestMapping(value="/student_book_notification",method = RequestMethod.POST)
	public @ResponseBody List<List<Map<String, Object>>> donate_notification(@RequestBody String user_id){
		StudentDao stu = new Student_Implement();
		List<List<Map<String, Object>>> notification = new ArrayList<List<Map<String, Object>>>();
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		List<Passenger> pass=stu.booking_notification(user_id);
		List<Ticket_Donation> tic_donate =stu.donate_notification(user_id);
		List<Exchange_Seat> exch_seat =stu.exchange_seat_notification(user_id);
		List<Permission> permission =stu.permission_notification(user_id);
		List<Schedule_Table> sch=new ArrayList<Schedule_Table>();
		if(pass.size()!=0){
			for(int i=0;i<pass.size();i++){
				Map<String,Object> passenger = new HashMap<String,Object>();
				if(pass.get(i).getBus_per_schedule_id()!=null){
					
				}else{
					sch=stu.checkSchedule(pass.get(i).getDestination_id().toString(),pass.get(i).getDate_of_travel());
					if(sch.size()==0){
						//waiting for booking approve
					}else{
						//Booking Conflict
					}
				}
			}
		}
		if(tic_donate.size()!=0){
			//this user have donated from other
		}
		if(exch_seat.size()!=0){
			//this user have other exchange seat to
		}
		for(int i=0;i<permission.size();i++){
			if(permission.size()!=0){
				if(permission.get(i).getStatus().equals("true")){
					//permission approved
				}else if(permission.get(i).getCreated_at().equals(permission.get(i).getUpdated_at())){
					//permission waiting for approved
				}else{
					//permission ignore
				}
			}
		}
		
		return null;
			
	}*/
	
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
	





	

	
	






	