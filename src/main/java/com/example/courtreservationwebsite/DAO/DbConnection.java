package com.example.courtreservationwebsite.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    private String jdbcURL = "jdbc:mysql://localhost/court-management";
    private String jdbcUsername = "root";
    private String jdbcPassword = "vietanh2409";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
