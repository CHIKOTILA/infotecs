package org.example;

import java.io.File;
import java.util.Scanner;

public class Menu {
    public static void displayMenu() {
        System.out.println("\nМеню:");
        System.out.println("1. Получение списка пар 'домен – адрес' из файла");
        System.out.println("2. Получение IP-адреса по доменному имени");
        System.out.println("3. Получение доменного имени по IP-адресу");
        System.out.println("4. Добавление новой пары 'домен – адрес' в файл");
        System.out.println("5. Удаление пары 'домен – адрес' по доменному имени или IP-адресу");
        System.out.println("6. Завершение работы");
        System.out.print("Выберите пункт меню: ");
    }

    public static void menu(User user) {
        Scanner scanner = new Scanner(System.in);
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
                    SFTPClient.uploadFileSFTP(user.getHost(), user.getPort(), user.getUsername(), user.getPassword());
                    File file = new File(Main.localDirectory);

                    // Проверяем, существует ли файл
                    if (file.exists()) {
                        // Пытаемся удалить файл
                        boolean deleted = file.delete();

                        if (deleted) {
                            System.out.println("Локальный файл успешно удален.");
                        } else {
                            System.out.println("Не удалось удалить файл.");
                        }
                    } else {
                        System.out.println("Файл не существует.");
                    }
                    return;
                default:
                    System.out.println("Такого пункта меню нет. Попробуйте снова.");
            }
        }
    }

    public static void clearConsole() {
        for (int i = 0; i < 150; i++) {
            System.out.println();
        }
    }
}
