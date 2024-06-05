package com.quizapp.operations;

import com.quizapp.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentOperations {

    public static void studentDataStore(String firstname, String lastname, String username, String password, String address,
                                        String email, Long mobileno) {
        Connection connetion = (new DBConnectionImpl()).getConnection();
        PreparedStatement preparedStmt = null;
        try {
            String sqlquary = "insert into students (firstname,lastname,username,password,address,email,mobileno)"
                    + "values(?,?,?,?,?,?,?)";
            preparedStmt = connetion.prepareStatement(sqlquary);
            preparedStmt.setString(1, firstname);
            preparedStmt.setString(2, lastname);
            preparedStmt.setString(3, username);
            preparedStmt.setString(4, password);
            preparedStmt.setString(5, address);
            preparedStmt.setString(6, email);
            preparedStmt.setLong(7, mobileno);
            int i = preparedStmt.executeUpdate();
            System.out.println("Execute done" + i);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStmt.close();
                connetion.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static int loginStudent(String username, String password) {
        int id = -1;
        String selectQuery = "SELECT id FROM students WHERE username = ? AND password = ?";
        Connection connection = (new DBConnectionImpl()).getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                id = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public static int checkQuizAttmptd(int studentId) {
        int score = -1;
        String selectQuery = "SELECT score FROM quizscore WHERE studentid = ?";
        Connection connection = (new DBConnectionImpl()).getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                score = resultSet.getInt("score");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return score;
    }

    public static List<Question> getMCQsFromDatabase() {
        List<Question> mcqs = new ArrayList<>();
        try {
            Connection connection = (new DBConnectionImpl()).getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM questions");
            while (resultSet.next()) {
                List<String> options = new ArrayList<>();
                String question = resultSet.getString("query");
                options.add(resultSet.getString("option1"));
                options.add(resultSet.getString("option2"));
                options.add(resultSet.getString("option3"));
                options.add(resultSet.getString("option4"));
                int answerkey = resultSet.getInt("answerkey");
                Question mcq = new Question(question, options, answerkey);
                mcqs.add(mcq);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mcqs;
    }

    public static void storeQuizResult(int studentId,int score){
        try {
            Connection connection = (new DBConnectionImpl()).getConnection();
            String query = "insert into quizscore (studentid, score) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, score);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
