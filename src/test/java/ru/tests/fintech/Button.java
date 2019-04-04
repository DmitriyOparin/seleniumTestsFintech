package ru.tests.fintech;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Button {

    private WebElement buttonElement;

    public Button(WebDriver driver) {
        String stringXpath = "//button[contains(@class, 'Button__button_ZsAp')]//div[contains(text(),'Заказать сим-карту')]";
        this.buttonElement = driver.findElement(By.xpath(stringXpath));
    }

    public boolean notActiveButton() {
        return buttonElement.isEnabled();
    }
}
