package warehouse;

import java.sql.*;

public class UserDao {
    private Connection connection;

    public UserDao() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/warehouse?useSSL=false";
        String user = "root";
        String password = "root";

        connection = DriverManager.getConnection(dbURL, user, password);
    }

    public boolean isUserInDatabase(String login, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE Login=? AND Password =?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);


            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
