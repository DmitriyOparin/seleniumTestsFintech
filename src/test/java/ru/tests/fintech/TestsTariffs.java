package ru.tests.fintech;

import org.junit.Test;
import org.openqa.selenium.By;

public class TestsTariffs extends BaseRunner {

    @Test
    public void emptyFields() {
        openSite("https://www.tinkoff.ru/mobile-operator/tariffs/");

        clearField(By.xpath("//div[contains(@class, 'ui-form__row_dropdownFIO')]//input[@name='fio']"));
        controlErrorMessage(By.xpath("//div[contains(@class, 'ui-form__row_dropdownFIO')]//div[contains(@class, 'ui-form-field-error-message')]"), "Укажите ваше ФИО");

        clearField(By.xpath("//div[contains(@class, 'ui-form__row_tel')]//input[@name='phone_mobile']"));
        controlErrorMessage(By.xpath("//div[contains(@class, 'ui-form__row_tel')]//div[contains(@class, 'ui-form-field-error-message')]"), "Необходимо указать номер телефона");

        selectNonResident();

        clearField(By.xpath("//div[contains(@class, 'ui-form__row_dropdownSuggest')]//input[@name='temp_non_resident_nationality']"));
        controlErrorMessage(By.xpath("//div[contains(@class, 'ui-form__row_dropdownSuggest')]//div[contains(@class, 'ui-form-field-error-message')]"), "Поле обязательное");
    }

    @Test
    public void incorrectFields() {
        openSite("https://www.tinkoff.ru/mobile-operator/tariffs/");

        writeInField(By.cssSelector("div.ui-form__row_dropdownFIO input[name=fio]"), "noName");
        controlErrorMessage(By.cssSelector("div.ui-form__row_dropdownFIO div.ui-form-field-error-message"), "Недостаточно информации. Введите фамилию, имя и отчество через пробел (Например: Иванов Иван Алексеевич)");

        writeInField(By.cssSelector("div.ui-form__row_tel input[name=phone_mobile]"), "45672");
        controlErrorMessage(By.cssSelector("div.ui-form__row_tel div.ui-form-field-error-message"), "Номер телефона должен состоять из 10 цифр, начиная с кода оператора");

        writeInField(By.cssSelector("div.ui-form__row_tel input[name=phone_mobile]"), "43454545544");
        controlErrorMessage(By.cssSelector("div.ui-form__row_tel div.ui-form-field-error-message"), "Код оператора должен начинаться с цифры 9");

        selectNonResident();

        writeInField(By.cssSelector("div.ui-form__row_dropdownSuggest input[name=temp_non_resident_nationality]"), "noCountry");
        controlErrorMessage(By.cssSelector("div.ui-form__row_dropdownSuggest div.ui-form-field-error-message"), "Выберите страну из выпадающего списка");

        writeInField(By.cssSelector("div.ui-form__row_dropdownSuggest input[name=email]"), "noEmail");
        controlErrorMessage(By.cssSelector("div.ui-form__row_dropdownSuggest div.ui-form-field-error-message"), "Введите корректный адрес эл. почты");
    }

    @Test
    public void tabSwitching() {
        openSite("https://www.google.ru/");
        writeInFieldFindGoogle(By.xpath("//form[contains(@class, 'tsf')]//input[@name='q']"), "мобайл тинькофф");
        openSiteInFindGoogle(By.xpath("//a[starts-with(@href, 'https://www.tinkoff.ru/mobile-operator/tariffs/')]"));
        String googleWindow = driver.getWindowHandle();
        String mobileWindow = getMobileWindowHandle(googleWindow);
        switchToWindow(mobileWindow);
        controlTitle("Тарифы Тинькофф Мобайла");
        switchToAndCloseWindow(googleWindow);
        switchToWindow(mobileWindow);
        controlUrlWindow("https://www.tinkoff.ru/mobile-operator/tariffs/");
    }

    @Test
    public void changeRegion() {
        openSite("https://www.tinkoff.ru/mobile-operator/tariffs/");

        // если регион НЕ измвестен
        driver.findElement(By.xpath("//div[@name='desktopMvnoRegionConfirmation']//span[@class='MvnoRegionConfirmation__option_v9PfP']")).click();

        // если регион измвестен
        driver.findElement(By.xpath("//div[contains(@class, 'MvnoRegionConfirmation__wrapper_1Jmmm')]")).click();

        // открытие списка и выбор москва
        driver.findElement(By.xpath("//div[@class='MobileOperatorRegionsPopup__regionsContainer_18pns']//div[contains(text(),'Москва')]")).click();

        controlLocation(By.xpath("//div[contains(@class, 'MvnoRegionConfirmation__wrapper_1Jmmm')]"), "Москва");

        driver.navigate().refresh();

        controlLocation(By.xpath("//div[contains(@class, 'MvnoRegionConfirmation__wrapper_1Jmmm')]"), "Москва");

        controlDifferencePriceDefaultPackage("Москва", "Краснодарский край");

        controlDifferencePriceMaximumPackage("Москва", "Краснодарский край");
    }


    @Test
    public void notActiveButton() {
        openSite("https://www.tinkoff.ru/mobile-operator/tariffs/");

        // если регион НЕ измвестен
        driver.findElement(By.xpath("//div[@name='desktopMvnoRegionConfirmation']//span[@class='MvnoRegionConfirmation__option_v9PfP']")).click();

        // если регион измвестен
        driver.findElement(By.xpath("//div[contains(@class, 'MvnoRegionConfirmation__wrapper_1Jmmm')]")).click();

        // открытие списка и выбор москва
        driver.findElement(By.xpath("//div[@class='MobileOperatorRegionsPopup__regionsContainer_18pns']//div[contains(text(),'Москва')]")).click();

        writeInField(By.xpath("//div[contains(@class, 'ui-form__row_dropdownFIO')]//input[@name='fio']"), "Андреева Анна Александровна");
        writeInField(By.xpath("//div[contains(@class, 'ui-form__row_tel')]//input[@name='phone_mobile']"), "9222222222");

        clickButton(By.xpath("//button[contains(@class, 'Button__button_ZsAp')]//div[contains(text(),'Заказать сим-карту')]"));
    }


}



