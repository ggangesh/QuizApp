package com.quizapp.display;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizApp {
	
//StudentDisplay display = new StudentDisplay();
	public void quizDisplayList() {
		List<MCQ> mcqs = getMCQsFromDatabase();
		
		
		int userId = 4; // Assuming you have a user ID, change as needed

		Result result = runQuiz(mcqs, userId);
		saveResultToDatabase(result);
		StudentDisplay.StoreAndDisplayResult();
	}

	private static List<MCQ> getMCQsFromDatabase() {
		List<MCQ> mcqs = new ArrayList<>();
		String url = "jdbc:mysql://localhost:3306/quizdb";
		String user = "root";
		String password = "Giridhar@96";

		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM questions");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String question = resultSet.getString("question");
				String option1 = resultSet.getString("option1");
				String option2 = resultSet.getString("option2");
				String option3 = resultSet.getString("option3");
				String option4 = resultSet.getString("option4");
				int answerkey = resultSet.getInt("answerkey");

				MCQ mcq = new MCQ(id, question, option1, option2, option3, option4, answerkey);
				mcqs.add(mcq);
			}

			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mcqs;
	}

	public static Result storeResultOfQuiz() {
		return null;
		
	
	}

	public static Result runQuiz(List<MCQ> mcqs, int userId) {
		Scanner sc = new Scanner(System.in);
		int score = 0;

		for (MCQ mcq : mcqs) {
			System.out.println(mcq.getQuestion());
			System.out.println("A. " + mcq.getOption1());
			System.out.println("B. " + mcq.getOption2());
			System.out.println("C. " + mcq.getOption3());
			System.out.println("D. " + mcq.getOption4());
			System.out.print("Your answer: ");
			// int answer = sc.next().toUpperCase().charAt(0);
			int answer = sc.nextInt();

			if (answer == mcq.getAnswerkey()) {
				score++;
			}
		}

		System.out.println("Your score is : " + score + " Out of " + mcqs.size());
		return new Result(userId, score);
	}

	public static void saveResultToDatabase(Result result) {
		String url = "jdbc:mysql://localhost:3306/quizdb";
		String user = "root";
		String password = "Giridhar@96";

		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			String query = "INSERT INTO results (user_id, score) VALUES (?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, result.getUserId());
			preparedStatement.setInt(2, result.getScore());
			preparedStatement.executeUpdate();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void displayScore() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizdb", "root", "Giridhar@96");
			Scanner scanner = new Scanner(System.in);

			System.out.println("Enter username:");
			String username = scanner.nextLine();

			System.out.println("Enter password:");
			String password = scanner.nextLine();

			if (authenticateUser(conn, username, password)) {
				System.out.println("Login successful!");

				int userId = getUserId(conn, username);
				displayUserScores(conn, userId);
			} else {
				System.out.println("Invalid username or password.");
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static boolean authenticateUser(Connection conn, String username, String password) throws SQLException {
		String sql = "SELECT * FROM students WHERE username = ? AND password = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();

		return rs.next();
	}

	private static int getUserId(Connection conn, String username) throws SQLException {
		String sql = "SELECT id FROM students WHERE username = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, username);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			return rs.getInt("id");
		} else {
			throw new SQLException("User ID not found.");
		}
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
}
