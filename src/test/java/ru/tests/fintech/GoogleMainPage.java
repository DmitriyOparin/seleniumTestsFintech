package ru.tests.fintech;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleMainPage extends Page  {
    public GoogleMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "q")
    public WebElement searchField;

    public void openPage() {
        driver.get("https://www.google.ru/");
    }


    public void writeInFieldFindGoogle(String text) {
        searchField.click();
        searchField.clear();
        searchField.sendKeys(text);
        selectionInFieldFindGoogle(By.xpath("//ul[@class='erkvQe']//span[contains(.,'тарифы')]"));
    }

    private void selectionInFieldFindGoogle(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).click();
    }
}
