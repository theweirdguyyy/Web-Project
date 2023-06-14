package cse.web;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database{
    private static final String URL = "jdbc:mysql://localhost:3306/sys";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "28550093";

    public static Connection getConnection() throws SQLException {
    	//Provide Database Connection
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            //e.printStackTrace();
        }
        return connection;
    }
}

