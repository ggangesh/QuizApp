package com.quizapp;

import java.sql.DriverManager;
import java.sql.Connection;

public class MyConnection {
	public static final String DRIVER_CLASS="com.mysql.cj.jdbc.Driver";
	public static final String URL="jdbc:mysql://localhost:3306/quizdb";
	public static final String USER_NAME="root";
	public static final String PASSWORD="Giridhar@96";
	private static Connection connection;

	public static Connection getConnection() {
		
		try {
			//Importing a driver class to project
			Class.forName(DRIVER_CLASS);
			//creating a connetion object
			connection = DriverManager.getConnection(URL,USER_NAME,PASSWORD);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
}
