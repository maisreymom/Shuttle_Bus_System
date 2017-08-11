package com.DaoClasses;



import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import com.EntityClasses.Schedule_Table;
import com.EntityClasses.User_Master;
import com.HibernateUtil.HibernateUtil;
import com.ModelClasses.Teacher;

public class Teacher_Implement implements TeacherDao{
	
	public List<User_Master> getUserInfo(String user_id){
		List<User_Master> user = new ArrayList<User_Master>();
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
     	try {
            trns = session.beginTransaction();
            String hql ="FROM User_Master";
            Query query =  session.createQuery(hql);
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
	
	
	public List<Schedule_Table> checkSchdule(){
		List<Schedule_Table> sche = new ArrayList<Schedule_Table>();
		Transaction trns = null;
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY/MM/dd");
		String today= formatter.format(date);
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
		
	public List<Schedule_Table> getSchdule(){
		List<Schedule_Table> sche = new ArrayList<Schedule_Table>();
		Transaction trns = null;
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY/MM/dd");
		String today= formatter.format(date);
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
	
	public List<Bus_Per_Schedule> getPassengerDetail(String sch_id){
		List<Bus_Per_Schedule> bus = new ArrayList<Bus_Per_Schedule>();
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
     	try {
            trns = session.beginTransaction();
            String hql ="FROM Bus_Per_Schedule as e where e.schedule_id = '"+sch_id+"'";
            Query query =  session.createQuery(hql);
            bus = query.list();
            System.out.println(bus.get(1).getNumber_of_seats());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return bus;
        } finally {
          // session.flush();
           //session.close();
        }
        return bus;
	}
	
	
	public Boolean BookService(Teacher[] passen){
		System.out.println(passen[0].getDestination_id());
		System.out.println(passen[0].getDate_of_travel());
		Boolean confirm=null;
		Transaction trns = null;
        User_Master user=new User_Master("t4jrtfh385");   
        Destination_Master destTo=new Destination_Master(passen[0].getDestination_id());
        Passenger passengerTo = new Passenger();
        passengerTo.setDate_of_booking(new Date());
        passengerTo.setUser_id(user);
        passengerTo.setDestination_id(destTo);
        passengerTo.setDate_of_travel(java.sql.Date.valueOf(passen[0].getDate_of_travel().toString().trim()));
        passengerTo.setCreated_at(new Date());
        passengerTo.setUpdated_at(new Date());
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

	//Driver
	public List<Bus_Per_Schedule> getdriverSche(){
		List<Bus_Per_Schedule> sche = new ArrayList<Bus_Per_Schedule>();
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		String today= formatter.format(date).toString();
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
     	try {
            trns = session.beginTransaction();
            Criteria c = session.createCriteria(Bus_Per_Schedule.class, "busPer");
            c.createAlias("busPer.schedule_id", "sche_id");
            c.add(Restrictions.ge("sche_id.date_of_travel", today));
            c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
            sche=c.list();
        }catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
     	}
        return sche;
	}
	
	
	public List<Bus_Per_Schedule> getDetail(String busPer_id){
		List<Bus_Per_Schedule> bus = new ArrayList<Bus_Per_Schedule>();
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
     	try {
            trns = session.beginTransaction();
            String hql ="FROM Bus_Per_Schedule as e where e.bus_per_schedule_id = '"+busPer_id+"'";
            Query query =  session.createQuery(hql);
            bus = query.list();
            System.out.println("Bus: "+bus);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return bus;
        } finally {
            session.flush();
            session.close();
        }
        return bus;
	}
	
	
	public List<Bus_Report_Table> checkConfim(String bus_id){
		List<Bus_Report_Table> bus = new ArrayList<Bus_Report_Table>();
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
     	try {
            trns = session.beginTransaction();
            String hql ="FROM Bus_Report_Table as e where e.bus_per_schedule_id='"+bus_id+"'";
            Query query =  session.createQuery(hql);
            bus = query.list(); 
            System.out.println(bus);
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            
        } finally {
           
            session.close();
        }      
        return bus;
	}
	
	
	//not yet change schId to busPerschID
	public String leaveConfim(String bus_per_id){
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
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
	public String arrivedConfim(String bus_per_id){
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
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
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    System.out.println(sdf.format(cal.getTime())); 
	}

	
}
