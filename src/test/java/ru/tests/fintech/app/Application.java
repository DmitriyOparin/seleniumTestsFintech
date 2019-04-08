package ru.tests.fintech.app;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.tests.fintech.pages.GoogleMainPage;
import ru.tests.fintech.pages.GoogleResultPage;
import ru.tests.fintech.pages.TinkoffDocumentsPage;
import ru.tests.fintech.pages.TinkoffMobilePage;
import ru.tests.fintech.tests.BrowsersFactory;

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

}
