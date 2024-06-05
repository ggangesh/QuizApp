package com.quizapp.operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ScoreasASEOrder {

    public static void displayAllStudentsScoreInAscOrder() {
        List<StudentScore> students = new ArrayList<>();
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            connection = getConnection();
            preparedStatement = createPreparedStatement(connection);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                int score = resultSet.getInt("score");

                students.add(new StudentScore(firstName, lastName, score));
            }

            printStudentsScores(students);
            connection.close();
            preparedStatement.close();
            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/quizdb", "root", "root");
    }

    private static PreparedStatement createPreparedStatement(Connection connection) throws Exception {
        String query = "SELECT firstname, lastname, score FROM students "
                + "INNER JOIN quizscore ON students.id = quizscore.studentid ORDER BY score ASC";
        return connection.prepareStatement(query);
    }

    private static void printStudentsScores(List<StudentScore> students) {
        System.out.println("Students Name                  Score");
        System.out.println("---------------------------------------");

        for (StudentScore student : students) {
            printStudentScore(student);
        }
    }

    private static void printStudentScore(StudentScore student) {
        String fullName = student.getFirstName() + " " + student.getLastName();
        int score = student.getScore();

        System.out.printf("%-30s - %d%n", fullName, score);
    }
}
