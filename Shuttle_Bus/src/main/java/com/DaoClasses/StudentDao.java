package com.DaoClasses;

import java.util.List;
import java.util.Set;

import com.EntityClasses.Batch_Master;
import com.EntityClasses.User_Master;
import com.ModelClasses.Donate;
import com.ModelClasses.Student;

public interface StudentDao {
	public List<Batch_Master> Batch();
	public List<User_Master> Bat_User(Donate donate) ;
	public Boolean donate(Donate bat);
}
