package org.example;

import java.util.Scanner;

public class Main {

    protected static final String localDirectory = "src/main/java/org/example/dir/infotecs.json";
    protected static final String remoteFilePath = "infotecs.json";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Авторизация\n");

        System.out.print("Введите адрес хоста: ");
        String host = scanner.nextLine();
/**
 (while (!JSONReader.isValidIp(host)) {
    System.out.print("Заново введите имя хоста: ");
    host = scanner.nextLine();
 }
 можно оставить эту проверку, но тогда нельзя будет вводить имя хоста (например lohov.net), а только адрес
 */

        System.out.print("\nВведите имя пользователя: ");
        String user = scanner.nextLine();

        System.out.print("\nВведите пароль: ");
        String password = scanner.nextLine();

        System.out.print("\nВведите порт: ");
        int port = scanner.nextInt();

        Menu.clearConsole();

 /**
  * Данные ниже можно использовать для тестирования
  * @host 185.58.207.63
  * @port 22
  * @user jora
  * @password password
  * @remoteFilePath "/home/jora/infotecs.json"
*/

        if (SFTPClient.downloadFileSFTP(host, port, user, password) == -1) {
            System.out.println("Ваши введенные данные\n" +
                    "Адрес хоста: " + host +
                    "\nПорт: " + port +
                    "\nИмя мользователя: " + user +
                    "\nПароль: " + password);
            return;
        }

        JSONReader.readJSON();

        while (true) {
            Menu.displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Menu.clearConsole();
                    JSONReader.showDomainIpList();
                    break;

                case 2:
                    Menu.clearConsole();
                    System.out.print("Введите домен: ");
                    String domain = scanner.nextLine();
                    System.out.println("IP адрес для " + domain + " " + JSONReader.getIpByDomain(domain));
                    break;

                case 3:
                    Menu.clearConsole();
                    System.out.print("Введите IP: ");
                    String ip = scanner.nextLine();
                    JSONReader.getDomainByIp(ip);
                    break;

                case 4:
                    Menu.clearConsole();
                    System.out.print("Введите доменное имя: ");
                    String newDomain = scanner.nextLine();
                    System.out.print("Введите IP-адрес: ");
                    String newIp = scanner.nextLine();
                    JSONReader.addDomainIp(newDomain, newIp);
                    break;

                case 5:
                    Menu.clearConsole();
                    System.out.print("Введите домен или IP для удаления: ");
                    String toDelete = scanner.nextLine();
                    JSONReader.removeDomainIp(toDelete);
                    break;

                case 6:
                    Menu.clearConsole();
                    System.out.println("Загрузка отредактированного файла на хост...");
                    SFTPClient.uploadFileSFTP(host, port, user, password);
                    return;
                default:
                    System.out.println("Такого пункта меню нет. Попробуйте снова.");
            }
        }
    }
}