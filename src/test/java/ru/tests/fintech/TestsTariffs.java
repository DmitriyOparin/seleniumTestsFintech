package ru.tests.fintech;

import org.junit.Test;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public void testTabSwitching() {
        GoogleMainPage googleMainPage = app.googleMainPage;
        googleMainPage.openPage();
        googleMainPage.writeInFieldFindGoogle("мобайл тинькофф ");

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