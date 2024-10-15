package com.crimsonlogic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnect {
	
	public static Connection dbConnection() {
		
		Connection conn = null;
		
		String url="jdbc:postgresql://localhost:5432/Prod_Pharmacy";
		String username="postgres";
		String password="crimson@123";
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url,username,password);
			
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return conn;
	}
	
	public static void main(String[] args) 
	{
		System.out.println(dbConnection());
	}

	public static void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
		// TODO Auto-generated method stub
		
	}
}
