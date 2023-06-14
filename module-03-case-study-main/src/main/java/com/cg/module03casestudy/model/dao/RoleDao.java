package com.cg.module03casestudy.model.dao;

import com.cg.module03casestudy.connection.JdbcConnection;
import com.cg.module03casestudy.model.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class RoleDao {
    private List<Role> roles = null;

    public List<Role> getAll() {
        roles = new LinkedList<>();
        try {
            Connection connection = JdbcConnection.getConnection();
            String query = "SELECT * FROM roles;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setName(resultSet.getString("name"));
                role.setDescription(resultSet.getString("description"));
                roles.add(role);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roles;
    }

    public Role get(int id) {
        try {
            Connection connection = JdbcConnection.getConnection();
            String query = "SELECT * FROM roles WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setName(resultSet.getString("name"));
                role.setDescription(resultSet.getString("description"));
                return role;
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Role();
    }

    public void insert(Role role) {
        try {
            if(role.getId() != null) {
                System.out.println("Role is existed.");
                return;
            }

            Connection connection = JdbcConnection.getConnection();
            String query = "INSERT INTO roles(name, description) VALUES(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, role.getName());
            preparedStatement.setString(2, role.getDescription());

            if(preparedStatement.executeUpdate() > 0) {
                System.out.println("Added role successfully.");
            } else {
                System.out.println("Failed to insert role.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Role role) {
        try {
            Connection connection = JdbcConnection.getConnection();
            String query = "UPDATE roles SET name = ?, description = ? WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, role.getName());
            preparedStatement.setString(2, role.getDescription());
            preparedStatement.setInt(3, role.getId());

            if(preparedStatement.executeUpdate() > 0) {
                System.out.println("Edited role successfully.");
            } else {
                System.out.println("Failed to edit role.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        try {
            Connection connection = JdbcConnection.getConnection();
            String query = "DELETE FROM roles WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            if(preparedStatement.executeUpdate() > 0) {
                System.out.println("Removed role successfully.");
            } else {
                System.out.println("Failed to remove role.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
