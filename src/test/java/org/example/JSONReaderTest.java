package org.example;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JSONReaderTest {

    @BeforeClass
    public void setUp() {
        JSONReader.readJSON();
    }

    @Test (priority = 0)
    public void testAddValidDomainIp() {
        JSONReader.addDomainIp("test.com", "1.1.1.100");
        Assert.assertEquals(JSONReader.getIpByDomain("test.com"), "1.1.1.100", "Добавлен тестовый IP");
    }

    @Test (priority = 1)
    public void testAddDuplicateDomain() {
        JSONReader.addDomainIp("test.com", "1.1.1.101");
        Assert.assertNotEquals(JSONReader.getIpByDomain("test.com"), "1.1.1.101", "Не должен был добавляться дубликат.");
    }

    @Test (priority = 2)
    public void testFindIpByDomain() {
        String ip = (String) JSONReader.getIpByDomain("test.com");
        Assert.assertEquals(ip, "1.1.1.100", "IP должен совпадать.");
    }

    @Test  (priority = 3)
    public void testFindDomainByIp() {
        String domain = JSONReader.getDomainByIp("1.1.1.100");
        Assert.assertEquals(domain, "test.com", "Домен должен совпадать.");
    }

    @Test  (priority = 4)
    public void testRemoveDomainIp() {
        JSONReader.removeDomainIp("1.1.1.100");
        Assert.assertEquals(JSONReader.getIpByDomain("test.com"),"не найден.","Домен должен быть удалён.");
    }
}