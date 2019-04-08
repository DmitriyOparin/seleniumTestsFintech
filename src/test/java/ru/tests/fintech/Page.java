package ru.tests.fintech;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class Page {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public String getGoogleWindowHandle() {
        return driver.getWindowHandle();
    }

    public String getMobileWindowHandle(String googleWindow) {
        Set<String> allWindows = driver.getWindowHandles();
        String mobileWindow = null;
        allWindows.remove(googleWindow);
        for (String i : allWindows)
            mobileWindow = i;
        return mobileWindow;
    }

    public void switchToWindow(String idWindow) {
        driver.switchTo().window(idWindow);
    }

    public void controlUrlWindow(String text) {
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains(text));
    }


    public void controlTitle(String text) {
        String title = driver.getTitle();
        Assert.assertTrue(text.equals(title));
    }

    public void switchToAndCloseWindow(String idWindow) {
        driver.switchTo().window(idWindow);
        driver.close();
    }
}
