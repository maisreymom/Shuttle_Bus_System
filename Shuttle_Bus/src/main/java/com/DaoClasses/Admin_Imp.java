package com.DaoClasses;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.Time;

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









import org.hibernate.transform.Transformers;
import org.springframework.scheduling.annotation.Scheduled;

import com.EntityClasses.Batch_Master;
import com.EntityClasses.Bus_Master;
import com.EntityClasses.Bus_Per_Schedule;
import com.EntityClasses.Bus_Report_Table;
import com.EntityClasses.Destination_Master;
import com.EntityClasses.Passenger;
import com.EntityClasses.Role_Master;
import com.EntityClasses.Schedule_Table;
import com.EntityClasses.User_Master;
import com.HibernateUtil.HibernateUtil;
import com.ModelClasses.Add_Bus;
import com.ModelClasses.BatchUpdate;
import com.ModelClasses.Destination;
import com.ModelClasses.Model_User;
import com.ModelClasses.Set_Schedule;
import com.ModelClasses.Shuttle;
import com.mysql.fabric.xmlrpc.base.Data;

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

	public List<User_Master> User() {
		 List<User_Master> list = new  ArrayList<User_Master> ();
        Transaction trns = null;
        Configuration con=new Configuration();
        con.configure("hibernate.cfg.xml");
     	SessionFactory sf=con.buildSessionFactory();
     	Session session=sf.openSession();
        
        try {
            trns = session.beginTransaction();
            String hql ="FROM User_Master";
            Query query =  session.createQuery(hql);
        
			List results = session.createCriteria(Passenger.class)
            	    .setProjection( Projections.projectionList()
            	       
            	        .add( Projections.groupProperty("date_of_travel"))
            	    )
            	    .list();
			
            
            list = query.list();
            
			
			
            
           
            	
            
        } catch (RuntimeException e) {
            e.printStackTrace();
           
        } finally {
            session.flush();
            
        }
        
        return list;
    }
	
	public List<Bus_Master> Schedule_Bus() {
	   List<Bus_Master> list = new  ArrayList<Bus_Master> ();
       Transaction trns = null;
       Configuration con=new Configuration();
       con.configure("hibernate.cfg.xml");
    	SessionFactory sf=con.buildSessionFactory();
    	Session session=sf.openSession();
       
       try {
           trns = session.beginTransaction();
           String hql ="FROM Bus_Master";
           Query query =  session.createQuery(hql);
       
			
           
           list = query.list();
 
 
       } catch (RuntimeException e) {
           e.printStackTrace();
          
       } finally {
           session.flush();
           
       }
       
       return list;
   }
	
	
	public List<Schedule_Table> Schedule_Report() {
		   List<Schedule_Table> list = new  ArrayList<Schedule_Table> ();
	       Transaction trns = null;
	       Configuration con=new Configuration();
	       con.configure("hibernate.cfg.xml");
	    	SessionFactory sf=con.buildSessionFactory();
	    	Session session=sf.openSession();
	       
	       try {
	           trns = session.beginTransaction();
	           String hql ="FROM Schedule_Table";
	           Query query =  session.createQuery(hql);
	       
				
	           
	           list = query.list();
	 
	 
	       } catch (RuntimeException e) {
	           e.printStackTrace();
	          
	       } finally {
	           session.flush();
	           
	       }
	       
	       return list;
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
	       Configuration con=new Configuration();
	       con.configure("hibernate.cfg.xml");
	       SessionFactory sf=con.buildSessionFactory();
	       Session session=sf.openSession();
	       
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
	       Configuration con=new Configuration();
	       con.configure("hibernate.cfg.xml");
	       SessionFactory sf=con.buildSessionFactory();
	       Session session=sf.openSession();
	       
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
        user.setUsername(add.getUsername());
        user.setEmail(add.getEmail());
        user.setFullname(add.getUsername());
        user.setUser_id(key);
        user.setGender(add.getGender());
        user.setNo_of_ticket(18);
        user.setPassword(add.getPassword());
        user.setPhone_number(add.getPhone_number());
        batch.setBatch_id(add.getBatch());
        role.setRole_id(add.getRole());
        user.setBatch_id(batch);
        user.setRole_id(role);
        
	    Configuration con=new Configuration();
        con.configure("hibernate.cfg.xml");
     	SessionFactory sf=con.buildSessionFactory();
     	Session session=sf.openSession();
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
	 		
	    Configuration con=new Configuration();
        con.configure("hibernate.cfg.xml");
     	SessionFactory sf=con.buildSessionFactory();
     	Session session=sf.openSession();
     	
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
	 		
	    Configuration con=new Configuration();
        con.configure("hibernate.cfg.xml");
     	SessionFactory sf=con.buildSessionFactory();
     	Session session=sf.openSession();
     	
        try {
        	
        	 trns = session.beginTransaction();
	         System.out.println(delete_id);
        	 Bus_Master p = (Bus_Master) session.get(Bus_Master.class,delete_id);
	           session.delete(p);
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
	 		
	    Configuration con=new Configuration();
        con.configure("hibernate.cfg.xml");
     	SessionFactory sf=con.buildSessionFactory();
     	Session session=sf.openSession();
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
	 	Configuration con=new Configuration();
        con.configure("hibernate.cfg.xml");
     	SessionFactory sf=con.buildSessionFactory();
     	Session session=sf.openSession();
     	
        Admin_Imp ad = new Admin_Imp();
        List<Set<Bus_Per_Schedule>> ls = new ArrayList<Set<Bus_Per_Schedule>>();
		String schedule_id = ad.Key(15);
		
		int Total_available_seats=0;
		int remaining_seats=0;
		
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
		String [] id = new String[set.length];
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
			
			
			id[i]=bps_id;
			map.put(bps_id, set[i].getPassenger_id());
			
			set_bps.add(bps);
		}
		for(Bus_Per_Schedule pb : set_bps){
			System.out.println(pb.getEst_arrival_time());
			
			
		}
		schedule.setBus_per_schedule(set_bps);
		
        try {
            trns = session.beginTransaction();
            
            session.save(schedule);
        	session.getTransaction().commit();
            
            return ad.setBus(map,ad.Date(set[0].getDate_of_travel()),set[0].getDestination_id())&&ad.setBusReport(id);
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
	public  boolean setBus(Map<String,List<String>> upd,String date,String dest) {
	 	Transaction trns = null;
	 	Configuration con=new Configuration();
        con.configure("hibernate.cfg.xml");
     	SessionFactory sf=con.buildSessionFactory();
     	Session session=sf.openSession();
     	
     	for(String bp : upd.keySet()){
     		String hql = "update Passenger p set p.bus_per_schedule_id='"+bp+"'"+" where p.user_id in (";
			System.out.println(upd.get(bp));
			
			for(int i=0;i<upd.get(bp).size();i++){
				if(!(i==upd.get(bp).size()-1)){
					hql = hql + "'"+upd.get(bp).get(i)+"',";
				}
				else {
					hql = hql + "'"+upd.get(bp).get(i)+"'";
				}
				
			}
			hql = hql + ") and p.date_of_travel='"+date+"' and p.destination_id.destination_id='"+dest+"'";
			Query query = session.createQuery(hql);
			//query.setParameter("sn","hjkldfhfhg");
        	 query.executeUpdate();
		}
        try {
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
	public  boolean setBusReport(String[] id) {
	 	Transaction trns = null;
	 	Configuration con=new Configuration();
        con.configure("hibernate.cfg.xml");
     	SessionFactory sf=con.buildSessionFactory();
     	Session session=sf.openSession();
     	
     	for(int i=0;i<id.length;i++){
     		System.out.println(id[i]);
     		Bus_Per_Schedule bps = new Bus_Per_Schedule();
         	Bus_Report_Table brt = new Bus_Report_Table();
         	bps.setBus_per_schedule_id(id[i]);
         	brt.setBus_per_schedule(bps);
         	session.saveOrUpdate(brt);
         	
     	}
     	
     	
     	
        try {
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
		
	       Configuration con=new Configuration();
	       con.configure("hibernate.cfg.xml");
	       SessionFactory sf=con.buildSessionFactory();
	       Session session=sf.openSession();
	       Admin_Imp ad = new Admin_Imp();
	       Criteria criteria = session.createCriteria(Passenger.class);
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
	       //map.put("schedule", );
	       map.put("status",ad.dataSchedule(ps.getDate_of_travel().toString(),ps.getDestination_id().getDestination_id()));
	    	   /*Schedule_Table sh =  new Schedule_Table();
	    	   sh = ad.dataSchedule(ps.getDate_of_travel().toString(),ps.getDestination_id().getDestination_id());
	    	   Map<String,Object> schedule_map = new HashMap<String,Object>();
	    	   schedule_map.put("schedule_id",sh.getSchedule_id());
	    	   schedule_map.put("total_seats",sh.getTotal_available_seats());
	    	   schedule_map.put("staff",sh.getStaff_seats());
	    	   schedule_map.put("customer",sh.getCustomer_seats());
	    	   schedule_map.put("student",sh.getStudent_seats());
	    	   schedule_map.put("remaining",sh.getRemaining_seats());*/
	    	
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
			Boolean ret=true;
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
		 Transaction trns = null;
	       Configuration con=new Configuration();
	       con.configure("hibernate.cfg.xml");
	       SessionFactory sf=con.buildSessionFactory();
	       Session session=sf.openSession();
	       Admin_Imp add = new Admin_Imp();
	       /*Criteria criteria = session.createCriteria(Passenger.class);
	       criteria.setProjection(Projections.projectionList()
	               .add(Projections.groupProperty("date_of_travel").as("date_of_travel"))
	               .add(Projections.groupProperty("destination_id").as("destination_id"))
	               .add(Projections.property("date_of_travel").as("date_of_travel"))
	               .add(Projections.property("destination_id").as("destination_id")));
	       criteria.setResultTransformer(Transformers.aliasToBean(Passenger.class));
	       List<Passenger> list = criteria.list();
	       List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
	       for (Passenger ps : list) {
	          
	    	   List<Passenger> pass = new ArrayList<Passenger>();
	    	   
	    	   String hql = "From Passenger P where P.destination_id.destination_name='"+ps.getDestination_id().getDestination_name()+"'"
	    			   +" and P.date_of_travel='"+ps.getDate_of_travel()+"'";
	    	   Query qpass = session.createQuery(hql);
	    	   pass = qpass.list();
	    	   
	           String group = "select count(E.user_id.role_id.role_name),  E.user_id.role_id.role_name "
	           		+"from Passenger E where E.destination_id.destination_name='" + ps.getDestination_id().getDestination_name()
	           		+"' and E.date_of_travel='"+ps.getDate_of_travel() +"' " 
	                +"GROUP BY E.user_id.role_id.role_name";
			       Query query = session.createQuery(group);
			       List<Object[]> results = query.list();
		   Map<String,Object> map = new HashMap<String,Object>();
	       map.put("destination", ps.getDestination_id().getDestination_name());
	       map.put("date", ps.getDate_of_travel());
	       for(Object[] sh: results){
	    	   map.put(sh[1].toString(), sh[0].toString());
	    	   
	    	   
	       }
	       List<Map<String,Object>> list_pass = new ArrayList<Map<String,Object>>();
	       for(Passenger ob_pass:pass){
	    	   Map<String,Object> map_pass = new HashMap<String,Object>();
	    	   map_pass.put("name", ob_pass.getUser_id().getFullname());
	    	   map_pass.put("batch", ob_pass.getUser_id().getBatch_id().getBatch_number());
	    	   map_pass.put("role", ob_pass.getUser_id().getRole_id().getRole_id());
	    	   map_pass.put("email", ob_pass.getUser_id().getEmail());
	    	   map_pass.put("phone", ob_pass.getUser_id().getPhone_number());
	    	   map_pass.put("user_id", ob_pass.getUser_id().getUser_id());
	    	   list_pass.add(map_pass);
	    	   
	       }
	       map.put("list", list_pass);
	       data.add(map);
	       
	       }*/
	       
    	   System.out.println(add.dataSchedule("2017-05-1","756fh4hfyo"));
	       
	    
	      
	       
	       try {
	           trns = session.beginTransaction();
	           
	       
        	
        	
	        session.getTransaction().commit();
	       
	       } catch (RuntimeException e) {
	    	   if (trns != null) {
	                
	            }
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		return format;
	}
	public boolean dataSchedule(String date_of_travel,String Destination_id){
	   Transaction trns = null;
       Configuration con=new Configuration();
       con.configure("hibernate.cfg.xml");
       SessionFactory sf=con.buildSessionFactory();
       Session session=sf.openSession();
		
	   boolean status =false;
	   String hql = "From Schedule_Table S where S.date_of_travel='"+date_of_travel+"' and S.destination_id.destination_id='"+Destination_id+"'";
 	   Query qpass = session.createQuery(hql);
 	   List<Schedule_Table> ll= qpass.list();
 	   
 	   if(ll.size()>0){
 		  status = true;
 	   }
	  
	 return status;
	}
	public List<Map<String,Object>> detailSchedule(String date_of_travel,String Destination_id){
		   Transaction trns = null;
	       Configuration con=new Configuration();
	       con.configure("hibernate.cfg.xml");
	       SessionFactory sf=con.buildSessionFactory();
	       Session session=sf.openSession();
		   Admin_Imp ad = new Admin_Imp();
		   
	       List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		   String hql = "From Schedule_Table S where S.date_of_travel='"+ad.Date(date_of_travel)+"' and S.destination_id.destination_id='"+Destination_id+"'";
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
	 			  pass_map.put("arrivel", bps.getEst_arrival_time());
	 			  pass_map.put("departure", bps.getEst_departure_time());
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
	 	   
		  
		 return data;
		}

	private String Key(int mount){
		 SecureRandom random = new SecureRandom();
		    String key;
		  
		    key=  new BigInteger(mount*5, random).toString(32);
		   
		return key;
	}
	
}
