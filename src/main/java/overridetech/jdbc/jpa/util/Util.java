package overridetech.jdbc.jpa.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import overridetech.jdbc.jpa.model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/test";
    private static final String USER = "postgres";
    private static final String PASS = "postgres";
    private static final String DIALECT = "org.hibernate.dialect.PostgreSQL9Dialect";
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "update";

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName(DRIVER).newInstance());
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SessionFactory createSessionFactory() {
        Configuration configuration = getHibernateConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
    public static Configuration getHibernateConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", DIALECT);
        configuration.setProperty("hibernate.connection.driver_class", DRIVER);
        configuration.setProperty("hibernate.connection.url", DB_URL);
        configuration.setProperty("hibernate.connection.username", USER);
        configuration.setProperty("hibernate.connection.password", PASS);
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }
}
