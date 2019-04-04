package ru.tests.fintech;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckBox {
    private WebElement checkboxElementClicked;
    private WebElement checkboxElementChecked;

    CheckBox(WebDriver driver, String text) {
        String stringXpathClicked = "//div[contains(@class, 'CheckboxSet__root_3OLWA')]" +
                "//label[contains(text(),'" + text + "')]";
        this.checkboxElementClicked = driver.findElement(By.xpath(stringXpathClicked));

        String stringXpathChecked = "//div[@class='CheckboxSet__root_3OLWA']//label[contains(text(),'" + text + "')]" +
                "/..//div[contains(@class,'Checkbox__inputOuter_5tJV0')]//input";
        this.checkboxElementChecked = driver.findElement(By.xpath(stringXpathChecked));
    }

    public void setActive() {
        if (!isSelectedCheckboxElement()) {
            checkboxElementClicked.click();
        }
    }

    public void unsetActive() {
        if (isSelectedCheckboxElement()) {
            checkboxElementClicked.click();
        }
    }

    private boolean isSelectedCheckboxElement() {
        return checkboxElementChecked.isSelected();
    }
}