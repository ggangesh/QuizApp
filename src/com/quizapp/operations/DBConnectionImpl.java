package com.quizapp.operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionImpl implements DBConnection {
    private Connection connection = null;

    @Override
    public Connection getConnection() {
        Properties prop = PropHandler.getApplicationProperties();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(prop.getProperty("db.url") + "/" + prop.getProperty("db.schema","quizdb"),
                    prop.getProperty("db.username", "root"),
                    prop.getProperty("db.password", "root"));
        } catch (ClassNotFoundException e) {
            System.out.println("Driver for mysql not found.Properly load mysql connector jar");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database Connection error");
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
