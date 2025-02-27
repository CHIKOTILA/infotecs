package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SFTPClientTest {

    private final String validHost = "185.58.207.63";
    private final int validPort = 22;
    private final String validUser = "jora";
    private final String validPassword = "password";

    @Test
    public void testSuccessfulDownload() {
        int result = SFTPClient.downloadFileSFTP(validHost, validPort, validUser, validPassword);
        Assert.assertEquals(result, 1, "Файл должен скачаться успешно.");
    }

    @Test
    public void testFailedDownloadInvalidCredentials() {
        int result = SFTPClient.downloadFileSFTP(validHost, validPort, "Kostik", "pass");
        Assert.assertEquals(result, -1, "Ожидался отказ в подключении.");
    }

    @Test
    public void testFailedDownloadInvalidHost() {
        int result = SFTPClient.downloadFileSFTP("host", validPort, validUser, validPassword);
        Assert.assertEquals(result, -1, "Ожидался отказ в подключении.");
    }

    @Test
    public void testUploadFile() {
        SFTPClient.uploadFileSFTP(validHost, validPort, validUser, validPassword);
        Assert.assertTrue(true, "Файл должен загрузиться без исключений.");
    }
}