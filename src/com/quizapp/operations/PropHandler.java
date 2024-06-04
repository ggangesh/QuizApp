package com.quizapp.operations;

import java.io.FileInputStream;
import java.util.Properties;

public class PropHandler {

	public static void main(String[] args) {
		Properties properties = new Properties();

		try  {
			FileInputStream input = new FileInputStream("config.properties");
			// Load the properties from the file
			properties.load(input);

			// Access the properties
			String dbUrl = properties.getProperty("database.url");
			String dbUser = properties.getProperty("database.user");
			String dbPassword = properties.getProperty("database.password");
			String maxConnections = properties.getProperty("max.connections");

			// Print the properties to verify
			System.out.println("Database URL: " + dbUrl);
			System.out.println("Database User: " + dbUser);
			System.out.println("Database Password: " + dbPassword);
			System.out.println("Max Connections: " + maxConnections);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
