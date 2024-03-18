package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS users"
                        + "("
                        + " id int auto_increment primary key,"
                        + " name varchar(45) not null,"
                        + " last_name varchar(45) not null,"
                        + " age tinyint null"
                        + ")";

        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute(query);
        } catch (SQLException ignore) { }
    }

    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS users";

        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute(query);
        } catch (SQLException ignore) { }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users (name, last_name, age) VALUES(?,?,?)";

        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
        } catch (SQLException ignore) { }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM users WHERE id=?";

        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ignore) { }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = new User(resultSet.getString("name")
                        , resultSet.getString("last_name")
                        , resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                userList.add(user);
            }
        } catch (SQLException ignore) { }

        return userList;
    }

    public void cleanUsersTable() {
        //String query = "DELETE FROM users";
        String query = "TRUNCATE TABLE users";

        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute(query);
        } catch (SQLException ignore) { }
    }
}
