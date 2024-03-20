package overridetech.jdbc.jpa;

import overridetech.jdbc.jpa.model.User;
import overridetech.jdbc.jpa.service.UserService;
import overridetech.jdbc.jpa.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.dropUsersTable();
        userService.createUsersTable();
        System.out.println("TABLICA SOZDANA");

        userService.saveUser("serg","chs", (byte) 24);
        System.out.println("User с именем – \"serg\" добавлен в базу данных");
        userService.saveUser("serg2","chs", (byte) 2);
        System.out.println("User с именем – \"serg2\" добавлен в базу данных");
        userService.saveUser("serg3","chs", (byte) 15);
        System.out.println("User с именем – \"serg3\" добавлен в базу данных");
        userService.saveUser("serg4","chs", (byte) 99);
        System.out.println("User с именем – \"serg4\" добавлен в базу данных");

        List<User> users = userService.getAllUsers();
        users.forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();



    }



}
