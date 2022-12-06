package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "create table if not exists User (id bigint primary key auto_increment, name varchar(50), lastname varchar(50), age tinyint);";
            statement.execute(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "drop table if exists User;";
            statement.execute(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into user (name, lastname, age) values (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from user where id = ?");
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "select * from user";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                User transientUser = new User();
                transientUser.setId(resultSet.getLong("id"));
                transientUser.setName(resultSet.getString("name"));
                transientUser.setLastName(resultSet.getString("lastname"));
                transientUser.setAge(resultSet.getByte("age"));
                users.add(transientUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "delete from user";
            statement.execute(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
