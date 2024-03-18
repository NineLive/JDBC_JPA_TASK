package overridetech.jdbc.jpa.service;

import overridetech.jdbc.jpa.dao.UserDao;
import overridetech.jdbc.jpa.dao.UserDaoJDBCImpl;
import overridetech.jdbc.jpa.model.User;
import overridetech.jdbc.jpa.util.Util;

import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final Connection connection = Util.getConnection();


    public void createUsersTable() {
        UserDao userDao = new UserDaoJDBCImpl(connection);
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        UserDao userDao = new UserDaoJDBCImpl(connection);
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDao userDao = new UserDaoJDBCImpl(connection);
//        userDao.createUsersTable();
        userDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        UserDao userDao = new UserDaoJDBCImpl(connection);
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        UserDao userDao = new UserDaoJDBCImpl(connection);
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        UserDao userDao = new UserDaoJDBCImpl(connection);
        userDao.cleanUsersTable();
    }
}
