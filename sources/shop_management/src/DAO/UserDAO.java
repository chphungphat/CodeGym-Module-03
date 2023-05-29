package DAO;

import entity.Address;
import entity.User;
import service.UserService;
import sun.dc.pr.PRError;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class UserDAO implements IUserDAO {
    private static final UserDAO userDAO = new UserDAO();

    private UserDAO() {}

    public static UserDAO getInstance() {
        return userDAO;
    }

    private final String INSERT_USER_SQL = "INSERT INTO User (name, email, phone, password, address_id) VALUES (?, ?, ?, ?, ?);";
    private final String SELECT_USER_BY_ID = "SELECT * FROM User WHERE user_id = ?";
    private final String SELECT_ALL_USERS = "SELECT user_id, name, email, phone " +
                                            "FROM User;";
    private final String SELECT_ADDRESS_IFO = "SELECT * FROM Address WHERE houseNumber = '219' AND street = ? AND ward = ? AND district = ? AND province = ? AND country = ? LIMIT 1;";
    private final String SELECT_USER_BY_EMAIL = "SELECT * FROM User WHERE email = ?;";
    private final String SELECT_ADDRESS_LAST_ADD = "SELECT address_id FROM Address ORDER BY address_id DESC LIMIT 1;";
    private final String DELETE_USER_SQL = "DELETE FROM User WHERE user_id = ?;";
    private final String UPDATE_USER_INFO_SQL = "UPDATE User SET name = ?, phone = ?, email = ? WHERE user_id = ?;";
    private final String UPDATE_USER_PASSWORD_SQL = "UPDATE User SET password = ? WHERE user_id = ?;";

    @Override
    public void insertUser(User user) throws SQLException {
        System.out.println("Trying to insert user " + user.getName());
        int address_id = 0;

        try (Connection connection = ConnectionDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_ADDRESS_LAST_ADD);
            
            while (rs.next()) {
                address_id = rs.getInt("address_id");
            }

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, address_id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            ConnectionDB.getInstance().printSQLException(exception);
        }
    }

    @Override
    public User selectUser(int id) {
        User user = null;
        Address address = null;
        int user_id = 0;
        String name = "";
        String email = "";
        String phone = "";
        String password = "";
        int address_id = 0;

        try (Connection connection = ConnectionDB.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                user_id = rs.getInt("user_id");
                name = rs.getString("name");
                email = rs.getString("email");
                phone = rs.getString("phone");
                password = rs.getString("password");
                address_id = rs.getInt("address_id");
            }
        } catch (SQLException exception) {
            ConnectionDB.getInstance().printSQLException(exception);
        }

        address = AddressDAO.getInstance().selectAddress(address_id);
        user = User.builder()
                .user_id(user_id)
                .name(name)
                .email(email)
                .phone(phone)
                .password(password)
                .address(address)
                .build();
        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        return null;
    }

    @Override
    public boolean loginToUser(String email, String password) {
        boolean isExist = false;
        boolean isCorrect = false;
        boolean check = false;

        User user = null;
        Address address = null;
        int user_id = 0;
        String name = "";
        String phone = "";
        int address_id = 0;

        try(Connection connection = ConnectionDB.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {

            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.next()) {
                System.out.println("User not found");
            } else {
                isExist = true;
                do {
                    String correctPass = rs.getString("password");
                    if (Objects.equals(password, correctPass)) {
                        isCorrect = true;
                        user_id = rs.getInt("user_id");
                        name = rs.getString("name");
                        phone = rs.getString("phone");
                        address_id = rs.getInt("address_id");
                    } else {
                        System.out.println("Password incorrect");
                    }
                } while (rs.next());
            }

            if (isExist && isCorrect) {
                check = true;
                address = AddressDAO.getInstance().selectAddress(address_id);
                user = User.builder()
                        .user_id(user_id)
                        .name(name)
                        .email(email)
                        .phone(phone)
                        .password(password)
                        .address(address)
                        .build();
                UserService.getInstance().setCurrentUser(user);
            }
        } catch (SQLException exception) {
            ConnectionDB.getInstance().printSQLException(exception);
        }
        return check;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateUserInfo(User user) throws SQLException {
        return false;
    }

    @Override
    public boolean updateUserPassword(User user) throws SQLException {
        return false;
    }
}
