package com.example.servlet.loginsystem02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnDB {

    Connection conn = null;

    public Connection getConn(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb?useSSL=false","root","root");
//            conn = DriverManager.getConnection("jdbc:mysql://192.168.1.6:3306/test_db","root","root");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
