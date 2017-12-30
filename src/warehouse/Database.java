package warehouse;

import java.sql.*;

public class Database {
    private static final String url = "jdbc:mysql://localhost:3306/warehouse?useSSL=false";
    private static final String DATABASE_LOGIN = "root";
    private static final String DATABASE_PASSWORD = "root";

    public static boolean isUserInDatabase(String login, String password) {
        try {
            Connection connection = DriverManager.getConnection(url, DATABASE_LOGIN, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Password FROM users WHERE Login=?");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getString("Password").equals(password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
