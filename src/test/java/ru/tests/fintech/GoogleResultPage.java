package ru.tests.fintech;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class GoogleResultPage extends Page {
    public GoogleResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void openSiteInFindGoogle(String url) {
        driver.findElement(By.xpath("//a[starts-with(@href, '" + url + "')]")).click();
    }
}
