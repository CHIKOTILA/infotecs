package org.example;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class JSONReader {
    private static Map<String, String> domainIpMap = new TreeMap<>();

    public static void readJSON() {
        try (BufferedReader reader = new BufferedReader(new FileReader(Main.localDirectory))) {
            String line;
            StringBuilder jsonContent = new StringBuilder();
            boolean startParsing = false;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.contains("\"addresses\"")) {
                    startParsing = true;
                    continue;
                }


                if (startParsing) {
                    jsonContent.append(line);
                }
            }

            String jsonString = jsonContent.toString();

            jsonString = jsonString.replaceAll("[\\[\\]{}\"]", "").trim();

            String[] entries = jsonString.split(",");

            String domain = null, ip = null;

            for (String entry : entries) {
                String[] keyValue = entry.split(":");

                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();

                    if (key.equals("domain")) {
                        domain = value;
                    } else if (key.equals("ip")) {
                        ip = value;
                    }

                    if (domain != null && ip != null) {
                        domainIpMap.put(domain, ip);
                        domain = null;
                        ip = null;
                    }
                }
            }


        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public static void writeJSON(Map<String, String> domainIpMap) {
        String fileName = Main.localDirectory;
        StringBuilder jsonContent = new StringBuilder();
        jsonContent.append("{ \"addresses\": [\n");

        for (Map.Entry<String, String> entry : domainIpMap.entrySet()) {
            jsonContent.append("  {\"domain\": \"").append(entry.getKey()).append("\", ")
                    .append("\"ip\": \"").append(entry.getValue()).append("\"},\n");
        }

        if (jsonContent.length() > 0) {
            jsonContent.deleteCharAt(jsonContent.length() - 2);
        }
        jsonContent.append("\n]}");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(jsonContent.toString());
            System.out.println("Перезаписан: " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка записи в: " + e.getMessage());
        }
    }

    public static void showDomainIpList() {
        if (domainIpMap.isEmpty()) {
            System.out.println("Список доменов пуст.\n т.к поступил пустой json или в нем отсутствует списаок addresses");
        } else {
            for (Map.Entry<String, String> entry : domainIpMap.entrySet()) {
                System.out.println("Domain: " + entry.getKey() + " - IP: " + entry.getValue());
            }
        }
    }

    public static Object getIpByDomain(String domain) {
        if (domainIpMap.containsKey(domain)) {
            return domainIpMap.get(domain);
        }
        return "не найден.";
    }

    public static String getDomainByIp(String ip) {
        boolean found = false;
        for (Map.Entry<String, String> entry : domainIpMap.entrySet()) {
            if (entry.getValue().equals(ip)) {
                System.out.println("Домен для " + ip + ": " + entry.getKey());
                return entry.getKey();
            }
        }
        if (!found) {
            System.out.println("IP-адрес не найден.");
        }
        return "IP-адрес не найден.";
    }

    public static void addDomainIp(String domain, String ip) {
        if (!isValidIp(ip)) {
            System.out.println("Некорректный IP-адрес.");
        } else if (domainIpMap.containsKey(domain)) {
            System.out.println("Домен уже существует с IP: " + domainIpMap.get(domain));
        } else if (domainIpMap.containsValue(ip)) {
            System.out.println("Этот IP уже привязан к другому домену.");
        } else {
            domainIpMap.put(domain, ip);
            JSONReader.writeJSON(domainIpMap);
            System.out.println("Новая пара добавлена.");
        }
    }

    public static void removeDomainIp(String identifier) {
        if (domainIpMap.containsKey(identifier)) {
            domainIpMap.remove(identifier);
            JSONReader.writeJSON(domainIpMap);
            System.out.println("Пара удалена.");
        } else {
            boolean removed = false;
            Iterator<Map.Entry<String, String>> iterator = domainIpMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                if (entry.getValue().equals(identifier)) {
                    iterator.remove();
                    removed = true;
                    JSONReader.writeJSON(domainIpMap);
                    System.out.println("Пара удалена.");
                    break;
                }
            }
            if (!removed) {
                System.out.println("Домен или IP-адрес не найден.");
            }
        }
    }

    public static boolean isValidIp(String ip) {
        String regex = "^([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})$";
        if (!ip.matches(regex)) {
            return false;
        }

        String[] parts = ip.split("\\.");
        for (String part : parts) {
            int num = Integer.parseInt(part);
            if (num < 0 || num > 255) {
                return false;
            }
        }
        return true;
    }
}
