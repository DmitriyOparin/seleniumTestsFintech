package ru.tests.fintech.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Select {

    private WebElement selectElement;

//    Select(WebDriver driver, String text) {
//        String stringXpath = "//div[contains(@class, 'ui-dropdown-select_mobile_native')]//span[text()='" + text + "']";
//        this.selectElement = driver.findElement(By.xpath(stringXpath));
//    }

    public void selectionValue(String text){
        selectElement.click();
        String stringXpath = "//div[contains(@class, 'ui-dropdown-select_mobile_native')]//span[text()='" + text + "']";
        selectElement.findElement(By.xpath(stringXpath)).click();
    }
}
