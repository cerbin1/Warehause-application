package warehouse;

import java.sql.*;

public class UserDao {

    public boolean isUserInDatabase(String login, String password) {
        Connection connection = DatabaseHelper.getConnection();
        if (connection == null) {
            throw new DatabaseConnectionException("Error while connecting to database");
        }
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
