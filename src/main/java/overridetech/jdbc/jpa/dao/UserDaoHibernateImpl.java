package overridetech.jdbc.jpa.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import overridetech.jdbc.jpa.model.User;
import overridetech.jdbc.jpa.util.Util;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.createSessionFactory();

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        String sql = "CREATE TABLE IF NOT EXISTS users (id BIGSERIAL NOT NULL PRIMARY KEY, name VARCHAR(50), lastName VARCHAR(50), age int2);";
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        String sql = "DROP TABLE IF EXISTS users";
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        List<User> list = criteria.list();
        session.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        getAllUsers().forEach(session::delete);
        session.getTransaction().commit();
        session.close();
    }
}
