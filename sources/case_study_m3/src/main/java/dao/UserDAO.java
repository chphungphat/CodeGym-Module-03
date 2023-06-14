package dao;

import Connector.Connector;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class UserDAO {
    private static final UserDAO userDAO = new UserDAO();

    private UserDAO() {}

    public static UserDAO getInstance() {
        return userDAO;
    }

    private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM User WHERE user_name = ?;";
    private static final String SELECT_ALL_USERS = "SELECT * FROM User;";

//    private static final String INSERT_USERS_SQL = "INSERT INTO Users (user_name, password, email, phone, postition) VALUES (?, ?, ?, ?, ?);";
//    private static final String SELECT_USER_BY_ID = "SELECT id,name,email,country FROM users WHERE id =?";
//    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
//    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE id = ?;";
//    private static final String UPDATE_USERS_SQL = "UPDATE users SET name = ?,email= ?, country =? WHERE id = ?;";


    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String user_name = rs.getString("user_name");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phone  = rs.getString("phone");
                Date birthDay = rs.getDate("birthDay");
                String position = rs.getString("position");
                Date created_date = rs.getDate("created_date");
                boolean isDeleted = rs.getBoolean("isDeleted");
                if (!isDeleted) {
                    User newUser = User.builder()
                            .user_id(user_id)
                            .user_name(user_name)
                            .password(password)
                            .email(email)
                            .phone(phone)
                            .position(position)
                            .birthDay(birthDay)
                            .created_date(created_date)
                            .build();
                    users.add(newUser);
                }
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return users;
    }



//    public void insertUser(User user) {
//        // try-with-resource statement will auto close the connection.
//        try (java.sql.Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
//            preparedStatement.setString(1, user.getUser_name());
//            preparedStatement.setString(2, user.getPassword());
//            System.out.println(preparedStatement);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//    }

//    public User selectUser(int id) {
//        User user = null;
//        // Step 1: Establishing a Connection
//        try (java.sql.Connection connection = getConnection();
//             // Step 2:Create a statement using connection object
//             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
//            preparedStatement.setInt(1, id);
//            System.out.println(preparedStatement);
//            // Step 3: Execute the query or update query
//            ResultSet rs = preparedStatement.executeQuery();
//
//            // Step 4: Process the ResultSet object.
//            while (rs.next()) {
//                String name = rs.getString("name");
//                String email = rs.getString("email");
//                String country = rs.getString("country");
//                user = new User(id, name, email, country);
//            }
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//        return user;
//    }



//    public List<User> selectAllUsers() {
//        List<User> users = new ArrayList<>();
//        try (java.sql.Connection connection = getConnection();
//
//             // Step 2:Create a statement using connection object
//             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
//            System.out.println(preparedStatement);
//            // Step 3: Execute the query or update query
//            ResultSet rs = preparedStatement.executeQuery();
//
//            // Step 4: Process the ResultSet object.
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                String email = rs.getString("email");
//                String country = rs.getString("country");
//                users.add(new User(id, name, email, country));
//            }
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//        return users;
//    }

//    public boolean deleteUser(int id) throws SQLException {
//        boolean rowDeleted;
//        try (java.sql.Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
//            statement.setInt(1, id);
//            rowDeleted = statement.executeUpdate() > 0;
//        }
//        return rowDeleted;
//    }
//
//    public boolean updateUser(User user) throws SQLException {
//        boolean rowUpdated;
//        try (java.sql.Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
//            statement.setString(1, user.getName());
//            statement.setString(2, user.getEmail());
//            statement.setString(3, user.getCountry());
//            statement.setInt(4, user.getId());
//
//            rowUpdated = statement.executeUpdate() > 0;
//        }
//        return rowUpdated;
//    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
