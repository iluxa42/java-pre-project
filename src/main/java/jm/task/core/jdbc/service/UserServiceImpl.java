package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao UserDaoHibernate = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        UserDaoHibernate.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        UserDaoHibernate.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        UserDaoHibernate.saveUser(name, lastName, age);
        System.out.printf("User с именем — %s добавлен в базу данных\n", name);
    }

    @Override
    public void removeUserById(long id) {
        UserDaoHibernate.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return UserDaoHibernate.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        UserDaoHibernate.cleanUsersTable();
    }
}
