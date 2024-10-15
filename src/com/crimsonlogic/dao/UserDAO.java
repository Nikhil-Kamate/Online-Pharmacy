package com.crimsonlogic.dao;

import java.sql.SQLException;

import com.crimsonlogic.model.User;

public interface UserDAO {
	
	// Method to add a new users
	public boolean addUser(User u);
	
	// Method for login
	User login(String user_email, String user_password) throws SQLException;
	
}
