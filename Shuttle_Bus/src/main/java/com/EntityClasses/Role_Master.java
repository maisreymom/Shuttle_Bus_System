package com.EntityClasses;


import java.util.Set;

public class Role_Master {

	private String role_id;
	private String role_name;
	
	private Set<User_Master> user_master;
	
	public Set<User_Master> getUser_master() {
		return user_master;
	}
	
	public void setUser_master(Set<User_Master> user_master) {
		this.user_master = user_master;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
}
