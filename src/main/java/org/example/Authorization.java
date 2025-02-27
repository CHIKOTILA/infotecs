package org.example;

import java.util.Scanner;

public class Authorization {
    public static User authorization() {
        User user = new User();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Авторизация\n");
        boolean exitFlag = false;
        System.out.println("Подключиться к тестовому серверу - 1\nВвести свои данные - 2");
        if (scanner.nextInt() == 1) {
            scanner.nextLine();
            System.out.print("\nВведите адрес хоста: ");
            user.setHost("185.58.207.63");

            System.out.print("\nВведите имя пользователя: ");
            user.setUsername("jora");

            System.out.print("\nВведите пароль: ");
            user.setPassword("password");

            System.out.print("\nВведите порт: ");
            user.setPort(22);

            SFTPClient.downloadFileSFTP(user.getHost(), user.getPort(), user.getUsername(), user.getPassword());
        } else {
            do {
                scanner.nextLine();
                if (exitFlag) {
                    System.out.println("Завершить программу? 1-Да 2-Нет");
                    if (scanner.nextInt() == 1) return user;
                    scanner.nextLine();
                }

                exitFlag = true;

                System.out.print("\nВведите адрес хоста: ");
                user.setHost(scanner.nextLine());

                System.out.print("\nВведите имя пользователя: ");
                user.setUsername(scanner.nextLine());

                System.out.print("\nВведите пароль: ");
                user.setPassword(scanner.nextLine());

                System.out.print("\nВведите порт: ");
                user.setPort(scanner.nextInt());

                Menu.clearConsole();
                User.printUserInfo();

            } while (SFTPClient.downloadFileSFTP(user.getHost(), user.getPort(), user.getUsername(), user.getPassword()) == -1);
        }
        return user;
    }
}
