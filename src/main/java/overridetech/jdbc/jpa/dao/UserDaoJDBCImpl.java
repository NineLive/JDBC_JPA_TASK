package overridetech.jdbc.jpa.dao;

import overridetech.jdbc.jpa.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;

    public UserDaoJDBCImpl() {
    }

    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    public void createUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS users (id BIGSERIAL NOT NULL PRIMARY KEY, name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, age integer NOT NULL);");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS users;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("INSERT INTO users(name, lastName,age) VALUES('" + name + "', '" + lastName + "', " + age + ");");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM users WHERE id = " + id + ";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            stmt.execute("SELECT * FROM users");
            ResultSet result = stmt.getResultSet();
            List<User> users = new ArrayList<>();
            if (!result.first()) {
                return users;
            } else {
                users.add(new User(result.getString(2), result.getString(3), result.getByte(4)));
            }

            while (result.next()) {
                users.add(new User(result.getString(2), result.getString(3), result.getByte(4)));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM users;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
