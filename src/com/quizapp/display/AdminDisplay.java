package com.quizapp.display;

import java.util.Scanner;

public class AdminDisplay {
    Scanner scanner = new Scanner(System.in);

    public void allOperations(String msg) {
        if (msg == null) {
            System.out.println("Hi Admin, You may perform these operations");
        } else {
            System.out.println(msg);
        }
        System.out.println(" 1. Get all students scores");
        System.out.println(" 2. Get particular student score");
        System.out.println(" 3. Add question for quiz");
        System.out.println(" 0. OR Go to App Home");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
//                displayAllStudentsScoreInAscOrder();
                break;
            case 2:
                displayStudentScoreById();
                break;
            case 3:
//                addQuestion();
        }
        System.out.println("Do you want to do other operations? (Y/N)");
        String ifFutherOps = scanner.next();
        if (ifFutherOps.equalsIgnoreCase("Y")) {
            allOperations("");
        }
    }

    private void displayStudentScoreById() {
        System.out.println("Enter student ID");
        int studentID = scanner.nextInt();
        getStudentScoreById();
    }
}
