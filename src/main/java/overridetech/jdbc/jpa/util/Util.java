package overridetech.jdbc.jpa.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static String DRIVER = "org.postgresql.Driver";
    private static String DB_URL = "jdbc:postgresql://127.0.0.1:5432/test";
    private static String USER = "postgres";
    private static String PASS = "postgres";

    public static void setConfig(String DRIVER, String DB_URL, String USER, String PASS) {
        Util.DRIVER = DRIVER;
        Util.DB_URL = DB_URL;
        Util.USER = USER;
        Util.PASS = PASS;
    }

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName(DRIVER).newInstance());
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
