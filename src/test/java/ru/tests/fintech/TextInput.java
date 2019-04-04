package ru.tests.fintech;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextInput {

    private WebElement fioElement;
    private WebElement mobileElement;
    private WebElement emailElement;
    private WebElement countryElement;

    public TextInput(WebDriver driver) {
        String stringFioXpath = "//div[contains(@class, 'ui-form__row_dropdownFIO')]//input[@name='fio']";
        this.fioElement = driver.findElement(By.xpath(stringFioXpath));

        String stringMobileXpath = "//div[contains(@class, 'ui-form__row_tel')]//input[@name='phone_mobile']";
        this.mobileElement = driver.findElement(By.xpath(stringMobileXpath));


            String stringEmailXpath = "//div[contains(@class, 'ui-form__row_dropdownSuggest')]//input[@name='email']";
            this.emailElement = driver.findElement(By.xpath(stringEmailXpath));

             selectNonResident(driver);

            String stringCountryXpath = "//div[contains(@class, 'ui-form__row_dropdownSuggest')]//input[@name='temp_non_resident_nationality']";
            this.countryElement = driver.findElement(By.xpath(stringCountryXpath));

    }

    public void selectNonResident(WebDriver driver) {
        String stringXpath = "//div[contains(@class, 'ui-form__row_dropdownSelect')]";
        driver.findElement(By.xpath(stringXpath)).click();

        String stringXpath1 = "//div[contains(@class, 'ui-form__row_dropdownSelect')]//span[text()='Не имею гражданства РФ']";
        driver.findElement(By.xpath(stringXpath1)).click();
    }

    public void writeFio(String fio) {
        fioElement.clear();
        fioElement.sendKeys(fio);
    }

    public void writeMobile(String mobile) {
        mobileElement.clear();
        mobileElement.sendKeys(mobile);
    }

    public void writeEmail(String email) {
        if (email != null){
            emailElement.clear();
            emailElement.sendKeys(email);
        }
    }

    public void writeCountry(String country) {
        if (country != null){
            countryElement.clear();
            countryElement.sendKeys(country);
        }
    }
}
