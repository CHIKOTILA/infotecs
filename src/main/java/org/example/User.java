package org.example;

public class User {
    private static String host;
    private static String username;
    private static String password;
    private static int port = 0;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        User.password = password;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        User.username = username;
    }

    public static String getHost() {
        return host;
    }

    public void setHost(String host) {
        User.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        User.port = port;
    }

    public static void printUserInfo() {
        System.out.println("Ваши введенные данные\n" +
                "Адрес хоста: " + host +
                "\nПорт: " + port +
                "\nИмя мользователя: " + username +
                "\nПароль: " + password + "\n");
    }
}
