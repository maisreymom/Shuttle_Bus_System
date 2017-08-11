package com.DaoClasses;


import java.util.List;
import java.util.Set;

import com.EntityClasses.User_Master;
import com.ModelClasses.Teacher;

public interface TeacherDao {
	public List<User_Master> getUserInfo(String user_id);
}
