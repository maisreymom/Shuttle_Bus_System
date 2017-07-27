package com.DaoClasses;

import java.util.Set;

import com.ModelClasses.Student;

public interface StudentDao {
	
	public boolean addBooking(Set <Student> student);
}
