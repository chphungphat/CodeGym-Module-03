package dao;

import com.sun.org.apache.bcel.internal.generic.PUSH;
import model.user.Address;
import model.user.User;
import model.user.User_Info;
import org.mindrot.jbcrypt.BCrypt;
import service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDAO {
    private static final UserDAO userDAO = new UserDAO();

    private UserDAO() {}

    public static UserDAO getInstance() {
        return userDAO;
    }

    private final String SELECT_USER_TO_LOGIN = "SELECT * " +
                                                "FROM User " +
                                                "WHERE user_name = ? OR email = ? OR phone = ?;";
    private final String SELECT_ALL_USERS = "SELECT * FROM User;";
    private final String INSERT_USER = "INSERT INTO User (user_name, phone, email, password, created_date, position_id) " +
                                            "VALUES (?, ?, ?, ?, ?, ?);";
    private final String UPDATE_USER = "UPDATE User SET user_name = ?, email = ?, phone = ?, password = ?;";


    private final int HASH_ROUNDS = 5;
    private final int ADMIN = 1;
    private final int CUSTOMER = 2;

    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                if (!rs.getBoolean("isDeleted")) {
                    int user_id = rs.getInt("user_id");
                    String user_name = rs.getString("user_name");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    Date created_date = rs.getDate("created_date");
                    User newUSer = User.builder()
                            .user_id(user_id)
                            .user_name(user_name)
                            .password(password)
                            .phone(phone)
                            .email(email)
                            .created_date(created_date)
                            .build();
                    users.add(newUSer);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return users;
    }

//    public boolean updateUser(User user) {
//        boolean rowUpdated = false;
//
//        try (Connection connection = Connector.getInstance().getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
//
//            preparedStatement.setString(1, user.getUser_name());
//        }
//    }

    public void insertUser(User user) {
        try (Connection connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {

            preparedStatement.setString(1, user.getUser_name());
            preparedStatement.setString(2, user.getPhone());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(HASH_ROUNDS)));
            preparedStatement.setDate(5, new java.sql.Date(user.getCreated_date().getTime()));
            preparedStatement.setInt(6, user.getPosition_id());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public User checkLogin(String loginString, String loginPass) {
        User user = null;
        try (Connection connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_TO_LOGIN)) {

            preparedStatement.setString(1, loginString);
            preparedStatement.setString(2, loginString);
            preparedStatement.setString(3, loginString);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String passDB = rs.getString("password");
                if ((BCrypt.checkpw(loginPass, passDB)) && (!rs.getBoolean("isDeleted"))) {
                    user = User.builder()
                            .user_id(rs.getInt("user_id"))
                            .user_name(rs.getString("user_name"))
                            .phone(rs.getString("phone"))
                            .email(rs.getString("email"))
                            .password(rs.getString("password"))
                            .created_date(rs.getDate("created_date"))
                            .position_id(rs.getInt("position_id"))
                            .build();
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return user;
    }



    public static void main(String[] args) {

    }
}
