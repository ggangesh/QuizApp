package com.quizapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Student {
	Connection connetion = null;
	PreparedStatement preStatement = null;

	public void studentDataStore(String firstname, String lastname, String username, String password, String address,
			String email, Long mobileno)
	
	{
		try {
			String sqlquary = "insert into students (firstname,lastname,username,password,address,email,mobileno)"
					+ "values(?,?,?,?,?,?,?)";
			connetion = MyConnection.getConnection();
			preStatement = connetion.prepareStatement(sqlquary);
			preStatement.setString(1, firstname);
			preStatement.setString(2, lastname);
			preStatement.setString(3, username);
			preStatement.setString(4, password);
			preStatement.setString(5, address);
			preStatement.setString(6, email);
			preStatement.setLong(7, mobileno);

			int i = preStatement.executeUpdate();
			System.out.println("Execute done" + i);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				preStatement.close();
				connetion.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void inputStudentInfo() {

		Scanner sc = new Scanner(System.in);
		String userInput;
		
			System.out.println("Enter your firstname");
			String firstname = sc.next();
			System.out.println("Enter your lastname");
			String lastname = sc.next();
			System.out.println("Enter your username");
			String username = sc.next();
			System.out.println("Enter your password");
			String password = sc.next();
			System.out.println("Enter your address");
			String address = sc.next();
			System.out.println("Enter your email");
			String email = sc.next();
			System.out.println("Enter your mobilNumber");
			Long mobileno = sc.nextLong();

			Student input = new Student();
			input.studentDataStore(firstname, lastname, username, password, address, email, mobileno);

			System.out.print("Registration Sucessfully");
			
	}
}

//		do {
//			System.out.println("Enter your firstname");
//			String firstname = sc.next();
//			System.out.println("Enter your lastname");
//			String lastname = sc.next();
//			System.out.println("Enter your username");
//			String username = sc.next();
//			System.out.println("Enter your password");
//			String password = sc.next();
//			System.out.println("Enter your address");
//			String address = sc.next();
//			System.out.println("Enter your email");
//			String email = sc.next();
//			System.out.println("Enter your mobilNumber");
//			Long mobileno = sc.nextLong();
//
//			Student input = new Student();
//			input.studentDataStore(firstname, lastname, username, password, address, email, mobileno);
//
//			System.out.print("Do you want to continue? (yes/no): ");
//			userInput = sc.next();
//		} while (userInput.equalsIgnoreCase("yes"));
//		sc.close();
//		System.out.println("No next Student Available For Quiz Registration.");
//	}
//}
