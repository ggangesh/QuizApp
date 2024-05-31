package com.quizapp.operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminOperations {
	public static void displayAllStudentsScoreInAscOrder() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizdb", "root", "root");

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
