package warehouse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    private final static String dbURL = "jdbc:mysql://localhost:3306/warehouse?useSSL=false";
    private final static String user = "root";
    private final static String password = "root";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(dbURL, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
