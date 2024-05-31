package com.quizapp.display;

import java.util.Scanner;

public class AppDisplay {
    Scanner scanner = new Scanner(System.in);

    public void welcome() {
        System.out.println("Welcome to QuizApp");
        System.out.println("How would you like to visit as ?");
        System.out.println("1. Student  or else as ");
        System.out.println("2. Admin?");
        int role = scanner.nextInt();
        scanner.nextLine();
        switch (role) {
            case 1:
                break;
            case 2:
                AdminDisplay admin = new AdminDisplay();
                admin.allOperations(null);
                break;
        }
    }
}
