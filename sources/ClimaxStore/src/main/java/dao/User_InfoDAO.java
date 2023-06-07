package dao;

import model.user.User;
import model.user.User_Info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class User_InfoDAO {
    private static final User_InfoDAO user_InfoDAO = new User_InfoDAO();

    private User_InfoDAO() {}

    public static User_InfoDAO getInstance() {
        return user_InfoDAO;
    }

    private final String SELECT_USER_INFO = "SELECT * FROM User_Info WHERE user_id = ?;";

    public User_Info getUserInfo(User user) {
        User_Info newUser_info = null;
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_INFO)) {

            preparedStatement.setInt(1, user.getUser_id());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int userInfo_id = rs.getInt("userInfo_id");
                int user_id = rs.getInt("user_id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Date birthday = rs.getDate("birthday");
                String gender = rs.getString("gender");

                newUser_info = User_Info.builder()
                                        .userInfo_id(userInfo_id)
                                        .user_id(user_id)
                                        .firstName(firstName)
                                        .lastName(lastName)
                                        .birthday(birthday)
                                        .gender(gender)
                                        .build();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return newUser_info;
    }
}
