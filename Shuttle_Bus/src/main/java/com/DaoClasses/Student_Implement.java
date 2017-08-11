package com.DaoClasses;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import com.EntityClasses.Batch_Master;
import com.EntityClasses.Emergency_Request;
import com.EntityClasses.Exchange_Seat;
import com.EntityClasses.Passenger;
import com.EntityClasses.Permission;
import com.EntityClasses.Schedule_Table;
import com.EntityClasses.Ticket_Donation;
import com.EntityClasses.User_Master;
import com.HibernateUtil.HibernateUtil;
import com.ModelClasses.Donate;
import com.ModelClasses.EmergencyBooking;
import com.ModelClasses.ExchangeSeat;
import com.ModelClasses.NotificationModel;
import com.ModelClasses.PermissionRequest;


public class Student_Implement implements StudentDao{
	
	public static String nowDate() {
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
	
	public List<User_Master> getUserInfo(String user_id){
		List<User_Master> user = new ArrayList<User_Master>();
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
     	try {
            trns = session.beginTransaction();
            String hql ="FROM User_Master where user_id=:id";
            Query query =  session.createQuery(hql);
            query.setString("id", user_id);
            user = query.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return user;
        } finally {
            session.flush();
            session.close();
        }
        return user;
	}
	
	public List<Batch_Master> Batch() {
		   List<Batch_Master> list = new  ArrayList<Batch_Master> ();
	       Transaction trns = null;
	       Session session = HibernateUtil.getSessionFactory().openSession();
	       
	       try {
	           trns = session.beginTransaction();
	           String hql ="FROM Batch_Master order by batch_number asc";
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
	
	public List<User_Master> Bat_User(Donate donate) {
		 List<User_Master> list = new  ArrayList<User_Master> ();
       Transaction trns = null;
       Configuration con=new Configuration();
       con.configure("hibernate.cfg.xml");
    	SessionFactory sf=con.buildSessionFactory();
    	Session session=sf.openSession();
       
       try {
           trns = session.beginTransaction();
           String hql ="FROM User_Master WHERE batch_id= :bat_id AND user_id!=:us_id";
           Query query =  session.createQuery(hql);
           query.setString("bat_id", donate.getBatch_id());
           query.setString("us_id", donate.getUser_id());
           list = query.list();
           
       } catch (RuntimeException e) {
           e.printStackTrace();
          
       } finally {
           session.flush();
           
       }
       return list;
   }
	
	public Boolean donate(Donate don) {
		Transaction trns = null;
		Ticket_Donation add = new Ticket_Donation();
        User_Master donate_to=new User_Master(don.getDonate_to());
        User_Master receive_from=new User_Master(don.getReceive_from());
        add.setDonate_to(donate_to);
        add.setReceive_from(receive_from);
        add.setNo_of_ticket(don.getNo_ticket());
        add.setCreated_at(new Date());
        add.setUpdated_at(new Date());
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            
            Query q = session.createQuery("from User_Master where user_id = :id ");
            q.setParameter("id",don.getReceive_from());
            User_Master re_from = (User_Master)q.list().get(0);
            if(re_from.getNo_of_ticket()==0||re_from.getNo_of_ticket()<don.getNo_ticket()){
            	return null;
            }
            System.out.println("KK"+re_from.getNo_of_ticket());
            System.out.println("KK"+don.getNo_ticket());
            re_from.setNo_of_donate_ticket(re_from.getNo_of_donate_ticket()+don.getNo_ticket());
            re_from.setNo_of_ticket(re_from.getNo_of_ticket()-don.getNo_ticket());
            session.update(re_from);
            
            Query q1 = session.createQuery("from User_Master where user_id = :id");
            q1.setParameter("id", don.getDonate_to());
            User_Master do_to = (User_Master)q1.list().get(0);
            do_to.setNo_of_ticket(do_to.getNo_of_ticket()+ don.getNo_ticket());
            session.update(do_to);
            
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
	
	public Boolean exchange(ExchangeSeat exch) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Exchange_Seat exs=new Exchange_Seat();
		User_Master ex_fr=new User_Master(exch.getReceive_from());
		User_Master ex_to=new User_Master(exch.getExchange_to());
        try {
        	  trns = session.beginTransaction();
        	  
        	  Query q = session.createQuery("from Passenger where user_id = :id and bus_per_schedule_id!=null");
              q.setString("id", exch.getReceive_from());
              Passenger ex_from = (Passenger)q.list().get(0);
        	  
	          String hql = "UPDATE Passenger set user_id = :ex_id "  + 
	                   "WHERE user_id = :id and bus_per_schedule_id!=null";
		      Query query = session.createQuery(hql);
		      query.setString("ex_id", exch.getExchange_to());
		      query.setString("id", exch.getReceive_from());
		      int result = query.executeUpdate();
		      System.out.println("Ex"+exch.getExchange_to());
		      System.out.println("Ex"+exch.getReceive_from());
		      System.out.println("Ex"+ex_from.getBus_per_schedule_id());
		      Schedule_Table sch=new Schedule_Table(ex_from.getBus_per_schedule_id().getSchedule_id().getSchedule_id());
		      exs.setExchange_from(ex_fr);
		      exs.setExchange_to(ex_to);
		      exs.setSchedule_id(sch);
		      exs.setCreated_at(new Date());
		      exs.setUpdated_at(new Date());
		      session.save(exs);
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
	
	public Boolean check_exchange(String user_id) {
		Transaction trns = null;
		List<Passenger> pas=new ArrayList<Passenger>(); 
		Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	trns = session.beginTransaction();
            String hql ="FROM Passenger WHERE user_id=:us_id and bus_per_schedule_id!=null";
            Query query =  session.createQuery(hql);
            query.setString("us_id", user_id);
            pas = query.list();
            if(pas.size()==0){
            	return null;
            }else{
            	return true;
            }
            
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
	
	public List<Schedule_Table> checkSchdule(String user_id){
		List<Passenger> pas=new ArrayList<Passenger>(); 
		List<Schedule_Table> sche = new ArrayList<Schedule_Table>();
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
     	try {
            trns = session.beginTransaction();
            String hql ="FROM Passenger WHERE user_id!=:us_id and bus_per_schedule_id!=null";
            Query query =  session.createQuery(hql);
            query.setString("us_id", user_id);
            pas = query.list();         
            
            Criteria c = session.createCriteria(Schedule_Table.class, "sch");
            c.add(Restrictions.ge("sch.date_of_travel", new Date()));
            for(int i=0;i<pas.size();i++){
           	 c.add(Restrictions.ne("sch.schedule_id", pas.get(0).getBus_per_schedule_id().getSchedule_id().getSchedule_id()));
            }
            c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
            sche=c.list();
        }catch (RuntimeException e){
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
     	}
        return sche;
	}
	
	public Boolean emergencyBooking(EmergencyBooking book) {
		Transaction trns = null;
		String status="emergency_waiting";
		Emergency_Request add = new Emergency_Request();
        User_Master user_id=new User_Master(book.getUser_id());
        Schedule_Table sch_id= new Schedule_Table(book.getSchedule_id());
        add.setUser_id(user_id);
        add.setSchedule_id(sch_id);
        add.setReason(book.getReason());
        add.setStatus(status);
        //add.setCreated_at(new Date());
        //add.setUpdated_at(new Date());
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
	
	public Boolean permission_request(PermissionRequest pr) {
		Transaction trns = null;
		String status="permission_waiting";
		Permission add = new Permission();
        User_Master user_id=new User_Master(pr.getUser_id());
        add.setUser_id(user_id);
        add.setDate_of_request(java.sql.Date.valueOf(pr.getDate_of_request()));
        add.setCreated_at(new Date());
        add.setUpdated_at(new Date());
        add.setReason(pr.getReason());
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
	
	public List<Map<String, Object>> booking_notification(NotificationModel noti) {
		Transaction trns = null;
		StudentDao stu = new Student_Implement();
		List<Passenger> pass = new ArrayList<Passenger>();
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		List<Schedule_Table> sch=new ArrayList<Schedule_Table>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String hql ="FROM Passenger WHERE user_id=:us_id and date_of_travel >=:today";
            Query query =  session.createQuery(hql);
            query.setString("us_id", noti.getUser_id());
            query.setDate("today", new Date());
            if(noti.getValue()==0){
            	query.setMaxResults(8);
            }else{
            	query.setMaxResults(noti.getValue()*10);
            }
            pass = query.list();
            System.out.println(pass.size());
    		if(pass.size()!=0){
    			for(int i=0;i<pass.size();i++){
    				Map<String,Object> passenger = new HashMap<String,Object>();
    				passenger.put("destination_name",pass.get(i).getDestination_id().getDestination_name() );
    				passenger.put("date_of_travel", pass.get(i).getDate_of_travel());
    				passenger.put("updated_at", pass.get(i).getUpdated_at());
    				if(pass.get(i).getBus_per_schedule_id()!=null){
    					//Booking Approved
    					passenger.put("status", "booking_approved");
    				}else{
    					sch=stu.checkSchedule(pass.get(i).getDestination_id().getDestination_id(),pass.get(i).getDate_of_travel());
    					if(sch.size()==0){
    						//waiting for booking approve
    						passenger.put("status", "booking_waiting");
    					}else{
    						//Booking Conflict
    						passenger.put("status", "booking_conflict");
    					}
    				}
    				list.add(passenger);
    			}
    		}
    		System.out.println(list);
        }catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
		return list;
	   }
	
	public List<Map<String, Object>> donate_notification(NotificationModel noti) {
		Transaction trns = null;
		StudentDao stu = new Student_Implement();
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		List<Ticket_Donation> tic_donate = new ArrayList<Ticket_Donation>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String hql ="FROM Ticket_Donation WHERE donate_to=:user_id";
            Query query =  session.createQuery(hql);
            query.setString("user_id", noti.getUser_id());
            if(noti.getValue()==0){
            	query.setMaxResults(8);
            }else{
            	query.setMaxResults(noti.getValue()*10);
            }
            tic_donate = query.list(); 
            for(int i=0;i<tic_donate.size();i++){
            	Map<String,Object> donate = new HashMap<String,Object>();
                donate.put("receive_from", tic_donate.get(i).getReceive_from().getFullname());
                donate.put("no_of_ticket", tic_donate.get(i).getNo_of_ticket());
                donate.put("updated_at", tic_donate.get(i).getUpdated_at());
                donate.put("status", "donated");
                list.add(donate);            
               }

        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	   }
	
	public List<Map<String, Object>> exchange_seat_notification(NotificationModel noti) {
		Transaction trns = null;
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		List<Exchange_Seat> exch_seat= new ArrayList<Exchange_Seat>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String hql ="FROM Exchange_Seat WHERE exchange_to=:user_id";
            Query query =  session.createQuery(hql);
            query.setString("user_id", noti.getUser_id());
            if(noti.getValue()==0){
            	query.setMaxResults(8);
            }else{
            	query.setMaxResults(noti.getValue()*10);
            }
            exch_seat = query.list(); 
            
            for(int i=0;i<exch_seat.size();i++){
            	Map<String,Object> exch = new HashMap<String,Object>();
            	exch.put("exchange_from", exch_seat.get(i).getExchange_from().getFullname());
            	exch.put("date_of_travel", exch_seat.get(i).getSchedule_id().getDate_of_travel());
            	exch.put("destination", exch_seat.get(i).getSchedule_id().getDestination_id().getDestination_name());
            	exch.put("updated_at", exch_seat.get(i).getUpdated_at());
            	exch.put("status", "exchange_seat");
            	list.add(exch);
            }
        }catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	   }
	
	public List<Map<String, Object>> permission_notification(NotificationModel noti) {
		Transaction trns = null;  
		List<Permission> permission = new ArrayList<Permission>();
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String hql ="FROM Permission WHERE user_id=:user_id";
            Query query =  session.createQuery(hql);
            query.setString("user_id", noti.getUser_id());
            if(noti.getValue()==0){
            	query.setMaxResults(8);
            }else{
            	query.setMaxResults(noti.getValue()*10);
            }
            permission = query.list(); 
            for(int i=0;i<permission.size();i++){
            	Map<String,Object> exch = new HashMap<String,Object>();
            	exch.put("date_of_request", permission.get(i).getDate_of_request());
            	exch.put("reason", permission.get(i).getReason());
            	exch.put("updated_at", permission.get(i).getUpdated_at());
            	exch.put("status", permission.get(i).getStatus());
            	list.add(exch);
    		}
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	   }
	
	public List<Schedule_Table> checkSchedule(String des_id, Date date_travel) {
		Transaction trns = null;
		List<Schedule_Table> sch=new ArrayList<Schedule_Table>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String hql ="FROM Schedule_Table WHERE date_of_travel=:date and destination_id=:dest";
            Query query =  session.createQuery(hql);
            query.setString("dest", des_id);
            query.setDate("date", date_travel);
            sch =  query.list(); 
            
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
        return sch;
	   }
	
	public static void main(String args[]){
		Transaction trns = null;
		String user_id="t4jrtfh385";
		StudentDao stu = new Student_Implement();
		List<Passenger> pass = new ArrayList<Passenger>();
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		List<Schedule_Table> sch=new ArrayList<Schedule_Table>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String hql ="FROM Passenger WHERE user_id=:us_id and date_of_travel >=:today";
            Query query =  session.createQuery(hql);
            query.setString("us_id", user_id);
            query.setDate("today", new Date());
            pass = query.list();
            System.out.println(pass.size());
    		if(pass.size()!=0){
    			for(int i=0;i<pass.size();i++){
    				Map<String,Object> passenger = new HashMap<String,Object>();
    				passenger.put("destination_name",pass.get(i).getDestination_id().getDestination_name() );
    				passenger.put("date_of_travel", pass.get(i).getDate_of_travel());
    				passenger.put("updated_at", pass.get(i).getUpdated_at());
    				if(pass.get(i).getBus_per_schedule_id()!=null){
    					//Booking Approved
    					passenger.put("status", "booking_approved");
    				}else{
    					sch=stu.checkSchedule(pass.get(i).getDestination_id().getDestination_id(),pass.get(i).getDate_of_travel());
    					if(sch.size()==0){
    						//waiting for booking approve
    						passenger.put("status", "booking_waiting");
    					}else{
    						//Booking Conflict
    						passenger.put("status", "booking_conflict");
    					}
    				}
    				list.add(passenger);
    			}
    		}
    		System.out.println(list);
        }catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();

        } finally {
           
            session.close();
        }
	}
	
	
}
