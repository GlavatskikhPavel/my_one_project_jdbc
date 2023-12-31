package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_hibernate";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "qweasdzxc123";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Cannot connect the database!", e);
        }
        return connection;
    }
}
