package overridetech.jdbc.jpa;

import overridetech.jdbc.jpa.model.User;
import overridetech.jdbc.jpa.service.UserService;
import overridetech.jdbc.jpa.service.UserServiceImpl;
import overridetech.jdbc.jpa.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String DRIVER = "org.postgresql.Driver";
        String DB_URL = "jdbc:postgresql://127.0.0.1:5432/test";
        String USER = "postgres";
        String PASS = "postgres";
        Util.setConfig(DRIVER, DB_URL, USER, PASS);

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("serg","chs", (byte) 24);
        System.out.println("User с именем – \"serg\" добавлен в базу данных");
        userService.saveUser("serg2","chs", (byte) 2);
        System.out.println("User с именем – \"serg2\" добавлен в базу данных");
        userService.saveUser("serg3","chs", (byte) 15);
        System.out.println("User с именем – \"serg3\" добавлен в базу данных");
        userService.saveUser("serg4","chs", (byte) 99);
        System.out.println("User с именем – \"serg4\" добавлен в базу данных");

        List<User> users = userService.getAllUsers();
        for (User user:users){
            System.out.println(user);
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();
        userService.dropUsersTable();
        userService.dropUsersTable();
    }
}
