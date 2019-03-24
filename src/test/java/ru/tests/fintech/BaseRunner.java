package ru.tests.fintech;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BaseRunner {
    WebDriver driver;
    public String browserName = System.getProperty("browser");
    String baseUrl;

    @Before
    public void setUp() {
        driver = getDriver();
        driver.manage().window().maximize();
        baseUrl = "https://www.tinkoff.ru/mobile-operator/tariffs/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private WebDriver getDriver() {
        try {
            BrowsersFactory.valueOf(System.getProperty("browser"));
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("Укажите наименование браузера в командной строке");
        }
        return BrowsersFactory.valueOf(browserName).create();
    }

    public void selectNonResident() {
        WebElement residentElem = driver.findElement(By.xpath("//div[@class='ui-select__title ui-select__title_columned ui-select__title_fade']"));
        residentElem.click();
        residentElem.sendKeys(Keys.DOWN);
        residentElem.sendKeys(Keys.DOWN);
        residentElem.sendKeys(Keys.ENTER);
    }

    public void writeInField(By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.click();
        element.clear();
        element.sendKeys(text);
        element.sendKeys(Keys.TAB);
    }

    public void controlErrorMessage(By locator, String text) {
        WebElement element = driver.findElement(locator);
        assertEquals(element.getText(), text);
    }

    public void clearField(By locator) {
        WebElement element = driver.findElement(locator);
        element.click();
        element.clear();
        element.sendKeys(Keys.TAB);
    }
}