package service;

import connection.JdbcConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class UserService {
    private List<User> users = null;

    public List<User> getAll() {
        users = new LinkedList<>();

        try {
            Connection connection = JdbcConnection.getConnection();
            String query = "SELECT u.id, u.fullname, u.username, " +
                    "u.password, u.email, u.address " +
                    "FROM user u ;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setFullname(resultSet.getString("fullname"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
                users.add(user);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}
