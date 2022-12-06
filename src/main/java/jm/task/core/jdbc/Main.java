package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Connection connection = Util.getConnection();

    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        List<User> users = new ArrayList<>();
        User user1 = new User("Mikhail", "Shatov", (byte) 21);
        User user2 = new User("Max", "Tret", (byte) 19);
        User user3 = new User("Nikita", "Sobin", (byte) 30);
        User user4 = new User("Demid", "Fet", (byte) 15);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        userDaoJDBC.createUsersTable(); //Создание табл

        for (User user: users) { //Сохранение юзеров в табл
            userDaoJDBC.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.println(user);
        }

        System.out.println(userDaoJDBC.getAllUsers());

        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();



    }
}
