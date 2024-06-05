package com.quizapp.display;

import java.util.Scanner;

public class AppDisplay {
    Scanner scanner = new Scanner(System.in);

    public void appDisplay(String msg) {
        System.out.println("---------------------------------------------------------");
        System.out.println("Welcome to QuizApp");
        System.out.println(msg);
        System.out.println("1. Student ");
        System.out.println("2. Admin ");
        System.out.println("0. EXIT APP");
        int role = scanner.nextInt();
        scanner.nextLine();
        switch (role) {
            case 1:
                StudentDisplay studentDisplay = new StudentDisplay();
                studentDisplay.displayLoginAndRegistration(null);
                break;
            case 2:
                AdminDisplay admin = new AdminDisplay();
                admin.allOperations(null);
                break;
            case 0:
                System.out.println("Shutting dowm ...");
                break;
        }
    }
}
