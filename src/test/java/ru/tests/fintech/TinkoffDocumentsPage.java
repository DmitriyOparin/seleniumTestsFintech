package ru.tests.fintech;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class TinkoffDocumentsPage extends Page {

    public Logger logger = LoggerFactory.getLogger(TinkoffDocumentsPage.class);

    public TinkoffDocumentsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openPage() {
        driver.get("https://www.tinkoff.ru/mobile-operator/documents/");
        logger.info("Открыт сайт https://www.tinkoff.ru/mobile-operator/documents/");
    }

    public void downloadAndControlFile(String nameFileInPage) {
        String separator = File.separator;
        String commonPath = "src" + separator + "test" + separator + "resources" + separator;

        WebElement linkFIle = driver.findElement(By.xpath("//a[contains(text(),'" + nameFileInPage + "')]"));
        String nameFileInDisk = linkFIle.getAttribute("href");
        nameFileInDisk = nameFileInDisk.substring(nameFileInDisk.lastIndexOf("/") + 1, nameFileInDisk.length());
        String halfPathFileInDisk = commonPath + nameFileInDisk;

        File file = new File(halfPathFileInDisk);
        file.delete();

        linkFIle.click();

        File newFile = new File(halfPathFileInDisk);

        String fullpath;
        for (int i = 0; i < 5; i++) {
            if (newFile.exists()) {
                try {
                    fullpath = new File(halfPathFileInDisk).getCanonicalPath();
                    System.out.println("Файл загружен. Путь: " + fullpath);
                    logger.info("Файл загружен");
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    logger.error("Файл не скачен");
                    e.printStackTrace();
                }
            }
        }

        Assert.assertTrue(newFile.exists());
        logger.error("Файл отсутствует");
    }
}
