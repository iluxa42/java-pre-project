package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Alex", "Sokolov", (byte) 25);
        userService.saveUser("Petr", "Lebedev", (byte) 33);
        userService.saveUser("Viktor", "Sidorov", (byte) 53);
        userService.saveUser("Ivan", "Ivanov", (byte) 16);

        //userDao.removeUserById(1L);

        List<User> userList = userService.getAllUsers();
        userList.forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
