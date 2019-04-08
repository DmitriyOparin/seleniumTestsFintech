package ru.tests.fintech;

import org.openqa.selenium.WebElement;

public class CheckBox {

    public void setActive(WebElement element) {
        element.click();

    }

    public void unsetActive(WebElement element) {
        element.click();
    }

    public boolean isSelectedCheckboxElement(WebElement element) {
        return element.isSelected();
    }
}