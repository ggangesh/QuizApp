package com.quizapp.display;

import java.util.Scanner;

import com.quizapp.Question;

public class AdminDisplay {
	Scanner sc = new Scanner(System.in);
	
	public static void displayAllStudentScore() 
	{
		
	}
	
	public static void studentScoreUsindId() 
	{
		
	}
	
	public static void addQuetionIntoDatabase() 
	{
		Question question = new Question();
		question.inputQuetion();
		
	}

	public void adminInfo() {
		System.out.println("\r\n" + "1. Display all students score as per ascending order \r\n"
				+ "2. Fetch student score by using id \r\n"
				+ "3. Add question with 4 options into database");
		int input = sc.nextInt();
		switch (input) {
		case 1:
			displayAllStudentScore();
			break;
		case 2:
			studentScoreUsindId();
			break;
		case 3:
			addQuetionIntoDatabase();
			break;
		}

	}
}
