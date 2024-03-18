package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Alex", "Sokolov", (byte) 25);
        userDao.saveUser("Petr", "Lebedev", (byte) 33);
        userDao.saveUser("Viktor", "Sidorov", (byte) 53);
        userDao.saveUser("Ivan", "Ivanov", (byte) 16);

        //userDao.removeUserById(1L);

        List<User> userList = userDao.getAllUsers();
        userList.forEach(System.out::println);

        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
