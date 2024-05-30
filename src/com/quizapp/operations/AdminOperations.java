package com.quizapp.operations;

import com.quizapp.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminOperations {
    public static int getStudentScoreById(int id) {
        int score = -1;
        String sqlQuery = "select score from quizscore where studentid=?";
        Connection conn = (new DBConnectionImpl()).getConnection();
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(sqlQuery);
            preparedStmt.setInt(1, id);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                score = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return score;
    }

    public static Student getStudentById(int id) {
        Student student = null;
        String sqlQuery = "select * from students where id=?";
        Connection conn = (new DBConnectionImpl()).getConnection();
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(sqlQuery);
            preparedStmt.setInt(1, id);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                student = new Student(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getLong(8));
            }
            preparedStmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }
}
