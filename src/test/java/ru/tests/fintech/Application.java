package ru.tests.fintech;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Application {
    public TinkoffMobilePage tinkoffMobilePage;
    public TinkoffDocumentsPage tinkoffDocumentsPage;
    public GoogleMainPage googleMainPage;
    public GoogleResultPage googleResultPage;
    public String browserName = System.getProperty("browser") == null ? "chrome" : System.getProperty("browser");
    private WebDriverWait wait;
    private WebDriver driver;


    public Application() {
        driver = getDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        tinkoffMobilePage = new TinkoffMobilePage(driver);
        tinkoffDocumentsPage = new TinkoffDocumentsPage(driver);
        googleMainPage = new GoogleMainPage(driver);
        googleResultPage = new GoogleResultPage(driver);
    }

    public WebDriver getDriver() {
        try {
            BrowsersFactory.valueOf(System.getProperty("browser"));
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("В командной строке не указано наименование браузера, поэтому выбран Google Chrome");
        }
        return BrowsersFactory.valueOf(browserName).create();
    }

    public void quit() {
        driver.quit();
        driver = null;
    }

    public void controlErrorMessage(By locator, String text) {
        WebElement element = driver.findElement(locator);
        Assert.assertTrue(text.equals(element.getText()));
    }

    public void clearField(By locator) {
        WebElement element = driver.findElement(locator);
        element.click();
        element.clear();
        element.sendKeys(Keys.TAB);
    }

    public void writeInField(By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.click();
        element.clear();
        element.sendKeys(text);
        element.sendKeys(Keys.TAB);
    }

    public void selectNonResident() {
        WebElement residentElem = driver.findElement(By.xpath("//div[contains(@class, 'ui-select__title_columned ui-select__title_fade')]"));
        residentElem.click();
        residentElem.sendKeys(Keys.DOWN);
        residentElem.sendKeys(Keys.DOWN);
        residentElem.sendKeys(Keys.ENTER);
    }
}
