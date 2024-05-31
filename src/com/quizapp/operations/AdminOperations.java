package com.quizapp.operations;

import com.quizapp.Question;
import com.quizapp.Student;

import java.sql.*;

public class AdminOperations {
	public static int getStudentScoreById(int id) {
		int score = -1;
		String sqlQuery = "select score from quizscore where studentid=?";
		Connection conn = (new DBConnectionImpl()).getConnection();
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = conn.prepareStatement(sqlQuery);
			preparedStmt.setInt(1, id);
			ResultSet resultSet = preparedStmt.executeQuery();
			while (resultSet.next()) {
				score = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return score;
	}

	public static Student getStudentById(int id) {
		Student student = null;
		String sqlQuery = "select * from students where id=?";
		Connection conn = (new DBConnectionImpl()).getConnection();
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = conn.prepareStatement(sqlQuery);
			preparedStmt.setInt(1, id);
			ResultSet resultSet = preparedStmt.executeQuery();
			while (resultSet.next()) {
				student = new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
						resultSet.getLong(8));
			}
			preparedStmt.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return student;
	}

	public static boolean addQuestion(Question question) {
		String sqlQuery = "insert into questions(query, option1, option2, option3, option4, answerkey) "
				+ "values (?,?,?,?,?,?)";
		Connection conn = (new DBConnectionImpl()).getConnection();
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = conn.prepareStatement(sqlQuery);
			preparedStmt.setString(1, question.getQuery());
			preparedStmt.setString(2, question.getOptions().get(0));
			preparedStmt.setString(3, question.getOptions().get(1));
			preparedStmt.setString(4, question.getOptions().get(2));
			preparedStmt.setString(5, question.getOptions().get(3));
			preparedStmt.setInt(6, question.getAnswerkey());

			int rows = preparedStmt.executeUpdate();
			if (rows == 1) {
				return true;
			}
			preparedStmt.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;
	}

	public static int getCountOfQuestionsAdded() {
		String sqlQuery = "select count(id) from questions";
		Connection conn = (new DBConnectionImpl()).getConnection();
		Statement statement = null;
		int countQuestions = 0;
		try {
			statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			while (resultSet.next()) {
				countQuestions = resultSet.getInt(1);
			}
			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return countQuestions;
	}

	public static void displayAllStudentsScoreInAscOrder() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = (new DBConnectionImpl()).getConnection();
			String query = "select firstname,lastname,score from students inner join quizscore on students.id = quizscore.studentid ORDER BY score ASC";

			preparedStatement = connection.prepareStatement(query);

			resultSet = preparedStatement.executeQuery();

			System.out.println("StudentsName                  Score");
			System.out.println("---------------------------------------");

			while (resultSet.next()) {
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				int score = resultSet.getInt("score");

				int fullNameLength = firstName.length() + lastName.length();

				System.out.print(firstName + " " + lastName);

				int spacesNeeded = 30 - fullNameLength;

				for (int i = 0; i < spacesNeeded; i++) {
					System.out.print(" ");
				}

				System.out.println(" - " + score);
			}

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
