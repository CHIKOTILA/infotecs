package org.example;

public class Main {

    protected static final String localDirectory = "src/main/java/org/example/dir/infotecs.json";
    protected static final String remoteFilePath = "infotecs.json";

    public static void main(String[] args) {
        User user = Authorization.authorization();
        JSONReader.readJSON();
        Menu.menu(user);
    }
}