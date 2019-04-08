package ru.tests.fintech.elements;

import org.openqa.selenium.WebElement;

public class TextInput {

    public void writeTextInput(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public String getTextInField(WebElement element) {
        return element.getText();
    }
}
