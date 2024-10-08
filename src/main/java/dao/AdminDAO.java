package dao;

import model.Admin;
import java.sql.*;

public class AdminDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/cooking_recipes?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    public AdminDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean validate(String username, String password) {
        boolean status = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("select * from admins where username = ? and password = ?")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public boolean validateUser(String username, String password) {
        boolean status = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("select * from users where username = ? and password = ?")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}