package com.DaoClasses;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.Time;

import java.text.DateFormat;


import java.text.DateFormat;


import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;









import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import org.springframework.stereotype.Repository;

import org.springframework.scheduling.annotation.Scheduled;


import com.EntityClasses.Batch_Master;
import com.EntityClasses.Bus_Master;
import com.EntityClasses.Bus_Per_Schedule;
import com.EntityClasses.Bus_Report_Table;
import com.EntityClasses.Destination_Master;
import com.EntityClasses.Emergency_Request;
import com.EntityClasses.Passenger;
import com.EntityClasses.Role_Master;
import com.EntityClasses.Schedule_Table;
import com.EntityClasses.User_Master;
import com.HibernateUtil.HibernateUtil;
import com.ModelClasses.Add_Bus;

import com.ModelClasses.EMR;

import com.ModelClasses.BatchUpdate;
import com.ModelClasses.Destination;

import com.ModelClasses.Model_User;
import com.ModelClasses.Report_Date;
import com.ModelClasses.Set_Schedule;
import com.ModelClasses.Shuttle;
import com.mysql.fabric.xmlrpc.base.Data;
@SuppressWarnings("unused")
@Repository
public class Admin_Imp implements Admin_Inf{
	
	
	
	public String dateFormate(java.util.Date date){
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    System.out.println(sdf.format(cal.getTime())); 
	    String today=sdf.format(cal.getTime());
		return today;
		
	}
	
	public String nowDate() {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String today=sdf.format(cal.getTime());
	    return today;

	}
	public static String nowDateTime() {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String today=sdf.format(cal.getTime());
	    return today;

	}

	public List<Map<String,Object>> User() {
	    List<User_Master> user = new  ArrayList<User_Master> ();
	    List<Map<String,Object>> userList = new ArrayList<Map<String,Object>>(); 
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String hql ="FROM User_Master";
            Query query =  session.createQuery(hql);
            user = query.list();
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
        } catch (RuntimeException e) {
            e.printStackTrace();
           
        } finally {
            session.flush();
            session.close();
        }
        
