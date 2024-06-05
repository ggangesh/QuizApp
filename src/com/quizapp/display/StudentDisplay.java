package com.quizapp.display;

import com.quizapp.Question;
import com.quizapp.operations.AdminOperations;
import com.quizapp.operations.StudentOperations;

import java.util.List;
import java.util.Scanner;

import static com.quizapp.operations.StudentOperations.*;

public class StudentDisplay {
    Scanner scanner = new Scanner(System.in);

    public void displayLoginAndRegistration(String msg) {
        System.out.println("---------------------------------------------------------");
        if (msg != null) {
            System.out.println(msg);
        }
        System.out.println("1. Student Registration \n2. Student Login ");
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                register();
                break;
            case 2:
                login();
                break;
        }
    }

    private void register() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your firstname : ");
        String firstname = sc.next();
        System.out.print("Enter your lastname : ");
        String lastname = sc.next();
        System.out.print("Enter your username : ");
        String username = sc.next();
        System.out.print("Enter your password : ");
        String password = sc.next();
        System.out.print("Enter your address : ");
        String address = sc.next();
        System.out.print("Enter your email : ");
        String email = sc.next();
        System.out.print("Enter your mobilNumber : ");
        Long mobileno = sc.nextLong();
        scanner.nextLine();
        StudentOperations.studentDataStore(firstname, lastname, username, password, address, email, mobileno);
        System.out.println("Registration Sucessfully");
        System.out.print("Login now ? (Y?N) : ");
        String yesNo = scanner.next();
        System.out.println();
        if (yesNo.equalsIgnoreCase("y")) {
            login();
        } else {
            AppDisplay appDisplay = new AppDisplay();
            appDisplay.appDisplay("Login as : ");
        }
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        int studentId;
        try {
            if ((studentId = StudentOperations.loginStudent(username, password)) != -1) {
                System.out.println("Login successful.");
                displayAfterLogin(studentId);
            } else {
                System.out.println("Invalid username or password");
                displayLoginAndRegistration("Note : For new students registration is required");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void displayAfterLogin(int studentId) {
        int score;
        if ((score = checkQuizAttmptd(studentId)) != -1) {
            System.out.println("You have already attempted the quiz with score : " + score);
        } else if (AdminOperations.getCountOfQuestionsAdded() == 10) {
            System.out.println("Would you like to start the quiz ? (Y/N)");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("Y")) {
                quizDisplay(studentId);
            } else {
                AppDisplay appDisplay = new AppDisplay();
                appDisplay.appDisplay("Login as : ");
            }
        } else {
            System.out.println("All 10 questions are not ready for quiz .\nPlease wait  till admin adds those questions ");
        }
    }

    private void quizDisplay(int studentId) {
        List<Question> mcqs = StudentOperations.getMCQsFromDatabase();
        int score = 0;
        int i = 1;
        for (Question mcq : mcqs) {
            System.out.println("Question " + i + " : " + mcq.getQuery());
            System.out.println("1. " + mcq.getOptions().get(0));
            System.out.println("2. " + mcq.getOptions().get(1));
            System.out.println("3. " + mcq.getOptions().get(2));
            System.out.println("4. " + mcq.getOptions().get(3));
            System.out.print("Your answer: ");
            int answer = scanner.nextInt();
            scanner.nextLine();
            if (answer == mcq.getAnswerkey()) {
                score++;
            }
            i++;
        }

        System.out.println("Your score is : " + score + " Out of " + mcqs.size());
        StudentOperations.storeQuizResult(studentId, score);
    }
}
