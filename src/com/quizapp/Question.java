package com.quizapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Question {

	Connection connetion = null;
	PreparedStatement preStatement = null;

	public void quetionDataStore(String question, String option1, String option2, String option3, String option4,
			int answerkey)

	{
		try {
			String sqlquaryForQuetion = "insert into questions (question, option1, option2, option3, option4, answerkey)"
					+ "values(?,?,?,?,?,?)";
			connetion = MyConnection.getConnection();
			preStatement = connetion.prepareStatement(sqlquaryForQuetion);
			preStatement.setString(1, question);
			preStatement.setString(2, option1);
			preStatement.setString(3, option2);
			preStatement.setString(4, option3);
			preStatement.setString(5, option4);
			preStatement.setInt(6, answerkey);

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

	public void inputQuetion() {

		Scanner sc = new Scanner(System.in);
		String userInput;

		do {
			System.out.println("Enter your query");
			String question = sc.nextLine();
			System.out.println("Enter your option1");
			String option1 = sc.nextLine();
			System.out.println("Enter your option2");
			String option2 = sc.nextLine();
			System.out.println("Enter your option3");
			String option3 = sc.nextLine();
			System.out.println("Enter your option4");
			String option4 = sc.nextLine();
			System.out.println("Enter your answerkey");
			int answerkey = sc.nextInt();

			//Question input = new Question();
			quetionDataStore(question, option1, option2, option3, option4, answerkey);

			System.out.print("Do you want to continue? (yes/no): ");
			userInput = sc.next();
		} while (userInput.equalsIgnoreCase("yes"));
		sc.close();
		System.out.println("Thank You.....!!!!!");
	}
}