        return userList;
    }
	
	public List<Map<String,Object>> Schedule_Bus() {
	   List<Bus_Master> bus = new  ArrayList<Bus_Master> ();
       Transaction trns = null;
    
       Session session = HibernateUtil.getSessionFactory().openSession();
    	List<Map<String,Object>> schedule_busList = new ArrayList<Map<String,Object>>();
       try {
           trns = session.beginTransaction();
           String hql ="FROM Bus_Master B where B.status='true'";
           Query query =  session.createQuery(hql);
       
			
           
           bus = query.list();
           
   		
   		
   		for(int i=0;i<bus.size();i++){
   			Map<String,Object> map = new HashMap<String,Object>();
   			map.put("no_seats", bus.get(i).getNo_of_seat());
   			map.put("bus_model", bus.get(i).getBus_model());
   			map.put("image", bus.get(i).getBus_image());
   			map.put("plate_number", bus.get(i).getPlate_number());
   			map.put("bus_id", bus.get(i).getBus_id());
   			schedule_busList.add(map);
   			
   		}
   			
 
       } catch (RuntimeException e) {
           e.printStackTrace();
          
       } finally {
           session.flush();
           session.close();
       }
       
       return schedule_busList;
   }
	
	
	public List<Map<String,Object>> Schedule_Report(Report_Date rep) {
		   Admin_Imp ad = new Admin_Imp();
		   List<Schedule_Table> list = new  ArrayList<Schedule_Table> ();
		   List<Map<String,Object>> report_List = new ArrayList<Map<String,Object>>();
	       Transaction trns = null;
	       Session session = HibernateUtil.getSessionFactory().openSession();
	       
	       try {
	           trns = session.beginTransaction();
	           
	           String hql ="FROM Schedule_Table st where st.date_of_travel  BETWEEN '"
	           +rep.getDate_from()+"' AND '"+rep.getDate_to()+"'";
	           Query query =  session.createQuery(hql);
	       
				
	           
	           list = query.list();
	           
	           for(int i=0;i<list.size();i++){
	   			
	   			Map<String,Object> map = new HashMap<String,Object>();
	   			List<String> driver_list = new ArrayList<String>();
	   			List<Integer> total_list = new ArrayList<Integer>();
	   			List<Integer> student_list = new ArrayList<Integer>();
	   			List<Integer> staff_list = new ArrayList<Integer>();
	   			List<Integer> customer_list = new ArrayList<Integer>();
	   			List<Integer> remaining_list = new ArrayList<Integer>();
	   			List<String> bus_list = new ArrayList<String>();
	   			List<String> time_list = new ArrayList<String>();
	   			map.put("date", list.get(i).getDate_of_travel());
	   			map.put("destination", list.get(i).getDestination_id().getDestination_name());
	   			map.put("total_seats", list.get(i).getTotal_available_seats());
	   			map.put("student", list.get(i).getStudent_seats());
	   			map.put("staff", list.get(i).getStaff_seats());
	   			map.put("customer", list.get(i).getCustomer_seats());
	   			map.put("mount_bus", list.get(i).getBus_per_schedule().size());
	   			for(Bus_Per_Schedule driver : list.get(i).getBus_per_schedule()){
	   				driver_list.add(driver.getUser_id().getFullname());
	   			}
	   			map.put("driver", driver_list);
	   			
	   			for(Bus_Per_Schedule total_seat : list.get(i).getBus_per_schedule()){
	   				total_list.add(total_seat.getNumber_of_seats());
	   			}
	   			map.put("total_seats", total_list);
	   			
	   			for(Bus_Per_Schedule student : list.get(i).getBus_per_schedule()){
	   				
	   				student_list.add(student.getNo_of_student());
	   			}
	   			map.put("student", student_list);
	   			
	   			for(Bus_Per_Schedule staff : list.get(i).getBus_per_schedule()){
	   				
	   				staff_list.add(staff.getNo_of_staff());
	   			}
	   			map.put("staff", staff_list);
	   			
	   			for(Bus_Per_Schedule customer : list.get(i).getBus_per_schedule()){
	   				
	   				customer_list.add(customer.getNo_of_customer());
	   			}
	   			map.put("customer", customer_list);
	   			
	   			for(Bus_Per_Schedule remaining : list.get(i).getBus_per_schedule()){
	   				
	   				remaining_list.add(remaining.getRemaining_seats());
	   			}
	   			map.put("remaining", remaining_list);
	   			
	   			
	   			for(Bus_Per_Schedule bus : list.get(i).getBus_per_schedule()){
	   				
	   				bus_list.add(bus.getBus_id().getBus_model());
	   			}	
	   			
	   			map.put("bus", bus_list);
	   			
	   			
	   			map.put("total_time", time_list);
	   			
	   			
	   			report_List.add(map);
	   			
	   			
	   		}
	   			
	 
	       } catch (RuntimeException e) {
	           e.printStackTrace();
	          
	       } finally {
	    	   
	           session.flush();
	           session.close();
	       }
	       
	       return report_List;
	   }
	
	public List<Map<String,Object>> Schedule() {
		   List<Schedule_Table> list = new  ArrayList<Schedule_Table> ();
	       Transaction trns = null;
	       Session session = HibernateUtil.getSessionFactory().openSession();
	       List<Map<String,Object>> list_sh = new ArrayList<Map<String,Object>>();
	       try {
	    	   int total_seats=0;
	           trns = session.beginTransaction();
	           String hql ="FROM Schedule_Table";
	           Query query =  session.createQuery(hql);
               list = query.list();
	           for(int i=0;i<list.size();i++){
	        	   Map<String, Object> map = new HashMap<String,Object>();
	        	   map.put("schedule_id", list.get(i).getSchedule_id());
	        	   
	        	   for(Bus_Per_Schedule bps : list.get(i).getBus_per_schedule()){
	        		   if(bps.getCustomer_only().equals("false")){
	        			   total_seats = total_seats + bps.getNumber_of_seats();
	        		   }
	        		   
	        	   }
	        	   map.put("total_seats", total_seats);
	        	   total_seats = 0;
	        	   
	        	   
	        	   list_sh.add(map);
	           }
	 
	       } catch (RuntimeException e) {
	           e.printStackTrace();
	          
	       } finally {
	           session.flush();
	           session.close();
	       }
	       
	       return list_sh;
	   }
	
	
	public  boolean Add_Shuttle (Add_Bus add_bus) {
		 	Transaction trns = null;
	        Bus_Master add = new Bus_Master();
	        Admin_Imp ad = new Admin_Imp();
			String key = ad.Key(15);
	        add.setBus_id(key);
	        add.setBus_model(add_bus.getBus_model());
	        add.setPlate_number(add_bus.getPlate_number());
	        add.setNo_of_seat(add_bus.getTotal_seats());
	        add.setBus_image("http://helloworld.jpg");
	        
		    Configuration con=new Configuration();
	        con.configure("hibernate.cfg.xml");
	     	SessionFactory sf=con.buildSessionFactory();
	     	Session session=sf.openSession();
	        try {
	            trns = session.beginTransaction();
	            session.save(add);
	            session.getTransaction().commit();
	            return true;
	        } catch (RuntimeException e) {
	            if (trns != null) {
	                trns.rollback();
	            }
	            e.printStackTrace();
	            return false;
	        } finally {
	           
	            session.close();
	        }
		
	    }
	
	public List<Batch_Master> Batch() {
		   List<Batch_Master> list = new  ArrayList<Batch_Master> ();
	       Transaction trns = null;
	       Session session = HibernateUtil.getSessionFactory().openSession();
	       
	       try {
	           trns = session.beginTransaction();
	           String hql ="FROM Batch_Master";
	           Query query =  session.createQuery(hql);
	           list = query.list();
	 
	 
	       } catch (RuntimeException e) {
	           e.printStackTrace();
	          
	       } finally {
	           session.flush();
	           session.close();
	       }
	       
	       return list;
	   }
	
	public List<Role_Master> Role() {
		   List<Role_Master> list = new  ArrayList<Role_Master> ();
	       Transaction trns = null;
	       Session session = HibernateUtil.getSessionFactory().openSession();
	       
	       try {
	           trns = session.beginTransaction();
	           String hql ="FROM Role_Master";
	           Query query =  session.createQuery(hql);
	           list = query.list();
	 
	 
	       } catch (RuntimeException e) {
	           e.printStackTrace();
	          
	       } finally {
	           session.flush();
	           session.close();
	       }
	       
	       return list;
	   }
	
	public  boolean Add_User (Model_User add) {
	 	Transaction trns = null;
        User_Master user = new User_Master();
        Admin_Imp ad = new Admin_Imp();
		String key = ad.Key(15);
		Batch_Master batch = new Batch_Master();
		Role_Master role = new Role_Master();
        user.setUsername(add.getLname());
        user.setEmail(add.getEmail());
        user.setFullname(add.getFname()+" "+add.getLname());
        user.setUser_id(key);
        user.setGender(add.getGender());
        user.setNo_of_ticket(18);
        user.setPassword(add.getPassword());
        user.setPhone_number(add.getPhone_number());
        batch.setBatch_id(add.getBatch());
        role.setRole_id(add.getRole());
        user.setBatch_id(batch);
        user.setRole_id(role);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	
        	
            trns = session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
           
            session.close();
        }
	
    }
	
	public  boolean Edit_Bus (Add_Bus add) {
	 	Transaction trns = null;
	 		
	 	 Session session = HibernateUtil.getSessionFactory().openSession();
     	
        try {
        	
        	 trns = session.beginTransaction();
	           
	           Bus_Master p = (Bus_Master) session.get(Bus_Master.class,add.getBus_id());
	           if(!add.getBus_model().equals("not_update")){
	        	   p.setBus_model(add.getBus_model());
	           }
	           if(!add.getPlate_number().equals("not_update")){
	        	   p.setPlate_number(add.getPlate_number());
	           }
	           if(!(add.getTotal_seats()==0)){
	        	   p.setNo_of_seat(add.getTotal_seats());
	           }
	           session.update(p);
	           session.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
           
            session.close();
        }
	
    }
	
	public  boolean Delete_Bus (String delete_id) {
	 	Transaction trns = null;
	 		
	 	Session session = HibernateUtil.getSessionFactory().openSession();
     	
        try {
        	
        	 trns = session.beginTransaction();
	         
        	 Bus_Master bus = (Bus_Master) session.get(Bus_Master.class,delete_id);
	           bus.setStatus("false");
	           session.update(bus);
	           session.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
           
            session.close();
        }
	
    }
	
	public  List<User_Master> Driver() {
	 	Transaction trns = null;
	 		
	 	Session session = HibernateUtil.getSessionFactory().openSession();
     	List<User_Master> results = new ArrayList<User_Master>();
        try {
        	
        	 trns = session.beginTransaction();
        	 String hql = "FROM User_Master U WHERE U.role_id.role_name = :employee_id";
         	Query query = session.createQuery(hql);
         	query.setParameter("employee_id","driver");
         	results =  query.list();
         	
 	       
	        session.getTransaction().commit();
            return results;
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
           
        } finally {
           
            session.close();
        }
        return results;
	
    }
	public boolean setSchedule (Set_Schedule[] set) {
	 	Transaction trns = null;
	 	Session session = HibernateUtil.getSessionFactory().openSession();
     	
        Admin_Imp ad = new Admin_Imp();
        List<Set<Bus_Per_Schedule>> ls = new ArrayList<Set<Bus_Per_Schedule>>();
		String schedule_id = ad.Key(15);
		
		int Total_available_seats=0;
		int remaining_seats=0;
		try {
			
			for(int i=0;i<set.length;i++){
				Total_available_seats = Total_available_seats+set[i].getTotal_seats();
				remaining_seats = remaining_seats + set[i].getRemaining_seats();
			}
			
			Schedule_Table schedule = new Schedule_Table();
			
			User_Master user = new User_Master();
			Bus_Master bm = new Bus_Master();
			Destination_Master dm = new Destination_Master();
			
			
			
			dm.setDestination_id(set[0].getDestination_id());
			
			
			
			
			schedule.setSchedule_id(schedule_id);
			schedule.setDate_of_travel(Date.valueOf(ad.Date(set[0].getDate_of_travel())));
			schedule.setDestination_id(dm);
			schedule.setStudent_seats(set[0].getStudent());
			schedule.setStaff_seats(set[0].getStaff());
			schedule.setCustomer_seats(set[0].getCustomer());
			schedule.setTotal_available_seats(Total_available_seats);
			schedule.setRemaining_seats(remaining_seats);
			
			
			
			Set<Bus_Per_Schedule> set_bps = new HashSet<Bus_Per_Schedule>();
			
			Map<String,List<String>> map = new HashMap<String,List<String>>(); 
			List<String> id = new ArrayList<String>();
			for(int i=0;i<set.length;i++){
				
				
				Bus_Per_Schedule bps = new Bus_Per_Schedule();
				
				String bps_id=ad.Key(15);
				bm.setBus_id(set[i].getBus_id());
				user.setUser_id(set[i].getDriver_name());
				bps.setSchedule_id(schedule);
				bps.setBus_id(bm);
				bps.setBus_per_schedule_id(bps_id);
				bps.setNumber_of_seats(set[i].getTotal_seats());
				bps.setUser_id(user);
				bps.setEst_arrival_time(Time.valueOf(set[i].getEst_arrival()+":00"));
				bps.setEst_departure_time(Time.valueOf(set[i].getEst_departure()+":00"));
				bps.setCustomer_only(set[i].getCustomer_only());
				
				id.add(bps_id);
				map.put(bps_id, set[i].getPassenger_id());
				
				set_bps.add(bps);
			}
			
			schedule.setBus_per_schedule(set_bps);
			
		    
		        trns = session.beginTransaction();
		        
		        session.save(schedule);
		    	
		        
		        //ad.setBus(map,ad.Date(set[0].getDate_of_travel()),set[0].getDestination_id());
		        		//ad.setBusReport(id);
		        
		        /*for(String bp : map.keySet()){
	         		if(map.get(bp).size()>0){
	         		
	        			for(int i=0;i<map.get(bp).size();i++){
	        				
	        				String hql = "update Passenger p set p.bus_per_schedule_id='"+bp+"'"+","
	        			    + " p.ticket_qrcode='"+map.get(bp).get(i)+ad.Key(15)
	        			    +"' where p.user_id ='"+map.get(bp).get(i)+"'"
	        				+" and p.date_of_travel='"+ad.Date(set[0].getDate_of_travel())
	        				+"' and p.destination_id.destination_id='"+set[0].getDestination_id()+"'";
	        				Query query = session.createQuery(hql);
	            			
	                   	 	query.executeUpdate();
	        			}
	        	
	         		}
	         		
	    		}*/
		        session.getTransaction().commit();
		        return ad.setBus(map,ad.Date(set[0].getDate_of_travel()),
		        		set[0].getDestination_id(),schedule.getSchedule_id())&&ad.setBusReport(id);
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
	
    }
	
	public boolean editSchedule (Set_Schedule[] edit) {
	 	Transaction trns = null;
	 	Session session = HibernateUtil.getSessionFactory().openSession();
     	Admin_Imp ad = new Admin_Imp();
     	Schedule_Table ls_st = new Schedule_Table();
     	
      	String hql = "From Schedule_Table st where st.date_of_travel='"
     	+ ad.Date(edit[0].getDate_of_travel()) +"' and st.destination_id='"+edit[0].getDestination_id()+"'";
 	    Query qpass = session.createQuery(hql);
 	    ls_st = (Schedule_Table) qpass.list().get(0);
 	    List<String> insert_report = new ArrayList<String>();
 	    List<String> bps_drop = new ArrayList<String>();
 	    List<String> bps_insert = new ArrayList<String>();
 	    List<String> pass_drop = new ArrayList<String>();
 	    List<String> pass_insert = new ArrayList<String>();
 	    List<Bus_Per_Schedule> list_bps = new ArrayList<Bus_Per_Schedule>(ls_st.getBus_per_schedule());
 	    Map<String,List<String>> map = new HashMap<String,List<String>>();
 	    Set<Bus_Per_Schedule> set_bps = new HashSet<Bus_Per_Schedule>();
 	    int total_seats = 0;
 	    int total_pass = 0;
 	    int total_student=0;
 	    int total_staff=0;
 	    int total_customer=0;
 	   
 	   
 	 	
 	   for(int i=0;i<edit.length;i++){
 		   total_seats = total_seats + edit[i].getTotal_seats();
 		   total_student = total_student + edit[i].getStudent();
 		   total_staff = total_staff + edit[i].getStaff();
 		   total_customer = total_customer + edit[i].getCustomer();
 		   
 	   }
 	   total_pass = total_student + total_staff + total_customer;
 	  
 	   if((ls_st.getTotal_available_seats()-ls_st.getRemaining_seats())>total_pass){
 		  
 		   for(Bus_Per_Schedule bps : ls_st.getBus_per_schedule()){
 			   for(Passenger ps : bps.getPassenger()){
 				   pass_insert.add(ps.getUser_id().getUser_id());
 			   }
 		   }
 		   
 		  for(int i=total_pass;i<pass_insert.size();i++){
			   pass_drop.add(pass_insert.get(i));
		   }
 		 if(pass_drop.size()>0){
 			ad.resetBus(pass_drop, ad.Date(edit[0].getDate_of_travel()),edit[0].getDestination_id());
 		 }
 		 
 	   }
 	   
 	  if(ls_st.getBus_per_schedule().size()>edit.length){
 	 	   
	    	for(int i=0;i<ls_st.getBus_per_schedule().size();i++){
	    		if(i<edit.length){
	    			bps_insert.add(list_bps.get(i).getBus_per_schedule_id());
	    		}
	    		else{
	    			bps_drop.add(list_bps.get(i).getBus_per_schedule_id());
	    		}
	    	}
	    	
	    	ad.resetBusPerSchedule(bps_drop);
	    	
	    }
	    else if(ls_st.getBus_per_schedule().size()<edit.length){
	    	int size = ls_st.getBus_per_schedule().size();
	    	for(Bus_Per_Schedule bps : ls_st.getBus_per_schedule()){
	    		bps_insert.add(bps.getBus_per_schedule_id());
	    	}
	    	for(int i=size;i<edit.length;i++){
	    		bps_insert.add(ad.Key(15));
	 	    }
	    	
	    }
	    else {
	    	for(Bus_Per_Schedule bps : ls_st.getBus_per_schedule()){
	    		bps_insert.add(bps.getBus_per_schedule_id());
	    	}
	    }
 	  
 	   ls_st.setCustomer_seats(total_customer);
 	   ls_st.setStudent_seats(total_student);
 	   ls_st.setStaff_seats(total_staff);
 	   ls_st.setRemaining_seats(total_seats-total_customer-total_student-total_staff);
 	   ls_st.setTotal_available_seats(total_seats);
 	   
 	   for(int i=0;i<edit.length;i++){
 		   Bus_Master bus = new Bus_Master();
 		   User_Master user = new User_Master();
 		   Bus_Per_Schedule bps = new Bus_Per_Schedule();
 		   if(i<ls_st.getBus_per_schedule().size()){
 			  bps = (Bus_Per_Schedule) session.load(Bus_Per_Schedule.class, bps_insert.get(i));
 		   }
 		   else{
 			   bps.setBus_per_schedule_id(bps_insert.get(i));
 			   bps.setSchedule_id(ls_st);
 			   insert_report.add(bps_insert.get(i));
 		   }
 		   
 		   bus.setBus_id(edit[i].getBus_id());
		   user.setUser_id(edit[i].getDriver_name());
 		   bps.setBus_id(bus);
 		   bps.setEst_arrival_time(Time.valueOf(edit[i].getEst_arrival()+":00"));
 		   bps.setEst_departure_time(Time.valueOf(edit[i].getEst_departure()+":00"));
 		   bps.setNo_of_customer(edit[i].getCustomer());
 		   bps.setNo_of_staff(edit[i].getStaff());
 		   bps.setNo_of_student(edit[i].getStudent());
 		   bps.setNumber_of_seats(edit[i].getTotal_seats());
 		   bps.setCustomer_only(edit[i].getCustomer_only());
 		   bps.setRemaining_seats(bps.getNumber_of_seats()-
 				   bps.getNo_of_student()-bps.getNo_of_customer()-bps.getNo_of_staff());
 		   bps.setUser_id(user);
 		   
 		   map.put(bps_insert.get(i),edit[i].getPassenger_id());
 		   set_bps.add(bps);
 		   
 	   }
 	    ls_st.setBus_per_schedule(set_bps);
 	  
        try {
            trns = session.beginTransaction();
            
            session.update(ls_st);
        	session.getTransaction().commit();
            
            return ad.setBus(map,ad.Date(edit[0].getDate_of_travel()),edit[0].getDestination_id(),ls_st.getSchedule_id())
            		&&ad.setBusReport(insert_report);
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
	
    }
	
	
	public  boolean setBus(Map<String,List<String>> upd,String date,String dest,String schedule_id) {
	 	Transaction trns = null;
	 	Session session = HibernateUtil.getSessionFactory().openSession();
     	Admin_Imp ad = new Admin_Imp();
     	
     	
        try {
            trns = session.beginTransaction();
            for(String bp : upd.keySet()){
         		if(upd.get(bp).size()>0){
         		
        			for(int i=0;i<upd.get(bp).size();i++){
        				
        				String hql = "update Passenger p set p.bus_per_schedule_id='"+bp+"'"+","
        			    + " p.ticket_qrcode='"+upd.get(bp).get(i)+ad.Key(15)
        			    +"' where p.user_id ='"+upd.get(bp).get(i)+"'"
        				+" and p.date_of_travel='"+date+"' and p.destination_id.destination_id='"+dest+"'";
        				Query query = session.createQuery(hql);
            			
                   	 	query.executeUpdate();
        			}
        	
         		}
         		
    		}
            
        	session.getTransaction().commit();
           
            return true;
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            
            try {
            	Schedule_Table sch = new Schedule_Table();
                sch.setSchedule_id(schedule_id);
                session.delete(sch);
                session.getTransaction().commit();
            } catch (RuntimeException ex) {
            	
            }
            return false;
        } finally {
           
            session.close();
        }
	
    }
	
	public  boolean resetBus(List<String> bps_id,String date,String dest) {
	 	Transaction trns = null;
	 	Session session = HibernateUtil.getSessionFactory().openSession();
     	Admin_Imp ad = new Admin_Imp();	
     		
		
        try {
            trns = session.beginTransaction();
            String hql = "update Passenger p set p.bus_per_schedule_id=NULL, p.ticket_qrcode=NULL where p.user_id in (";
     		for(int i=0;i<bps_id.size();i++){
     			if(i<bps_id.size()-1){
     				hql = hql + "'" + bps_id.get(i) + "',";
     			}
     			else{
     				hql = hql + "'" + bps_id.get(i) + "') and p.date_of_travel='"
     			    +date+"' and p.destination_id.destination_id='"+dest+"'";
     			}
     	
     		}
			Query query = session.createQuery(hql);
        	query.executeUpdate();
            
        	session.getTransaction().commit();
           
            return true;
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
           
            session.close();
        }
	
    }
	
	public  boolean resetBusPerSchedule(List<String> bps_id) {
	 	Transaction trns = null;
	 	Session session = HibernateUtil.getSessionFactory().openSession();
     	
     		
     		
     		
		
        try {
            trns = session.beginTransaction();
            
            for(int i=0;i<bps_id.size();i++){
     			Bus_Per_Schedule bps = new Bus_Per_Schedule();
     			bps = (Bus_Per_Schedule) session.load(Bus_Per_Schedule.class, bps_id.get(i));
         		session.delete(bps);
     		}
        	session.getTransaction().commit();
           
            return true;
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
           
            session.close();
        }
	
    }
	public  boolean setBusReport(List<String> id) {
	 	Transaction trns = null;
	 	Session session = HibernateUtil.getSessionFactory().openSession();
     	
        try {
        	trns = session.beginTransaction();
        	for(int i=0;i<id.size();i++){
         		
         		Bus_Per_Schedule bps = new Bus_Per_Schedule();
             	Bus_Report_Table brt = new Bus_Report_Table();
             	bps.setBus_per_schedule_id(id.get(i));
             	brt.setBus_per_schedule(bps);
             	session.saveOrUpdate(brt);
             	
         	}
        	
        	session.getTransaction().commit();
           
            return true;
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
           
            session.close();
        }
	
    }
	public  boolean setEmergency(EMR[] confirm) {
	 	Transaction trns = null;
	 	Session session = HibernateUtil.getSessionFactory().openSession();
     	Admin_Imp ad = new Admin_Imp();
     	Schedule_Table sch = new Schedule_Table();
     	int mount=0;
     	int student=0;
     	int customer = 0;
     	int staff=0;
        int temp=0;
        
     	
        try {
            trns = session.beginTransaction();
            for(int i=0;i<confirm.length;i++){
         		if(confirm[i].getUser_id().size()>0){
         			
         			sch = (Schedule_Table) session.load(Schedule_Table.class,confirm[i].getSchedule_id());
             		mount = confirm[i].getUser_id().size();
             		sch.setRemaining_seats(sch.getRemaining_seats()-mount);
             		temp= 0;
             		for(Bus_Per_Schedule bps : sch.getBus_per_schedule()){
             			if(mount>0){
             				if(bps.getCustomer_only().equals("false")){
                 				if(bps.getRemaining_seats()>mount){
                 					bps.setRemaining_seats(bps.getRemaining_seats()-mount);
                 				
                 					for(int j=0;j<mount;j++){
                 						User_Master user = new User_Master();
                 						user = (User_Master) session.load(User_Master.class, confirm[i].getUser_id().get(j));
                 						
                 						if(user.getRole_id().getRole_name().equals("student")){
                 							student++;
                 							
                 						}
                 						else if(user.getRole_id().getRole_name().equals("customer")){
                 							customer++;
                 						}
                 						else if(user.getRole_id().getRole_name().equals("staff")){
                 							staff++;
                 						}
                 						
                 						Emergency_Request emr = new Emergency_Request();
                 			     		String hql = "From Emergency_Request er where er.user_id ='"
                 						+confirm[i].getUser_id().get(j)+"'";
                 				    	Query qpass = session.createQuery(hql);
                 				    	emr =  (Emergency_Request) qpass.list().get(0);
                 				    	
                 			     		emr.setStatus("confirmed");
                 			     		session.update(emr);
                 						confirm[i].getUser_id().remove(j);
                 						
                 						Passenger ps = new Passenger();
                 						ps.setBus_per_schedule_id(bps);
                 						ps.setUser_id(user);
                 						ps.setDate_of_travel(sch.getDate_of_travel());
                 						ps.setDestination_id(sch.getDestination_id());
                 						ps.setTicket_qrcode(user.getUser_id()
                 								+sch.getDestination_id().getDestination_id()+ad.Key(15));
                 						session.save(ps);
                 						
                 					}
                 					bps.setNo_of_customer(bps.getNo_of_customer()+customer);
                 					bps.setNo_of_staff(bps.getNo_of_staff()+staff);
                 					bps.setNo_of_student(bps.getNo_of_student()+student);
                 					
                 					sch.setCustomer_seats(sch.getCustomer_seats()+customer);
                 					sch.setStaff_seats(sch.getStaff_seats()+staff);
                 					sch.setStudent_seats(sch.getStudent_seats()+student);
                 				
                 					staff = 0;
                 					customer = 0;
                 					student = 0;
                 					mount =0;
                 			
                 				}
                 				else{
                 					bps.setRemaining_seats(0);
                 					mount = mount - bps.getRemaining_seats();
                 					for(int j=0;j<bps.getRemaining_seats();j++){
                 						User_Master user = new User_Master();
                 						user = (User_Master) session.load(User_Master.class, confirm[i].getUser_id().get(j));
                 						user.getRole_id().getRole_id();
                 						if(user.getRole_id().getRole_name().equals("student")){
                 							student++;
                 							
                 						}
                 						else if(user.getRole_id().getRole_name().equals("customer")){
                 							customer++;
                 						}
                 						else if(user.getRole_id().getRole_name().equals("staff")){
                 							staff++;
                 						}
                 						
                 						Emergency_Request emr = new Emergency_Request();
                 			     		String hql = "From Emergency_Request er where er.user_id ='"
                 						+confirm[i].getUser_id().get(j)+"'";
                 				    	Query qpass = session.createQuery(hql);
                 				    	emr =  (Emergency_Request) qpass.list().get(0);
                 				    	
                 			     		emr.setStatus("confirmed");
                 			     		session.update(emr);
                 			     		
                 						Passenger ps = new Passenger();
                 						ps.setBus_per_schedule_id(bps);
                 						ps.setUser_id(user);
                 						ps.setDate_of_travel(sch.getDate_of_travel());
                 						ps.setDestination_id(sch.getDestination_id());
                 						ps.setTicket_qrcode(user.getUser_id()
                 								+sch.getDestination_id().getDestination_id()+ad.Key(15));
                 						session.save(ps);
                 						confirm[i].getUser_id().remove(j);
                 					}
                 					bps.setNo_of_customer(bps.getNo_of_customer()+customer);
                 					bps.setNo_of_staff(bps.getNumber_of_seats()+staff);
                 					bps.setNo_of_student(bps.getNo_of_student()+student);
                 					
                 					sch.setCustomer_seats(sch.getCustomer_seats()+customer);
                 					sch.setStaff_seats(sch.getStaff_seats()+staff);
                 					sch.setStudent_seats(sch.getStudent_seats()+student);
                 					staff = 0;
                 					customer = 0;
                 					student = 0;
                 					
                 					
                 				}
                 				
                 			}
             			}
             			else{
             				
             				break;
             				
             			}
             			
             		}
             		session.update(sch);
         		}
         		
         		
         	}
        	session.getTransaction().commit();
           
            return true;
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
           
            session.close();
        }
	
    }
	public  boolean rejectEmergency(List<String> reject) {
	 	Transaction trns = null;
	 	Session session = HibernateUtil.getSessionFactory().openSession();
     	
     	
        try {
        	for(int i=0;i<reject.size();i++){
         		Emergency_Request emr = new Emergency_Request();
         		String hql = "From Emergency_Request er where er.user_id ='"+reject.get(i)+"'";
    	    	Query qpass = session.createQuery(hql);
    	    	emr =  (Emergency_Request) qpass.list().get(0);
    	    	
         		emr.setStatus("rejected");
         		session.update(emr);
         	}
            trns = session.beginTransaction();
          
            
        	session.getTransaction().commit();
           
            return true;
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
           
            session.close();
        }
	
    }
	public  List<Map<String,Object>> showSchedule() {
		
		   Session session = HibernateUtil.getSessionFactory().openSession();
	       Admin_Imp ad = new Admin_Imp();
	       Criteria criteria = session.createCriteria(Passenger.class);
	       criteria.add(Restrictions.ge("date_of_travel",ad.currentDate()));
	       criteria.setProjection(Projections.projectionList()
	               .add(Projections.groupProperty("date_of_travel").as("date_of_travel"))
	               .add(Projections.groupProperty("destination_id").as("destination_id"))
	               .add(Projections.property("date_of_travel").as("date_of_travel"))
	               .add(Projections.property("destination_id").as("destination_id")));
	       criteria.setResultTransformer(Transformers.aliasToBean(Passenger.class));
	       List<Passenger> list = criteria.list();
	       List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
	       try {
	       for (Passenger ps : list) {
	          
	    	   List<Passenger> pass = new ArrayList<Passenger>();
	    	   List<Passenger> cus = new ArrayList<Passenger>();
	    	   
	    	   String hql = "From Passenger P where P.destination_id.destination_name='"+ps.getDestination_id().getDestination_name()+"'"
	    			   +" and P.date_of_travel='"+ps.getDate_of_travel()+"' and P.user_id.role_id.role_name!='customer'";
	    	   Query qpass = session.createQuery(hql);
	    	   pass = qpass.list();
	    	   
	    	   String customer = "From Passenger P where P.destination_id.destination_name='"+ps.getDestination_id().getDestination_name()+"'"
	    			   +" and P.date_of_travel='"+ps.getDate_of_travel()+"' and P.user_id.role_id.role_name='customer'";
	    	   Query qcustomer = session.createQuery(customer);
	    	   cus = qcustomer.list();
	    	   
	           String group = "select count(E.user_id.role_id.role_name),  E.user_id.role_id.role_name "
	           		+"from Passenger E where E.destination_id.destination_name='" + ps.getDestination_id().getDestination_name()
	           		+"' and E.date_of_travel='"+ps.getDate_of_travel() +"' " 
	                +"GROUP BY E.user_id.role_id.role_name";
			       Query query = session.createQuery(group);
			       List<Object[]> results = query.list();
		   Map<String,Object> map = new HashMap<String,Object>();
	       map.put("destination", ps.getDestination_id().getDestination_name());
	       map.put("destination_id", ps.getDestination_id().getDestination_id());
	       map.put("date", ps.getDate_of_travel());
	   
	       map.put("status",ad.dataSchedule(ps.getDate_of_travel().toString(),ps.getDestination_id().getDestination_id()));
	    	 
	       for(Object[] sh: results){
	    	   map.put(sh[1].toString(), sh[0].toString());
	    	   
	    	   
	       }
	       List<Map<String,Object>> list_pass = new ArrayList<Map<String,Object>>();
	       for(Passenger ob_pass:pass){
	    	   Map<String,Object> map_pass = new HashMap<String,Object>();
	    	   map_pass.put("name", ob_pass.getUser_id().getFullname());
	    	   map_pass.put("batch", ob_pass.getUser_id().getBatch_id().getBatch_number());
	    	   map_pass.put("role", ob_pass.getUser_id().getRole_id().getRole_name());
	    	   map_pass.put("email", ob_pass.getUser_id().getEmail());
	    	   map_pass.put("phone", ob_pass.getUser_id().getPhone_number());
	    	   map_pass.put("user_id", ob_pass.getUser_id().getUser_id());
	    	   
	    	   list_pass.add(map_pass);
	    	   
	       }
	       List<Map<String,Object>> list_cus = new ArrayList<Map<String,Object>>();
	       for(Passenger ob_pass:cus){
	    	   Map<String,Object> map_pass = new HashMap<String,Object>();
	    	   map_pass.put("name", ob_pass.getUser_id().getFullname());
	    	   map_pass.put("batch", ob_pass.getUser_id().getBatch_id().getBatch_number());
	    	   map_pass.put("role", ob_pass.getUser_id().getRole_id().getRole_name());
	    	   map_pass.put("email", ob_pass.getUser_id().getEmail());
	    	   map_pass.put("phone", ob_pass.getUser_id().getPhone_number());
	    	   map_pass.put("user_id", ob_pass.getUser_id().getUser_id());
	    	   
	    	   list_cus.add(map_pass);
	    	   
	       }
	       map.put("list", list_pass);
	       map.put("list_cus", list_cus);
	       data.add(map);
	       
	       }
	       
	      
	        
	       } catch (RuntimeException e) {
	    	  
	           e.printStackTrace();
	          
	       } finally {
	           session.flush();
	           session.close();
	       }
	       return data;
	}
	
	public List<Map<String,Object>> showEmergency(){
		   Transaction trns = null;
		   Session session = HibernateUtil.getSessionFactory().openSession();
		   Admin_Imp ad = new Admin_Imp();
		   
	       List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
	       try{
			   String hql = "From Emergency_Request where status='panding'";
		 	   Query qpass = session.createQuery(hql);
		 	   List<Emergency_Request> emr= qpass.list();
		 	  
			  for(int i=0;i<emr.size();i++){
				  Map<String,Object> emr_map = new HashMap<String,Object>();
				  emr_map.put("emr_id", emr.get(i).getId());
				  emr_map.put("name", emr.get(i).getUser_id().getFullname());
				  emr_map.put("role", emr.get(i).getUser_id().getRole_id().getRole_name());
				  emr_map.put("user_id", emr.get(i).getUser_id().getUser_id());
				  emr_map.put("date_of_travel", emr.get(i).getSchedule_id().getDate_of_travel());			  
				  emr_map.put("schedule_id", emr.get(i).getSchedule_id().getSchedule_id());
				  emr_map.put("destination", emr.get(i).getSchedule_id().getDestination_id().getDestination_name());
				  emr_map.put("reason", emr.get(i).getReason());
				  
				  data.add(emr_map);
		  }
		  
			} catch (RuntimeException e) {
		 	   if (trns != null) {
		 		   trns.rollback();
		         }
		        e.printStackTrace();
		       
		    } finally {
		        session.flush();
		        session.close();
		    }	  
	 		  
	 	   
		 
		 return data;
		}
	

	public List<Destination_Master> getDes() {
		   List<Destination_Master> list = new  ArrayList<Destination_Master> ();
	       Transaction trns = null;
	       Configuration con=new Configuration();
	       con.configure("hibernate.cfg.xml");
	       SessionFactory sf=con.buildSessionFactory();
	       Session session=sf.openSession();
	       String sta="true";
	       try {
	           trns = session.beginTransaction();
	           String hql ="FROM Destination_Master WHERE status=:st";
	           Query query =  session.createQuery(hql);
	           query.setString("st", sta);
	           list = query.list();
	           
	       } catch (RuntimeException e) {
	           e.printStackTrace();
	          
	       } finally {
	           session.flush();
	           session.close();
	       }
	       
	       return list;
	   }
	
	public Boolean addDestin(String des_name) {
		Transaction trns = null;
        Destination_Master add = new Destination_Master();
        Admin_Imp ad = new Admin_Imp();
        String status="true";
		String key = ad.Key(15);
        add.setDestination_id(key);
        add.setDestination_name(des_name);
        add.setStatus(status);
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(add);
            session.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
           
            session.close();
        }
	   }
	
	public String desUpdate(Destination des) {
	       Transaction trns = null;
	       Configuration con=new Configuration();
	       con.configure("hibernate.cfg.xml");
	       SessionFactory sf=con.buildSessionFactory();
	       Session session=sf.openSession();
	       System.out.println(des.getDes_update());
	       try {
	           trns = session.beginTransaction();
	           String hql = "UPDATE Destination_Master set destination_name = :name "  + 
	                   "WHERE destination_id = :id";
		      Query query = session.createQuery(hql);
		      query.setString("id", des.getDes_id());
		      query.setString("name", des.getDes_update());
		      int result = query.executeUpdate();
		      session.getTransaction().commit();
	 
	       } catch (RuntimeException e) {
	           e.printStackTrace();
	          
	       } finally {
	           session.flush();
	           session.close();
	       }
	       
	       return null;
	   }
	
	public Boolean desDelete(String des) {
			Boolean ret=false;
	       Transaction trns = null;
	       Configuration con=new Configuration();
	       con.configure("hibernate.cfg.xml");
	       SessionFactory sf=con.buildSessionFactory();
	       Session session=sf.openSession();
	       try {
	    	     String fal="false";
	     		 trns = session.beginTransaction();
		         String hql = "UPDATE Destination_Master set status = :st "  + 
		                   "WHERE destination_id = :id";
			      Query query = session.createQuery(hql);
			      query.setString("id", des);
			      query.setString("st", fal);
			      int result = query.executeUpdate();
			      session.getTransaction().commit();
			      ret=true;
	 
	       } catch (RuntimeException e) {
	           e.printStackTrace();
	           ret=false;
	          
	       } finally {
	           session.flush();
	           session.close();
	       }
		return ret;  
	   }
	
	public List<Batch_Master> get_batch() {
		   List<Batch_Master> list = new  ArrayList<Batch_Master> ();
	       Transaction trns = null;
	       Configuration con=new Configuration();
	       con.configure("hibernate.cfg.xml");
	       SessionFactory sf=con.buildSessionFactory();
	       Session session=sf.openSession();
	       String sta="true";
	       try {
	           trns = session.beginTransaction();
	           String hql ="FROM Batch_Master WHERE status=:st order by batch_number asc";
	           Query query =  session.createQuery(hql);
	           query.setString("st", sta);
	           list = query.list();
	           
	       } catch (RuntimeException e) {
	           e.printStackTrace();
	          
	       } finally {
	           session.flush();
	           session.close();
	       }
	       
	       return list;
	   }
	
	public Boolean batUpdate(BatchUpdate bat) {
		   Boolean ret=true;
	       Transaction trns = null;
	       Session session = HibernateUtil.getSessionFactory().openSession();
	       System.out.println();
	       try {
	    	   trns = session.beginTransaction();
	    	   String hql = "UPDATE Batch_Master set date_of_leaving = :leave, "  + 
		        		  "date_of_returning= :return, "+
		        		   "deadline_of_booking= :deadline "+
		        		   "WHERE batch_id = :id";
	      Query query = session.createQuery(hql);
	      query.setString("id", bat.getBatch_id());
	      query.setDate("leave", java.sql.Date.valueOf(bat.getDate_of_leaving()));
	      query.setDate("return", java.sql.Date.valueOf(bat.getDate_of_returning()));
	      query.setTimestamp("deadline", java.sql.Timestamp.valueOf(bat.getDeadline_of_booking()));
	      int result = query.executeUpdate();
	      session.getTransaction().commit();
	      System.out.println(result);
	 
	       } catch (RuntimeException e) {
	           e.printStackTrace();
	           ret= false;
	          
	       } finally {
	           session.flush();
	           session.close();
	       }
	       
	       return ret;
	   }
	
	public Boolean addBatch(BatchUpdate bat) {
		Transaction trns = null;
        Batch_Master add = new Batch_Master();
        Admin_Imp ad = new Admin_Imp();
        String status="true";
		String key = ad.Key(15);
        add.setBatch_id(key);
        add.setBatch_number(bat.getBatch_number());
        add.setDate_of_leaving(java.sql.Date.valueOf(bat.getDate_of_leaving()));
        add.setDate_of_returning(java.sql.Date.valueOf(bat.getDate_of_returning()));
        add.setDeadline_of_booking(java.sql.Timestamp.valueOf(bat.getDeadline_of_booking()));
        add.setStatus(status);
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(add);
            session.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
           
            session.close();
        }
	   }
	
	public Boolean deleteBatch(String des) {
	   Boolean ret=true;
       Transaction trns = null;
       Configuration con=new Configuration();
       con.configure("hibernate.cfg.xml");
       SessionFactory sf=con.buildSessionFactory();
       Session session=sf.openSession();
       try {
    	     String fal="false";
     		 trns = session.beginTransaction();
	           String hql = "UPDATE Batch_Master set status = :st "  + 
	                   "WHERE batch_id = :id";
		      Query query = session.createQuery(hql);
		      query.setString("id", des);
		      query.setString("st", fal);
		      int result = query.executeUpdate();
		      session.getTransaction().commit();
		      System.out.println(result);
 
       } catch (RuntimeException e) {
           e.printStackTrace();
           ret=false;
          
       } finally {
           session.flush();
           session.close();
       }
	return ret;  
   }
	
	public void autoUpdateBatchData() {
	       Transaction trns = null;
	       Admin_Imp adm=new Admin_Imp();
	       Session session = HibernateUtil.getSessionFactory().openSession();
	       try {
	    	   Query q = session.createQuery("from Batch_Master");
	    	   System.out.println(q.list().size());
	    	   for(int i=0;i<q.list().size();i++){
	    		   trns = session.beginTransaction();
	    		   Batch_Master bat = (Batch_Master)q.list().get(i);
		    	   //String lea_date=adm.plusSevenDate(bat.getDate_of_leaving());
		    	   System.out.println(bat.getDate_of_returning());
		    	   System.out.println(java.sql.Date.valueOf(adm.nowDate()));
		    	   System.out.println(bat.getDate_of_leaving());
		    	   System.out.println(bat.getDate_of_returning());
		    	   System.out.println(bat.getDeadline_of_booking());
		    	   
		    	   if(java.sql.Date.valueOf(adm.nowDate()).after(bat.getDate_of_returning())){
		    		     Calendar c1 = Calendar.getInstance(); 
			      	     c1.setTime(bat.getDate_of_leaving()); 
			      	     c1.add(Calendar.DATE, 7);
			      	     String lea_date=c1.get(Calendar.YEAR) + "-" + (c1.get(Calendar.MONTH) + 1) + "-" + c1.get(Calendar.DATE);
				      	 
				      	 
				      	 Calendar c2 = Calendar.getInstance();
			    	     c2.setTime(bat.getDate_of_returning()); 
			    	     c2.add(Calendar.DATE, 7);
			    	     String ret_date=c2.get(Calendar.YEAR) + "-" + (c2.get(Calendar.MONTH) + 1) + "-" + c2.get(Calendar.DATE);
			    	     
			    	     
			    	     Calendar c3 = Calendar.getInstance();
			    	     c3.setTime(bat.getDeadline_of_booking()); 
			    	     c3.add(Calendar.DATE, 7);
			    	     String deadline=c3.get(Calendar.YEAR) + "-" + (c3.get(Calendar.MONTH) + 1) + "-" + c3.get(Calendar.DATE);
			    	     
			    	     System.out.println(bat.getDate_of_leaving());
			    	     System.out.println(bat.getDate_of_returning());
			    	     System.out.println(bat.getDeadline_of_booking());
			    	     
			    	     bat.setDate_of_leaving(java.sql.Date.valueOf(lea_date));
			    	     bat.setDeadline_of_booking(java.sql.Date.valueOf(deadline));
			    	     bat.setDate_of_returning(java.sql.Date.valueOf(ret_date));
		    	   }
		    	   
		    	   session.update(bat);
		    	   session.getTransaction().commit();
	    	   }
	    	  
	       } catch (RuntimeException e) {
	           e.printStackTrace();
	          
	       } finally {
	           session.flush();
	           session.close();
	       }
	   }



	public static void main(String arg[]){
		   Session session = HibernateUtil.getSessionFactory().openSession();
	       //Transaction trns = session.beginTransaction();
	       Admin_Imp add = new Admin_Imp();
	       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	       java.util.Date date = new java.util.Date();
	       System.out.println(dateFormat.format(date));
	       try {
	          // trns = session.beginTransaction();
	           Criteria criteria = session.createCriteria(Passenger.class);
	           criteria.add( Restrictions.ge("date_of_travel", add.currentDate()) );
		       criteria.setProjection(Projections.projectionList()
		               .add(Projections.groupProperty("date_of_travel").as("date_of_travel"))
		               .add(Projections.groupProperty("destination_id").as("destination_id"))
		               .add(Projections.property("date_of_travel").as("date_of_travel"))
		               .add(Projections.property("destination_id").as("destination_id")));
		               
		       criteria.setResultTransformer(Transformers.aliasToBean(Passenger.class));
		       List<Passenger> list = criteria.list();
	           for(int i=0;i<list.size();i++){
	        	   System.out.println(list.get(i).getDate_of_travel());
	        	   
	           }
        	   
        	 
        	  
	       } catch (RuntimeException e) {
	    	   //if (trns != null) {
	    		  // trns.rollback();
	            //}
	           e.printStackTrace();
	          
	       } finally {
	           session.flush();
	           session.close();
	       }
	       
		  
		}

	public String Date(String date_convert){
		SimpleDateFormat format1 = new SimpleDateFormat("MMM d, y");
	       SimpleDateFormat format2 = new SimpleDateFormat("y-MM-dd");
	       java.util.Date date;
	       String format = "";
			try {
				date =  format1.parse(date_convert);
				format = format2.format(date);
			} catch (ParseException e1) {
				
				e1.printStackTrace();
			}
			
		return format;
	}
	public java.util.Date currentDate(){
		
	       java.util.Date date = new java.util.Date();
	       
			
		return date;
	}
	public boolean dataSchedule(String date_of_travel,String Destination_id){
	   Transaction trns = null;
	   Session session = HibernateUtil.getSessionFactory().openSession();
		
	   boolean status =false;
	   try{
		   String hql = "From Schedule_Table S where S.date_of_travel='"+date_of_travel+"' and S.destination_id.destination_id='"+Destination_id+"'";
	 	   Query qpass = session.createQuery(hql);
	 	   List<Schedule_Table> ll= qpass.list();
	 	   
	 	   if(ll.size()>0){
	 		  status = true;
	 	   }
	   	} catch (RuntimeException e) {
	 	   if (trns != null) {
	 		   trns.rollback();
	         }
	        e.printStackTrace();
       
	    } finally {
	        session.flush();
	        session.close();
	    }
	  
	 return status;
	}
	public List<Map<String,Object>> detailSchedule(String date_of_travel,String Destination_id){
		   Transaction trns = null;
		   Session session = HibernateUtil.getSessionFactory().openSession();
		   Admin_Imp ad = new Admin_Imp();
		   
	       List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
	       
	       try{
			   String hql = "From Schedule_Table S where S.date_of_travel='"+ad.Date(date_of_travel)
					   +"' and S.destination_id.destination_id='"
					   +Destination_id+"'";
			   System.out.println(hql);
		 	   Query qpass = session.createQuery(hql);
		 	   List<Schedule_Table> ll= qpass.list();
		 	   
		 	   if(ll.size()>0){
		 		   for(Bus_Per_Schedule bps:ll.get(0).getBus_per_schedule()){
		 			  Map<String,Object> pass_map = new HashMap<String,Object>();
		 			  List<Map<String,Object>> detail_list = new ArrayList<Map<String,Object>>();
		 			  pass_map.put("bus", bps.getBus_id().getBus_model());
		 			  pass_map.put("driver", bps.getUser_id().getFullname());
		 			  pass_map.put("total_seats", bps.getNumber_of_seats());
		 			  pass_map.put("departure", bps.getEst_departure_time());
		 			  pass_map.put("arrival", bps.getEst_arrival_time());
		 			  pass_map.put("departure", bps.getEst_departure_time());
		 			  pass_map.put("schedule_id", bps.getSchedule_id().getSchedule_id());
		 			  pass_map.put("bps_id", bps.getBus_per_schedule_id());
		 			  pass_map.put("remaining", bps.getRemaining_seats());
		 			  pass_map.put("customer_only", bps.getCustomer_only());
		 			  for(Passenger ps: bps.getPassenger()){
		 				 Map<String,Object> detail_map = new HashMap<String,Object>();
		 				 detail_map.put("name", ps.getUser_id().getFullname());
		 				 detail_map.put("batch", ps.getUser_id().getBatch_id().getBatch_number());
		 				 detail_map.put("role", ps.getUser_id().getRole_id().getRole_name());
		 				 detail_map.put("email", ps.getUser_id().getEmail());
		 				 detail_map.put("phone", ps.getUser_id().getPhone_number());
		 				 detail_map.put("user_id", ps.getUser_id().getUser_id());
		 				 detail_list.add(detail_map);
		 			  }
		 			  pass_map.put("detail", detail_list);
		 			  data.add(pass_map);
		 			  
		 		   }
		 		   
			 	
			 	   
		 	   }
	 	   
			} catch (RuntimeException e) {
		 	   if (trns != null) {
		 		   trns.rollback();
		         }
		        e.printStackTrace();
		       
		    } finally {
		        session.flush();
		        session.close();
		    }
	 	   
		 
		 return data;
		}

	private String Key(int mount){
		 SecureRandom random = new SecureRandom();
		    String key;
		  
		    key=  new BigInteger(mount*5, random).toString(32);
		   
		return key;
	}
	
}
