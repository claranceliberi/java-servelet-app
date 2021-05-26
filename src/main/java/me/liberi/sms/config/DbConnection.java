package me.liberi.sms.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static Connection getConnection() throws SQLException {
        Connection connection;
        String dbUrl = "jdbc:mysql://127.0.0.1/servelet";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        connection = DriverManager.getConnection(dbUrl, "liberi", "liberi");
        if (connection == null) {
            System.out.println("Failed to connect to database ");
            System.exit(0);
        }

        return connection;
    }
}
