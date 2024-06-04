package com.quizapp.display;

import java.util.Scanner;

public class AppDisplay {
	Scanner sc = new Scanner(System.in);

	public void appDisplay() {

		StudentDisplay studentDisplay = new StudentDisplay();
		AdminDisplay adminDisplay = new AdminDisplay();

		System.out.println("==========>>>>>  Welcome to Quiz App  <<<<<==========");
		System.out.println("Select Your Roll\n 1:- Student\n 2:- Admin");
		int input = sc.nextInt();
		switch (input) {
		case 1:
			studentDisplay.studentInfo();
			break;
		case 2:
			adminDisplay.adminInfo();
			break;
		}
		sc.close();
	}
}
