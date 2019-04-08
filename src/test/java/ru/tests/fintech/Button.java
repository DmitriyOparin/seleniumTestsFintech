package ru.tests.fintech;

import org.openqa.selenium.WebElement;

public class Button {

    public void clickButton(WebElement element) {
        element.click();
    }

    public boolean notActiveButton(WebElement element) {
        return element.isEnabled();
    }
}
