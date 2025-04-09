package swingApplication;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static final String JDBC_URL_TEMPLATE = "jdbc:mysql://localhost:3306/";
    private static final String USERNAME = "root"; // Change as per your database configuration
    private static final String PASSWORD = "0000"; // Change as per your database configuration

    // Method to get database connection
    public static Connection getConnection(String databaseName) throws Exception {
        String jdbcUrl = JDBC_URL_TEMPLATE + databaseName;
        return DriverManager.getConnection(jdbcUrl, USERNAME, PASSWORD);
    }
}
