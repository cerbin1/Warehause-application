package warehouse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableDAO {
    public List<Table> getTables() {
        Connection connection = DatabaseHelper.getConnection();

        if (connection == null) {
            throw new DatabaseConnectionException("Error while connecting to database");
        }
        List<Table> tableNames = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();


            ResultSet resultSet = statement.executeQuery("SELECT * FROM tables");

            while (resultSet.next()) {
                tableNames.add(new Table(resultSet.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableNames;
    }

}

class Table {
    private String name;

    public Table(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
