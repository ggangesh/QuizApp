package com.quizapp.display;

import java.util.Scanner;

import com.quizapp.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class StudentDisplay {
	static int userId;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/quizdb";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "Giridhar@96";
	Scanner scanner = new Scanner(System.in);

	// QuizApp quizApp = new QuizApp();
	public void studentLogin() {

		System.out.print("Enter username: ");
		String username = scanner.next();

		System.out.print("Enter password: ");
		String password = scanner.next();

		try {
			if (loginStudent(username, password)) {
				System.out.println("Login successful.");
				// if custemor login successful then call below method
				displayAfterLogin();
			} else {
				System.out.println("Invalid username or password.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static boolean loginStudent(String username, String password) throws SQLException {
		String selectQuery = "SELECT * FROM students WHERE username = ? AND password = ?";

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.next();
			}
		}
	}

	public void storeResult() {

	}

	public void displayQuizResult() {

		// quizApp.displayScore();
		StoreAndDisplayResult();

	}

	public static void studentRegistration() {
		Student student = new Student();
		student.inputStudentInfo();
	}

	public static void StoreAndDisplayResult() {
		try {
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter username:");
			String username = scanner.nextLine();
			System.out.println("Enter password:");
			String password = scanner.nextLine();
			userId = authenticateUser(conn, username, password);
			if (userId != -1) {
				System.out.println("Login successful!");
				System.out.println("Enter score:");
				int score = scanner.nextInt();
				saveResult(conn, userId, score);
				System.out.println("Result saved successfully.");
				// Display updated results
				displayUserScores(conn, userId);
			} else {
				System.out.println("Invalid username or password.");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static int authenticateUser(Connection conn, String username, String password) throws SQLException {
		String sql = "SELECT id FROM students WHERE username = ? AND password = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			return rs.getInt("id");
		} else {
			return -1; // User not found or incorrect credentials
		}
	}

	private static void saveResult(Connection conn, int userId, int score) throws SQLException {
		String sql = "INSERT INTO results (user_id, score) VALUES (?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, score);
		pstmt.executeUpdate();
	}

	private static void displayUserScores(Connection conn, int userId) throws SQLException {
		String sql = "SELECT score, quiz_date FROM results WHERE user_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, userId);
		ResultSet rs = pstmt.executeQuery();

		System.out.println("Scores for user ID " + userId + ":");
		while (rs.next()) {
			int score = rs.getInt("score");
			Timestamp quizDate = rs.getTimestamp("quiz_date");
			System.out.println("Score: " + score + ", Date: " + quizDate);
		}
	}

	public void displayAfterLogin() {

		System.out.println("Select Below Option");
		System.out.println(
				"1. Display the list of questions \n" + "2. Store Quiz result into database\n3. Display Quiz result");
		int option = scanner.nextInt();
		switch (option) {
		case 1:
			// displyList();
			QuizApp quizApp = new QuizApp();
			quizApp.quizDisplayList();
			break;
		case 2:
			storeResult();
			break;
		case 3:
			displayQuizResult();
			break;
		}
		scanner.close();
	}
//}

	public void studentInfo() {
		// StudentDisplay studentDisplay = new StudentDisplay();
		System.out.println(
				"If you are new student here then register. \nYou have already register then select login option");
		System.out.println("1. Student Registration \n2. Student Login ");

		int input = scanner.nextInt();
		switch (input) {
		case 1:
			studentRegistration();
			break;
		case 2:
			studentLogin();
			break;
//			default : System.out.println("Please enter valid choice 1 or 2");
//		}System.out.println("Do you want to do other operations? (Y/N)");
//	    String ifFutherOps = scanner.next();
//	    scanner.nextLine();
//	    if (ifFutherOps.equalsIgnoreCase("Y")) {
//	        //allOperations("");
//	    	studentInfo();
//		
//	} 
		}
	}
}
