package com.DaoClasses;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import com.EntityClasses.Bus_Per_Schedule;
import com.EntityClasses.Bus_Report_Table;
import com.EntityClasses.Destination_Master;
import com.EntityClasses.Passenger;
import com.EntityClasses.Permission;
import com.EntityClasses.Schedule_Table;
import com.EntityClasses.User_Master;
import com.HibernateUtil.HibernateUtil;
import com.ModelClasses.Booking;
import com.ModelClasses.NotificationModel;
import com.ModelClasses.PermissionRequest;

public class Teacher_Implement implements TeacherDao{
	
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
			
	public List<Destination_Master> getDestinationId(){	
		List<Destination_Master> dest = new ArrayList<Destination_Master>();
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
     	try {
            trns = session.beginTransaction();
            String hql ="FROM Destination_Master WHERE status='true'";
            Query query =  session.createQuery(hql);
            dest = query.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return dest;
        } finally {
            session.flush();
            session.close();
        }
        return dest;
	}
		
	public List<Schedule_Table> getSchdule(){
		List<Schedule_Table> sche = new ArrayList<Schedule_Table>();
		Transaction trns = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
     	try {
            trns = session.beginTransaction();
            Criteria c = session.createCriteria(Schedule_Table.class, "sch");
            c.add(Restrictions.ge("sch.date_of_travel", new Date()));
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
	
	public List<Map<String,Object>> getPassengerDetail(String sch_id){
		List<Bus_Per_Schedule> bus = new ArrayList<Bus_Per_Schedule>();
		Transaction trns = null;
		List<Map<String,Object>> passPerDriver = new ArrayList<Map<String, Object>>();
		Set<Passenger> pa=new HashSet<Passenger>();
        Session session = HibernateUtil.getSessionFactory().openSession();
     	try {
            trns = session.beginTransaction();
            String hql ="FROM Bus_Per_Schedule as e where e.schedule_id = '"+sch_id+"'";
            Query query =  session.createQuery(hql);
            bus = query.list();
    		 
    		for(int i=0;i<bus.size();i++){
    			List<Map<String, Object>> passPer = new ArrayList<Map<String, Object>>();
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
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
          session.flush();
          session.close();
        }
        return passPerDriver;
	}
	
	
	public Boolean BookService(Booking[] passen){
		Boolean confirm=null;
		Transaction trns = null;
        User_Master user=new User_Master(passen[0].getUser_id());   
        Destination_Master destTo=new Destination_Master(passen[0].getDestination_id());
        Passenger passengerTo = new Passenger();
        passengerTo.setDate_of_booking(new Date());
        passengerTo.setUser_id(user);
        passengerTo.setDestination_id(destTo);
        passengerTo.setDate_of_travel(java.sql.Date.valueOf(passen[0].getDate_of_travel().toString().trim()));
        passengerTo.setCreated_at(new Date());
        passengerTo.setUpdated_at(new Date());
        passengerTo.setNotification("new");
        Set<Passenger> ps = new HashSet<Passenger>(); 
	    user.setPassenger(ps);
	    destTo.setPassenger(ps);
	    Configuration con=new Configuration();
        con.configure("hibernate.cfg.xml");
     	SessionFactory sf=con.buildSessionFactory();
     	Session session=sf.openSession();
        try {
            trns = session.beginTransaction();
            if(passen.length==2){
            	Destination_Master destBack=new Destination_Master(passen[1].getDestination_id());
                Passenger passengerBack = new Passenger();
                passengerBack.setDate_of_booking(new Date());
                passengerBack.setUser_id(user);
                passengerBack.setDestination_id(destBack);
                passengerBack.setDate_of_travel(java.sql.Date.valueOf(passen[1].getDate_of_travel().toString().trim()));
                passengerBack.setCreated_at(new Date());
                passengerBack.setUpdated_at(new Date());
                passengerBack.setBus_per_schedule_id(null);
                passengerBack.setNotification("new");
                ps.add(passengerTo);
        	    ps.add(passengerBack);
            	destBack.setPassenger(ps);
            	session.save(passengerTo);
            	session.save(passengerBack);
            }else{
            	ps.add(passengerTo);
            	session.save(passengerTo);
            }
            session.getTransaction().commit();
            confirm=true;
        } catch (RuntimeException e) {
        	confirm=false;
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            
        } finally {
           
            session.close();
        }      
        return confirm;
	}
	
	
	
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
	
	public List<Map<String,Object>> list_student_permission() {
		String status="permission_approved";
		Transaction trns = null;
		List<Permission> pas=new ArrayList<Permission>(); 
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	trns = session.beginTransaction();
            String hql ="FROM Permission WHERE status=:sta";
            Query query =  session.createQuery(hql);
            query.setString("sta", status);
            pas = query.list();
            for(int i=0;i<pas.size();i++){
            	Map<String,Object> mp=new HashMap<String,Object>();
                mp.put("username", pas.get(i).getUser_id().getUsername());
                mp.put("date_of_request", pas.get(i).getDate_of_request());
                mp.put("batch_number", pas.get(i).getUser_id().getBatch_id().getBatch_number());
                mp.put("full_name", pas.get(i).getUser_id().getFullname());
                mp.put("student_id", pas.get(i).getUser_id().getUser_id());
                mp.put("reason", pas.get(i).getReason());
                list.add(mp);
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
	
	
	public List<Map<String, Object>> permission_request_teacher(NotificationModel noti) {
		System.out.println("Value: "+noti.getValue());
		Transaction trns = null;  
		String status="permission_waiting";
		List<Permission> permission = new ArrayList<Permission>();
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String hql ="FROM Permission WHERE status=:sta";
            Query query =  session.createQuery(hql);
            query.setString("sta", status);
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
            	perm.put("batch_number", permission.get(i).getUser_id().getBatch_id().getBatch_number());
            	perm.put("student_fullname", permission.get(i).getUser_id().getFullname());
            	perm.put("student_username", permission.get(i).getUser_id().getUsername());
            	perm.put("student_id", permission.get(i).getUser_id().getUser_id());
            	perm.put("permission_id", permission.get(i).getId());
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
	public Boolean teacher_confirm_permission(PermissionRequest per){
		System.out.println(per.getStatus());
		Transaction trns = null;  
		Teacher_Implement tea= new Teacher_Implement();
		String date_of_request =tea.Date(per.getDate_of_request());
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {		    
		    trns = session.beginTransaction();
            String hql = "UPDATE Permission set status = :sta "
	        		   +"WHERE user_id= :id and date_of_request= :date";
		    Query query = session.createQuery(hql);
		    query.setString("id", per.getUser_id());
		    query.setString("date", date_of_request);
		    query.setString("sta", per.getStatus());
		    int result = query.executeUpdate();
		    session.getTransaction().commit();
		    System.out.println(result);
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
	   }
	/*
	public boolean addBooking(Set<Teacher> teacher) {
        Transaction trns = null;
        User_Master user=new User_Master("t4jrtfh385");        
        Destination_Master dest = new Destination_Master("756fh4hfyo");     
        Passenger passenger = new Passenger();
        passenger.setDate_of_travel("2017-05-18 13:17:17");
        passenger.setDate_of_booking("2017-05-18 13:17:17"); 
        passenger.setDestination_id(dest);
	    passenger.setUser_id(user);	    
	    Set<Passenger> ps = new HashSet<Passenger>();
	    ps.add(passenger);
	    user.setPassenger(ps);
	    dest.setPassenger(ps);
	    Configuration con=new Configuration();
        con.configure("hibernate.cfg.xml");
     	SessionFactory sf=con.buildSessionFactory();
     	Session session=sf.openSession();
        try {
            trns = session.beginTransaction();
            session.save(passenger);
            session.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
           
            //session.close();
        }
	
    }*/
	
	public static void main(String arg[]){		
		Transaction trns = null;  
		String id="t4jrtfh385";
		String date="2017-08-17";
		String status="permission_approved";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String hql = "UPDATE Permission set status = :sta "
	        		   +"WHERE user_id= :id and date_of_request= :date";
		    Query query = session.createQuery(hql);
		    query.setString("id", id);
		    query.setString("date", date);
		    query.setString("sta", status);
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
