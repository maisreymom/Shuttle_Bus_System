package com.DaoClasses;

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
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import com.EntityClasses.Bus_Per_Schedule;
import com.EntityClasses.Bus_Report_Table;
import com.EntityClasses.Passenger;
import com.EntityClasses.User_Master;
import com.HibernateUtil.HibernateUtil;

public class Diver_Impl implements Driver_Inf{
	
	public Map<String,Object> checkConfim(String bus_id){
		List<Bus_Report_Table> bus = new ArrayList<Bus_Report_Table>();
		Map<String,Object> bus_per = new HashMap<String,Object>();
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
     	try {
            trns = session.beginTransaction();
            String hql ="FROM Bus_Report_Table as e where e.bus_per_schedule_id='"+bus_id+"'";
            Query query =  session.createQuery(hql);
            bus = query.list(); 
            bus_per.put("actual_departure_time",bus.get(0).getActual_departure_time());
    		bus_per.put("actual_arrival_time",bus.get(0).getActual_arrival_time());
            
            System.out.println(bus);
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            
        } finally {
           
            session.close();
        }      
        return bus_per;
	}
	
	public String leaveConfim(String bus_per_id){
			Calendar cal = Calendar.getInstance();
		    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		    String time= sdf.format(cal.getTime()).toString();
			System.out.println(time);
			Transaction trns = null;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	     	try {
	     		trns = session.beginTransaction();
	            Query q = session.createQuery("update Bus_Report_Table set actual_departure_time=:recievedDate where bus_per_schedule_id=:Id");
	            q.setString("recievedDate", time);
	            q.setString("Id", bus_per_id);
	            q.executeUpdate();
	            trns.commit();  
	        } catch (RuntimeException e) {
	            if (trns != null) {
	                trns.rollback();
	            }
	            e.printStackTrace();
	            
	        } finally {
	           
	            session.close();
	        }      
	        return bus_per_id;
		}
	public String arrivedConfim(String bus_per_id){
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	    String time= sdf.format(cal.getTime());
		System.out.println(time);
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
     	try {
     		trns = session.beginTransaction();
            Query q = session.createQuery("update Bus_Report_Table set actual_arrival_time=:recievedDate where bus_per_schedule_id=:Id");
            q.setString("recievedDate", time);
            q.setString("Id", bus_per_id);
            q.executeUpdate();
            trns.commit();  
        }catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            
        } finally {
           
            session.close();
        }      
        return bus_per_id;
	}
	//Driver
		public List<Bus_Per_Schedule> getdriverSche(){
			List<Bus_Per_Schedule> sche = new ArrayList<Bus_Per_Schedule>();
			Transaction trns = null;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	     	try {
	            trns = session.beginTransaction();
	            Criteria c = session.createCriteria(Bus_Per_Schedule.class, "busPer");
	            c.createAlias("busPer.schedule_id", "sche_id");
	            c.add(Restrictions.ge("sche_id.date_of_travel", new Date()));
	            c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	            sche=c.list();
	            System.out.println(sche.size());
	        }catch (RuntimeException e) {
	            if (trns != null) {
	                trns.rollback();
	            }
	            e.printStackTrace();
	     	}finally {
		          // session.flush();
		          // session.close();
		    }
	        return sche;
		}
		
		
		
		public List<Map<String, Object>> getDetail(String busPer_id){
			List<Bus_Per_Schedule> bus = new ArrayList<Bus_Per_Schedule>();
			List<Map<String, Object>> passReturn = new ArrayList<Map<String, Object>>();
			Set<Passenger> pa=new HashSet<Passenger>(); 
			Transaction trns = null;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	     	try {
	            trns = session.beginTransaction();
	            String hql ="FROM Bus_Per_Schedule as e where e.bus_per_schedule_id = '"+busPer_id+"'";
	            Query query =  session.createQuery(hql);
	            bus = query.list();

	    		for(int i=0;i<bus.size();i++){
	    			pa = bus.get(i).getPassenger();
	    			for(Passenger b:pa){
	    				Map<String,Object> sch = new HashMap<String,Object>();
	    	        	sch.put("passenger_fullname",b.getUser_id().getFullname());
	    	        	sch.put("passenger_username",b.getUser_id().getUsername());
	    	        	sch.put("batch",b.getUser_id().getBatch_id().getBatch_number());
	    	        	sch.put("role",b.getUser_id().getRole_id().getRole_name());
	    	        	sch.put("seat_number",b.getSeat_number());
	    	        	passReturn.add(sch);
	    			}
	    		}
	            
	        } catch (RuntimeException e) {
	            e.printStackTrace();
	        } finally {
	           session.flush();
	           session.close();
	        }
	        return passReturn;
		}
		
		public List<Bus_Per_Schedule> myschedule_driver(String driver_id){
			User_Master user=new User_Master(driver_id);
			List<Bus_Per_Schedule> sche = new ArrayList<Bus_Per_Schedule>();
			Transaction trns = null;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	     	try {
	            trns = session.beginTransaction();
	            Criteria c = session.createCriteria(Bus_Per_Schedule.class, "busPer");
	            c.add(Restrictions.eq("busPer.user_id", user));
	            c.createAlias("busPer.schedule_id", "sche_id");
	            c.add(Restrictions.ge("sche_id.date_of_travel", new Date()));
	            c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	            sche=c.list();
	            System.out.println(sche.size());
	        }catch (RuntimeException e) {
	            if (trns != null) {
	                trns.rollback();
	            }
	            e.printStackTrace();
	     	}finally {
		          // session.flush();
		          // session.close();
		    }
	        return sche;
		}
		
		
		public static void main(String args[]){
			String driver_id="21lrdipquks8nme";
			User_Master user=new User_Master("jdfh4737rb");
			List<Bus_Per_Schedule> sche = new ArrayList<Bus_Per_Schedule>();
			Transaction trns = null;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	     	try {
	            trns = session.beginTransaction();
	            Criteria c = session.createCriteria(Bus_Per_Schedule.class, "busPer");
	            c.add(Restrictions.eq("busPer.user_id", user));
	            c.createAlias("busPer.schedule_id", "sche_id");
	            c.add(Restrictions.ge("sche_id.date_of_travel", new Date()));
	            c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	            sche=c.list();
	            System.out.println(sche.size());
	        }catch (RuntimeException e) {
	            if (trns != null) {
	                trns.rollback();
	            }
	            e.printStackTrace();
	     	}finally {
		          // session.flush();
		          // session.close();
		    }
		}

}
