package com.DaoClasses;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.Time;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.hibernate.criterion.Projections;









import com.EntityClasses.Batch_Master;
import com.EntityClasses.Bus_Master;
import com.EntityClasses.Bus_Per_Schedule;
import com.EntityClasses.Bus_Report_Table;
import com.EntityClasses.Destination_Master;
import com.EntityClasses.Passenger;
import com.EntityClasses.Role_Master;
import com.EntityClasses.Schedule_Table;
import com.EntityClasses.User_Master;
import com.ModelClasses.Add_Bus;
import com.ModelClasses.Model_User;
import com.ModelClasses.Set_Schedule;
import com.mysql.fabric.xmlrpc.base.Data;

public class Admin_Imp implements Admin_Inf{

	public List<List<Passenger>> Schedule() {
		List<List<Passenger>> date = new ArrayList<List<Passenger>>(); 
        Transaction trns = null;
        Configuration con=new Configuration();
        con.configure("hibernate.cfg.xml");
     	SessionFactory sf=con.buildSessionFactory();
     	Session session=sf.openSession();
        
        try {
            trns = session.beginTransaction();
            String hql ="FROM Passenger";
            Query query =  session.createQuery(hql);
        
			List results = session.createCriteria(Passenger.class)
					
            	    .setProjection( Projections.projectionList()
            	       
            	        .add( Projections.groupProperty("date_of_travel"))
            	    )
            	    .list();
			
            
            List<Passenger> list = query.list();
            
			
			
            for(int i=0;i<results.size();i++){
            	List<Passenger> dat = new ArrayList<Passenger>(); 
            	for(int j=0;j<list.size();j++){
            		if(list.get(j).getDate_of_travel().toString().equals(results.get(i).toString())){
            			
            			dat.add(list.get(j));
            			
            			
            		}
            			
            		}
            	
            	date.add(dat);
            	}
           
            	
            	/*for(int i=0;i<date.size();i++){
            		for(int j=0;j<date.get(i).size();j++){
            			System.out.println(date.get(i).get(j).getDate_of_travel());
            		}
            		 
            }*/
           
            
        } catch (RuntimeException e) {
            e.printStackTrace();
           
        } finally {
            session.flush();
            
        }
        
        return date;
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
		schedule.setDate_of_travel(Date.valueOf(set[0].getDate_of_travel()));
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
			bps.setEst_arrival_time(Time.valueOf(set[i].getEst_arrival()));
			bps.setEst_departure_time(Time.valueOf(set[i].getEst_departure()));
			
			
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
            
            return ad.setBus(map)&&ad.setBusReport(id);
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
	public  boolean setBus(Map<String,List<String>> upd) {
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
			hql = hql + ")";
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
	
	
	public static void main(String arg[]){
		 Transaction trns = null;
	       Configuration con=new Configuration();
	       con.configure("hibernate.cfg.xml");
	       SessionFactory sf=con.buildSessionFactory();
	       Session session=sf.openSession();
	       
	       try {
	           trns = session.beginTransaction();
	           
	       	String hql = "update Passenger p set p.ticket_qrcode=:sn where p.user_id in ( '21lruks8nme', '74jcfhd380', '7rvqg6dlbuvi')";
        	Query query = session.createQuery(hql);
        	query.setParameter("sn","hjkldfhfhg");
        	 int res = query.executeUpdate();
        	
	        session.getTransaction().commit();
	           
	       } catch (RuntimeException e) {
	           e.printStackTrace();
	          
	       } finally {
	           session.flush();
	           session.close();
	       }
	       
		  
		}
	private String Key(int mount){
		 SecureRandom random = new SecureRandom();
		    String key;
		  
		    key=  new BigInteger(mount*5, random).toString(32);
		   
		return key;
	}
	
}
