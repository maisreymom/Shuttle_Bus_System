package com.DaoClasses;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;

import com.EntityClasses.Batch_Master;
import com.EntityClasses.Passenger;
import com.EntityClasses.Ticket_Donation;
import com.EntityClasses.User_Master;
import com.HibernateUtil.HibernateUtil;
import com.ModelClasses.BatchUpdate;
import com.ModelClasses.Donate;
import com.ModelClasses.Student;

public class Student_Implement implements StudentDao{
	
	
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            
            Query q = session.createQuery("from User_Master where user_id = :id ");
            System.out.println(don.getReceive_from());
            q.setParameter("id",don.getReceive_from());
            User_Master re_from = (User_Master)q.list().get(0);
            if(re_from.getNo_of_ticket()==0||re_from.getNo_of_ticket()<don.getNo_ticket()){
            	return null;
            }
            re_from.setNo_of_donate_ticket(re_from.getNo_of_donate_ticket()+don.getNo_ticket());
            re_from.setNo_of_ticket(re_from.getNo_of_ticket()-don.getNo_ticket());
            session.update(re_from);
            
            Query q1 = session.createQuery("from User_Master where user_id = :id ");
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
	
	
	
	public static void main(String args[]){
		Transaction trns = null;
		Ticket_Donation add = new Ticket_Donation();
        StudentDao ad = new Student_Implement();
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

        } finally {
           
            session.close();
        }
	}
	
	
}
