package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        User user1 = new User("Mikhail", "Shatov", (byte) 21);
        User user2 = new User("Max", "Tret", (byte) 19);
        User user3 = new User("Nikita", "Sobin", (byte) 30);
        User user4 = new User("Demid", "Fet", (byte) 15);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        userService.createUsersTable();
        users.forEach(user -> userService.saveUser(user.getName(), user.getLastName(), user.getAge()));
        userService.removeUserById(2);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.closeFactory();
    }
}
