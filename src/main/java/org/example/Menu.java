package org.example;

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

    public static void clearConsole() {
        for (int i = 0; i < 150; i++) {
            System.out.println();
        }
    }
}
