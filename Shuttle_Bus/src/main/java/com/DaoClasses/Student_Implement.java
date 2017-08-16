package com.DaoClasses;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

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
import com.ModelClasses.Cancel_Ticket;
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
	public String DateTime(String date_convert){
		SimpleDateFormat format1 = new SimpleDateFormat("MMM d, y HH:mm:ss");
	       SimpleDateFormat format2 = new SimpleDateFormat("y-MM-dd HH:mm:ss");
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
	public List<Map<String, Object>> getUserInfo(String user_id){
		List<User_Master> user = new ArrayList<User_Master>();
		Student_Implement stu=new Student_Implement();
		Transaction trns = null;
		List<Map<String,Object>> userReturn = new ArrayList<Map<String,Object>>();
        Session session = HibernateUtil.getSessionFactory().openSession();
     	try {
            trns = session.beginTransaction();
            String hql ="FROM User_Master where user_id=:id";
            Query query =  session.createQuery(hql);
            query.setString("id", user_id);
            user = query.list();
            String date_of_leaving=stu.Date(user.get(0).getBatch_id().getDate_of_leaving().toString());
            String date_of_returning=stu.Date(user.get(0).getBatch_id().getDate_of_returning().toString());
           // String deadline_of_booking=stu.Date(user.get(0).getBatch_id().getDeadline_of_booking().toString());;
            Map<String,Object> users = new HashMap<String,Object>();
        	users.put("fullname", user.get(0).getFullname());
        	users.put("username", user.get(0).getUsername());
        	users.put("batch_number", user.get(0).getBatch_id().getBatch_number());
        	users.put("date_of_leaving", user.get(0).getBatch_id().getDate_of_leaving().toString());
        	users.put("date_of_returning", user.get(0).getBatch_id().getDate_of_returning().toString());
        	//users.put("deadline_of_booking", deadline_of_booking);
        	users.put("no_of_ticket", user.get(0).getNo_of_ticket());
        	userReturn.add(users);
        	System.out.println(userReturn);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return userReturn;
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
	          // session.flush();
	          // session.close();
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
	
	public List<Schedule_Table> checkSchduleEmer(String user_id){
		List<Passenger> pas=new ArrayList<Passenger>(); 
		List<Schedule_Table> sche = new ArrayList<Schedule_Table>();
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
     	try {
            trns = session.beginTransaction();
            String hql ="FROM Passenger WHERE user_id!=:us_id and bus_per_schedule_id!=null";
            Query query =  session.createQuery(hql);
            query.setString("us_id", user_id);
            //query.setDate("today", new Date());
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
        add.setCreated_at(new Date());
        add.setUpdated_at(new Date());
        add.setNotification("new");
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(add);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
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
        add.setNotification("new");
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
	public Boolean cancel_ticket(Cancel_Ticket cancel) {
		System.out.println(cancel.date_of_travel);
		Transaction trns = null;
		Student_Implement stu = new Student_Implement();
		String date_of_travel=stu.Date(cancel.date_of_travel);
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	trns = session.beginTransaction();
            String hql = "Delete Passenger where user_id = :id and date_of_travel=:date and destination_id=:des" ;
		      Query query = session.createQuery(hql);
		      query.setString("id", cancel.getUser_id());
		      query.setString("date", date_of_travel);
		      query.setString("des", cancel.destination_id);
		      int result = query.executeUpdate();
		      session.getTransaction().commit();
		      System.out.println(result);
        }catch (RuntimeException e) {
            return false;
        } finally {
            session.close();
        }
		return true;
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
            pass = query.list();
            System.out.println(pass.size());
    		if(pass.size()!=0){
    			for(int i=0;i<pass.size();i++){
    				Map<String,Object> passenger = new HashMap<String,Object>();
    				passenger.put("destination_name",pass.get(i).getDestination_id().getDestination_name() );
    				passenger.put("destination_id",pass.get(i).getDestination_id().getDestination_id());
    				passenger.put("date_of_travel", pass.get(i).getDate_of_travel());
    				passenger.put("updated_at", pass.get(i).getUpdated_at());
    				passenger.put("notification", pass.get(i).getNotification());	
    				if(pass.get(i).getBus_per_schedule_id()!=null){
    					//Booking Approved
    					ByteArrayOutputStream out = QRCode.from(pass.get(i).getTicket_qrcode().toString()).to(ImageType.PNG).stream();  
    					byte[] test = out.toByteArray();
    					String encodedImage = Base64.getEncoder().encodeToString(test);
    					passenger.put("qrcode", encodedImage);
    					passenger.put("status", "booking_approved");
    					passenger.put("bus_per_sch_id", pass.get(i).getBus_per_schedule_id().getBus_per_schedule_id());
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
		System.out.println("Value: "+noti.getValue());
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
            System.out.println(tic_donate.size());
            for(int i=0;i<tic_donate.size();i++){
            	Map<String,Object> donate = new HashMap<String,Object>();
                donate.put("receive_from", tic_donate.get(i).getReceive_from().getFullname());
                donate.put("no_of_ticket", tic_donate.get(i).getNo_of_ticket());
                donate.put("updated_at", tic_donate.get(i).getUpdated_at());
                donate.put("status", "donated");
                donate.put("notification", tic_donate.get(i).getNotification());
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
	
	public List<Map<String, Object>> emergency_notification(NotificationModel noti) {
		System.out.println("Value: "+noti.getValue());
		Transaction trns = null;
		StudentDao stu = new Student_Implement();
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		List<Emergency_Request> emer_book = new ArrayList<Emergency_Request>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String hql ="FROM Emergency_Request WHERE user_id=:id";
            Query query =  session.createQuery(hql);
            query.setString("id", noti.getUser_id());
            if(noti.getValue()==0){
            	query.setMaxResults(8);
            }else{
            	query.setMaxResults(noti.getValue()*10);
            }
            emer_book = query.list(); 
            System.out.println(emer_book.size());
            for(int i=0;i<emer_book.size();i++){
            	Map<String,Object> emer = new HashMap<String,Object>();
            	emer.put("date_of_travel", emer_book.get(i).getSchedule_id().getDate_of_travel());
            	emer.put("destination_name", emer_book.get(i).getSchedule_id().getDestination_id().getDestination_name());
            	emer.put("updated_at", emer_book.get(i).getUpdated_at());
            	emer.put("status", emer_book.get(i).getStatus());
            	emer.put("reason", emer_book.get(i).getReason());
            	emer.put("notification", emer_book.get(i).getNotification());
                list.add(emer);            
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
		System.out.println("Value: "+noti.getValue());
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
            System.out.println(exch_seat.size());
            for(int i=0;i<exch_seat.size();i++){
            	Map<String,Object> exch = new HashMap<String,Object>();
            	exch.put("exchange_from", exch_seat.get(i).getExchange_from().getFullname());
            	exch.put("date_of_travel", exch_seat.get(i).getSchedule_id().getDate_of_travel());
            	exch.put("destination", exch_seat.get(i).getSchedule_id().getDestination_id().getDestination_name());
            	exch.put("updated_at", exch_seat.get(i).getUpdated_at());
            	exch.put("status", "exchange_seat");
            	exch.put("notification", exch_seat.get(i).getNotification());
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
		System.out.println("Value: "+noti.getValue());
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
            System.out.println(permission.size());
            for(int i=0;i<permission.size();i++){
            	Map<String,Object> perm = new HashMap<String,Object>();
            	perm.put("date_of_request", permission.get(i).getDate_of_request());
            	perm.put("reason", permission.get(i).getReason());
            	perm.put("updated_at", permission.get(i).getUpdated_at());
            	perm.put("status", permission.get(i).getStatus());
            	perm.put("notification", permission.get(i).getNotification());
            	list.add(perm);
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
		String des="h4if59igbpu3kj3";
		Student_Implement stu= new Student_Implement();
		String date=stu.Date("Aug 19, 2017");
		String user_id="t4jrtfh385";
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String hql = "Delete Passenger where user_id = :id and date_of_travel=:date and destination_id=:des" ;
		      Query query = session.createQuery(hql);
		      query.setString("id", user_id);
		      query.setString("date", date);
		      query.setString("des", des);
		      int result = query.executeUpdate();
		      session.getTransaction().commit();
		      System.out.println(result);
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
