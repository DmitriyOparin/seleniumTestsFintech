package ru.tests.fintech;

import org.junit.Test;
import org.openqa.selenium.By;

public class TestsTariffs extends BaseRunner {


    @Test
    public void testEmptyFields() {
        TinkoffMobilePage tinkoffMobilePage = app.tinkoffMobilePage;
        tinkoffMobilePage.openPage();
        app.clearField(By.xpath("//div[contains(@class, 'ui-form__row_dropdownFIO')]//input[@name='fio']"));
        app.controlErrorMessage(By.xpath("//div[contains(@class, 'ui-form__row_dropdownFIO')]//div[contains(@class, 'ui-form-field-error-message')]"), "Укажите ваше ФИО");
        app.clearField(By.xpath("//div[contains(@class, 'ui-form__row_tel')]//input[@name='phone_mobile']"));
        app.controlErrorMessage(By.xpath("//div[contains(@class, 'ui-form__row_tel')]//div[contains(@class, 'ui-form-field-error-message')]"), "Необходимо указать номер телефона");
    }


    @Test
    public void testIncorrectFields() {
        TinkoffMobilePage tinkoffMobilePage = app.tinkoffMobilePage;
        tinkoffMobilePage.openPage();
        app.writeInField(By.cssSelector("div.ui-form__row_dropdownFIO input[name=fio]"), "noName");
        app.controlErrorMessage(By.cssSelector("div.ui-form__row_dropdownFIO div.ui-form-field-error-message"), "Недостаточно информации. Введите фамилию, имя и отчество через пробел (Например: Иванов Иван Алексеевич)");
        app.writeInField(By.cssSelector("div.ui-form__row_tel input[name=phone_mobile]"), "45672");
        app.controlErrorMessage(By.cssSelector("div.ui-form__row_tel div.ui-form-field-error-message"), "Номер телефона должен состоять из 10 цифр, начиная с кода оператора");
        app.writeInField(By.cssSelector("div.ui-form__row_tel input[name=phone_mobile]"), "43454545544");
        app.controlErrorMessage(By.cssSelector("div.ui-form__row_tel div.ui-form-field-error-message"), "Код оператора должен начинаться с цифры 9");
        app.selectNonResident();
        app.writeInField(By.cssSelector("div.ui-form__row_dropdownSuggest input[name=temp_non_resident_nationality]"), "noCountry");
        app.controlErrorMessage(By.cssSelector("div.ui-form__row_dropdownSuggest div.ui-form-field-error-message"), "Выберите страну из выпадающего списка");
        app.writeInField(By.cssSelector("div.ui-form__row_dropdownSuggest input[name=email]"), "noEmail");
        app.controlErrorMessage(By.cssSelector("div.ui-form__row_dropdownSuggest div.ui-form-field-error-message"), "Введите корректный адрес эл. почты");
    }


    @Test
    public void testTabSwitching() {
        GoogleMainPage googleMainPage = app.googleMainPage;
        googleMainPage.openPage();
        googleMainPage.writeInFieldFindGoogle("мобайл тинькофф");

        GoogleResultPage googleResultPage = app.googleResultPage;
        googleResultPage.openSiteInFindGoogle("https://www.tinkoff.ru/mobile-operator/tariffs/");
        String googleWindow = googleResultPage.getGoogleWindowHandle();
        String mobileWindow = googleResultPage.getMobileWindowHandle(googleWindow);
        googleResultPage.switchToWindow(mobileWindow);
        googleResultPage.controlTitle("Тарифы Тинькофф Мобайла");
        googleResultPage.switchToAndCloseWindow(googleWindow);
        googleResultPage.switchToWindow(mobileWindow);
        googleResultPage.controlUrlWindow("https://www.tinkoff.ru/mobile-operator/tariffs/");
    }


    @Test
    public void testChangeRegion() {
        TinkoffMobilePage tinkoffMobilePage = app.tinkoffMobilePage;
        tinkoffMobilePage.openPage();
        tinkoffMobilePage.cancelFindRegion();
        tinkoffMobilePage.changeRegion("Москва");
        tinkoffMobilePage.controlLocation("Москва");
        tinkoffMobilePage.refreshPage();
        tinkoffMobilePage.controlLocation("Москва");
        tinkoffMobilePage.controlDifferencePriceDefaultPackage("Краснодарский", "Москва");
        tinkoffMobilePage.controlEqualPriceMaximumPackage("Краснодарский", "Москва");
    }


    @Test
    public void testNotActiveButton() {
        TinkoffMobilePage tinkoffMobilePage = app.tinkoffMobilePage;
        tinkoffMobilePage.openPage();
        tinkoffMobilePage.cancelFindRegion();
        tinkoffMobilePage.changeRegion("Москва");
        tinkoffMobilePage.setMinSettingPackage();
        tinkoffMobilePage.writeInField("Андреева Анна Александровна", "9222222222", null, null);
        tinkoffMobilePage.controlNotActiveButton();
    }


    @Test
    public void testDownloadFile() {
        TinkoffDocumentsPage tinkoffDocumentsPage = app.tinkoffDocumentsPage;
        tinkoffDocumentsPage.openPage();
        tinkoffDocumentsPage.downloadAndControlFile("Описание акции \"Год кино с Тинькофф Мобайл\"");
    }

}