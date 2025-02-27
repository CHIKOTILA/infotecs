package org.example;

import com.jcraft.jsch.*;

public class SFTPClient {
    public static int downloadFileSFTP(String host, int port, String user, String password) {
        try {
            String localDirectory = Main.localDirectory;
            String remoteFilePath = Main.remoteFilePath;
            System.out.println("Соединение с хостом...");
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect();

            if (session.isConnected()) {
                System.out.println("Соединение установлено");
            }

            try {
                System.out.println("Загрузка фала с сервера...");
                ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
                channelSftp.connect();
                channelSftp.get(remoteFilePath, localDirectory);

                channelSftp.disconnect();
                session.disconnect();
                System.out.println("Файл загружен с сервера");
            } catch (JSchException e) {
                System.out.println("Файл не загружен с сервера");
            }

        } catch (Exception e) {
            System.out.println("Соединение не установлено, проверьте введеные данные или права на загрузку");
            return -1;
        }
        return 1;
    }

    public static void uploadFileSFTP(String host, int port, String user, String password) {
        try {
            String remoteFilePath = Main.remoteFilePath;
            String localDirectory = Main.localDirectory;

            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect();


            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            channelSftp.put(localDirectory, remoteFilePath);

            channelSftp.disconnect();
            session.disconnect();

            System.out.println("Файл загружен на сервер");
        } catch (Exception e) {
            System.out.println("Соединение не установлено, проверьте введеные данные или права на запись");
            e.printStackTrace();
        }
    }
}
