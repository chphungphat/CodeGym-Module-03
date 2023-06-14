package com.cg.module03casestudy.model.dao;

import com.cg.module03casestudy.connection.JdbcConnection;
import com.cg.module03casestudy.model.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class UserDao {

    private List<UserDto> userDtos = null;

    public List<UserDto> getAll() {
        userDtos = new LinkedList<>();

        try {
            Connection connection = JdbcConnection.getConnection();
            String query = "SELECT u.id, u.username, u.email, u.password, u.fullname, " +
                                    "u.address, u.phone, u.avatar, u.activated, r.description " +
                            "FROM users u " +
                            "INNER JOIN roles r ON u.role_id = r.id;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UserDto userDto = new UserDto();
                userDto.setId(resultSet.getInt("id"));
                userDto.setUsername(resultSet.getString("username"));
                userDto.setEmail(resultSet.getString("email"));
                userDto.setPassword(resultSet.getString("password"));
                userDto.setFullname(resultSet.getString("fullname"));
                userDto.setAddress(resultSet.getString("address"));
                userDto.setPhone(resultSet.getString("phone"));
                userDto.setAvatar(resultSet.getString("avatar"));
                userDto.setActivated(Boolean.parseBoolean(resultSet.getString("activated")));
                userDto.setRoleDesc(resultSet.getString("description"));
                userDtos.add(userDto);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userDtos;
    }

    public UserDto get(Integer id) {
        try {
            Connection connection = JdbcConnection.getConnection();
            String query = "SELECT u.id, u.username, u.email, u.password, u.fullname, " +
                                    "u.address, u.phone, u.avatar, u.activated, r.description " +
                            "FROM users u " +
                            "INNER JOIN roles r ON u.role_id = r.id WHERE u.id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                UserDto userDto = new UserDto();
                userDto.setId(resultSet.getInt("id"));
                userDto.setUsername(resultSet.getString("username"));
                userDto.setEmail(resultSet.getString("email"));
                userDto.setPassword(resultSet.getString("password"));
                userDto.setFullname(resultSet.getString("fullname"));
                userDto.setAddress(resultSet.getString("address"));
                userDto.setPhone(resultSet.getString("phone"));
                userDto.setAvatar(resultSet.getString("avatar"));
                userDto.setActivated(Boolean.parseBoolean("activated"));
                userDto.setRoleDesc(resultSet.getString("description"));
                return userDto;
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new UserDto();
    }

    public UserDto get(String email) {
        try {
            Connection connection = JdbcConnection.getConnection();
            String query = "SELECT u.id, u.email, u.password, u.fullname, u.phone, " +
                                    "u.avatar, r.description " +
                            "FROM users u " +
                            "INNER JOIN roles r ON u.role_id = r.id WHERE u.email = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                UserDto userDto = new UserDto();
                userDto.setId(resultSet.getInt("id"));
                userDto.setEmail(resultSet.getString("email"));
                userDto.setPassword(resultSet.getString("password"));
                userDto.setFullname(resultSet.getString("fullname"));
                userDto.setPhone(resultSet.getString("phone"));
                userDto.setAvatar(resultSet.getString("avatar"));
                userDto.setRoleDesc(resultSet.getString("description"));
                return userDto;
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new UserDto();
    }

    public void insert(UserDto userDto) {
        try {
            if(userDto.getId() != null) {
                System.out.println("User is existed.");
                return;
            }

            Connection connection = JdbcConnection.getConnection();
            String query = "INSERT INTO users(email, username, password, fullname, " +
                                             "address, phone, avatar, activated, role_id) " +
                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userDto.getEmail());
            preparedStatement.setString(2, userDto.getUsername());
            preparedStatement.setString(3, userDto.getPassword());
            preparedStatement.setString(4, userDto.getFullname());
            preparedStatement.setString(5, userDto.getAddress());
            preparedStatement.setString(6, userDto.getPhone());
            preparedStatement.setString(7, userDto.getAvatar());
            preparedStatement.setBoolean(8, userDto.getActivated());
            preparedStatement.setInt(9, userDto.getRoleId());

            if(preparedStatement.executeUpdate() > 0) {
                System.out.println("Added user successfully.");
            } else {
                System.out.println("Failed to insert user.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(UserDto userDto) {
        try {
            Connection connection = JdbcConnection.getConnection();
            String query = "UPDATE users " +
                            "SET email = ?, username = ?, password = ?, fullname = ?, " +
                                "address = ?, phone = ?, avatar = ?, activated = ?, role_id = ? " +
                            "WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userDto.getEmail());
            preparedStatement.setString(2, userDto.getUsername());
            preparedStatement.setString(3, userDto.getPassword());
            preparedStatement.setString(4, userDto.getFullname());
            preparedStatement.setString(5, userDto.getAddress());
            preparedStatement.setString(6, userDto.getPhone());
            preparedStatement.setString(7, userDto.getAvatar());
            preparedStatement.setBoolean(8, userDto.getActivated());
            preparedStatement.setInt(9, userDto.getRoleId());
            preparedStatement.setInt(10, userDto.getId());

            if(preparedStatement.executeUpdate() > 0) {
                System.out.println("Edited user successfully.");
            } else {
                System.out.println("Failed to edit user.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        try {
            Connection connection = JdbcConnection.getConnection();
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            if(preparedStatement.executeUpdate() > 0) {
                System.out.println("Removed user successfully.");
            } else {
                System.out.println("Failed to remove user.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

