package com.quizapp.operations;

import java.sql.Connection;

public interface DBConnection {
    Connection getConnection();
}
