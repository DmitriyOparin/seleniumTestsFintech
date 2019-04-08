package ru.tests.fintech;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoogleResultPage extends Page {

    public Logger logger = LoggerFactory.getLogger(GoogleResultPage.class);

    public GoogleResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void openSiteInFindGoogle(String url) {
        driver.findElement(By.xpath("//a[starts-with(@href, '" + url + "')]")).click();
        logger.info("Открыта страница по url: " + url);

    }
}
