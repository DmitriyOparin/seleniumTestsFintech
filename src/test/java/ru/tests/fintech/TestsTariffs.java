package ru.tests.fintech;

import org.junit.Test;
import org.openqa.selenium.By;

public class TestsTariffs extends BaseRunner {

    @Test
    public void emptyFields() {
        driver.get(baseUrl);

        clearField(By.name("fio"));
        controlErrorMessage(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Фамилия, имя и отчество'])[1]/following::div[3]"), "Укажите ваше ФИО");

        clearField(By.name("phone_mobile"));
        controlErrorMessage(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Контактный телефон'])[1]/following::div[2]"), "Необходимо указать номер телефона");

        selectNonResident();

        clearField(By.name("temp_non_resident_nationality"));
        controlErrorMessage(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='ЯПОНИЯ'])[1]/following::div[3]"), "Поле обязательное");
    }

    @Test
    public void incorrectFields() {
        driver.get(baseUrl);

        writeInField(By.name("fio"), "noName");
        controlErrorMessage(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Фамилия, имя и отчество'])[1]/following::div[3]"), "Недостаточно информации. Введите фамилию, имя и отчество через пробел (Например: Иванов Иван Алексеевич)");

        writeInField(By.name("phone_mobile"), "45672");
        controlErrorMessage(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Контактный телефон'])[1]/following::div[2]"), "Номер телефона должен состоять из 10 цифр, начиная с кода оператора");

        writeInField(By.name("phone_mobile"), "43454545544");
        controlErrorMessage(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Контактный телефон'])[1]/following::div[2]"), "Код оператора должен начинаться с цифры 9");

        selectNonResident();

        writeInField(By.name("temp_non_resident_nationality"), "country");
        controlErrorMessage(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Укажите страну'])[1]/following::div[3]"), "Выберите страну из выпадающего списка");

        writeInField(By.name("email"), "noEmail");
        controlErrorMessage(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Электронная почта'])[1]/following::div[3]"), "Введите корректный адрес эл. почты");
    }
}