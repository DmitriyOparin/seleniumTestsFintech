package ru.tests.fintech;

import org.junit.Test;
import org.openqa.selenium.By;

public class TestsTariffs extends BaseRunner {


    @Test
    public void testEmptyFields() {
        openPage("https://www.tinkoff.ru/mobile-operator/tariffs/");
        clearField(By.xpath("//div[contains(@class, 'ui-form__row_dropdownFIO')]//input[@name='fio']"));
        controlErrorMessage(By.xpath("//div[contains(@class, 'ui-form__row_dropdownFIO')]//div[contains(@class, 'ui-form-field-error-message')]"), "Укажите ваше ФИО");
        clearField(By.xpath("//div[contains(@class, 'ui-form__row_tel')]//input[@name='phone_mobile']"));
        controlErrorMessage(By.xpath("//div[contains(@class, 'ui-form__row_tel')]//div[contains(@class, 'ui-form-field-error-message')]"), "Необходимо указать номер телефона");
        selectNonResident();
        clearField(By.xpath("//div[contains(@class, 'ui-form__row_dropdownSuggest')]//input[@name='temp_non_resident_nationality']"));
        controlErrorMessage(By.xpath("//div[contains(@class, 'ui-form__row_dropdownSuggest')]//div[contains(@class, 'ui-form-field-error-message')]"), "Поле обязательное");
    }


    @Test
    public void testIncorrectFields() {
        openPage("https://www.tinkoff.ru/mobile-operator/tariffs/");
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
    public void testTabSwitching() {
        openPage("https://www.google.ru/");
        writeInFieldFindGoogle("мобайл тинькофф");
        openSiteInFindGoogle("https://www.tinkoff.ru/mobile-operator/tariffs/");
        String googleWindow = getGoogleWindowHandle();
        String mobileWindow = getMobileWindowHandle(googleWindow);
        switchToWindow(mobileWindow);
        controlTitle("Тарифы Тинькофф Мобайла");
        switchToAndCloseWindow(googleWindow);
        switchToWindow(mobileWindow);
        controlUrlWindow("https://www.tinkoff.ru/mobile-operator/tariffs/");
    }


    @Test
    public void testChangeRegion() {
        openPage("https://www.tinkoff.ru/mobile-operator/tariffs/");
        cancelFindRegion();
        changeRegion("Москва");
        controlLocation("Москва");
        refreshPage();
        controlLocation("Москва");
        controlDifferencePriceDefaultPackage("Краснодарский", "Москва");
        controlEqualPriceMaximumPackage("Краснодарский", "Москва");
    }


    @Test
    public void testNotActiveButton() {
        openPage("https://www.tinkoff.ru/mobile-operator/tariffs/");
        cancelFindRegion();
        changeRegion("Москва");
        setMinSettingPackage();
        writeInField("Андреева Анна Александровна", "9222222222", null, null);
        controlNotActiveButton();
    }
}