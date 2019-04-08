package ru.tests.fintech;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TinkoffMobilePage extends Page {

    public TinkoffMobilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // текстовые поля
    @FindBy(xpath = "//div[contains(@class, 'ui-form__row_dropdownFIO')]//input[@name='fio']")
    public WebElement textFieldFio;

    @FindBy(xpath = "//div[contains(@class, 'ui-form__row_tel')]//input[@name='phone_mobile']")
    public WebElement textFieldMobile;

    @FindBy(xpath = "//div[contains(@class, 'ui-form__row_dropdownSuggest')]//input[@name='email']")
    public WebElement textFieldEmail;

    // выпадающие списки
    @FindBy(xpath = "//div[contains(@class, 'ui-form__row_dropdownSelect')]")
    public WebElement selectNonResident;

    @FindBy(xpath = "//div[contains(@class, 'ui-dropdown-select_mobile_native')]//span[text()='Интернет']")
    public WebElement selectInternet;

    @FindBy(xpath = "//div[contains(@class, 'ui-dropdown-select_mobile_native')]//span[text()='Звонки']")
    public WebElement selectMobile;

    // кнопки
    @FindBy(xpath = "//button[contains(@class, 'Button__button_ZsAp')]//div[contains(text(),'Заказать сим-карту')]")
    public WebElement buttonOrderSimCard;

    // чекбоксы
    @FindBy(xpath = "//label[contains(text(),'Мессенджеры')]")
    public WebElement checkboxMessengerClicked;
    @FindBy(xpath = "//label[contains(text(),'Мессенджеры')]/..//div[contains(@class,'Checkbox__inputOuter_5tJV0')]//input")
    public WebElement checkboxMessengerChecked;

    @FindBy(xpath = "//label[contains(text(),'Социальные сети')]")
    public WebElement checkboxSocialNetworksClicked;
    @FindBy(xpath = "//label[contains(text(),'Социальные сети')]/..//div[contains(@class,'Checkbox__inputOuter_5tJV0')]//input")
    public WebElement checkboxSocialNetworksChecked;

    @FindBy(xpath = "//label[contains(text(),'Музыка')]")
    public WebElement checkboxMusicClicked;
    @FindBy(xpath = "//label[contains(text(),'Музыка')]/..//div[contains(@class,'Checkbox__inputOuter_5tJV0')]//input")
    public WebElement checkboxMusicChecked;

    @FindBy(xpath = "//label[contains(text(),'Видео')]")
    public WebElement checkboxVideoClicked;
    @FindBy(xpath = "//label[contains(text(),'Видео')]/..//div[contains(@class,'Checkbox__inputOuter_5tJV0')]//input")
    public WebElement checkboxVideoChecked;

    @FindBy(xpath = "//label[contains(text(),'Безлимитные СМС')]")
    public WebElement checkboxUnlimitedSMSClicked;
    @FindBy(xpath = "//label[contains(text(),'Безлимитные СМС')]/..//div[contains(@class,'Checkbox__inputOuter_5tJV0')]//input")
    public WebElement checkboxUnlimitedSMSChecked;

    @FindBy(xpath = "//label[contains(text(),'Режим модема')]")
    public WebElement checkboxmodemModeClicked;
    @FindBy(xpath = "//label[contains(text(),'Режим модема')]/..//div[contains(@class,'Checkbox__inputOuter_5tJV0')]//input")
    public WebElement checkboxmodemModeChecked;


    public void openPage() {
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
    }

    public void writeInField(String fio, String mobile, String email, String country) {
        TextInput textInput = new TextInput();

        textInput.writeTextInput(textFieldFio, fio);
        textInput.writeTextInput(textFieldMobile, mobile);

        if (email != null) {
            textInput.writeTextInput(textFieldEmail, email);
        }

        if (country != null) {
            WebElement countryElement = selectNonResident();
            textInput.writeTextInput(countryElement, country);
        }
    }

    private WebElement selectNonResident() {
        selectNonResident.click();

        String stringXpath1 = "//div[contains(@class, 'ui-form__row_dropdownSelect')]//span[text()='Не имею гражданства РФ']";
        driver.findElement(By.xpath(stringXpath1)).click();

        String stringCountryXpath = "//div[contains(@class, 'ui-form__row_dropdownSuggest')]//input[@name='temp_non_resident_nationality']";

        return driver.findElement(By.xpath(stringCountryXpath));
    }

    public void cancelFindRegion() {
        try {
            driver.findElement(By.xpath("//div[@name='desktopMvnoRegionConfirmation']//span[@class='MvnoRegionConfirmation__option_v9PfP']")).click();
        } catch (NoSuchElementException ignored) {
        }
    }

    public void changeRegion(String nameCity) {
        driver.findElement(By.xpath("//div[contains(@class, 'MvnoRegionConfirmation__wrapper_1Jmmm')]")).click();
        driver.findElement(By.xpath("//div[@class='MobileOperatorRegionsPopup__regionsContainer_18pns']//div[contains(text(),'" + nameCity + "')]")).click();
    }

    public void setMinSettingPackage() {
        CheckBox checkboxMessengers = new CheckBox();
        boolean isSelectedCheckboxMessengers = checkboxMessengers.isSelectedCheckboxElement(checkboxMessengerChecked);
        if (isSelectedCheckboxMessengers) {
            checkboxMessengers.unsetActive(checkboxMessengerClicked);
        }

        CheckBox checkboxSocialNetworks = new CheckBox();
        boolean isSelectedCheckboxSocialNetworks = checkboxSocialNetworks.isSelectedCheckboxElement(checkboxSocialNetworksChecked);
        if (isSelectedCheckboxSocialNetworks) {
            checkboxSocialNetworks.unsetActive(checkboxSocialNetworksClicked);
        }

        CheckBox checkboxMusic = new CheckBox();
        boolean isSelectedCheckboxMusic = checkboxMusic.isSelectedCheckboxElement(checkboxMusicChecked);
        if (isSelectedCheckboxMusic) {
            checkboxMusic.unsetActive(checkboxMusicClicked);
        }

        CheckBox checkboxVideo = new CheckBox();
        boolean isSelectedCheckboxVideo = checkboxVideo.isSelectedCheckboxElement(checkboxVideoChecked);
        if (isSelectedCheckboxVideo) {
            checkboxVideo.unsetActive(checkboxVideoClicked);
        }

        CheckBox checkboxUnlimitedSMS = new CheckBox();
        boolean isSelectedcCeckboxUnlimitedSMS = checkboxUnlimitedSMS.isSelectedCheckboxElement(checkboxUnlimitedSMSChecked);
        if (isSelectedcCeckboxUnlimitedSMS) {
            checkboxUnlimitedSMS.unsetActive(checkboxUnlimitedSMSClicked);
        }

        selectedValueInInternet("0 ГБ");
        selectedValueInMobile("0 минут");
    }

    public void setMaxSettingPackage() {
        CheckBox checkboxMessengers = new CheckBox();
        boolean isSelectedCheckboxMessengers = checkboxMessengers.isSelectedCheckboxElement(checkboxMessengerChecked);
        if (!isSelectedCheckboxMessengers) {
            checkboxMessengers.setActive(checkboxMessengerClicked);
        }

        CheckBox checkboxSocialNetworks = new CheckBox();
        boolean isSelectedCheckboxSocialNetworks = checkboxSocialNetworks.isSelectedCheckboxElement(checkboxSocialNetworksChecked);
        if (!isSelectedCheckboxSocialNetworks) {
            checkboxSocialNetworks.setActive(checkboxSocialNetworksClicked);
        }

        CheckBox checkboxMusic = new CheckBox();
        boolean isSelectedCheckboxMusic = checkboxMusic.isSelectedCheckboxElement(checkboxMusicChecked);
        if (!isSelectedCheckboxMusic) {
            checkboxMusic.setActive(checkboxMusicClicked);
        }

        CheckBox checkboxVideo = new CheckBox();
        boolean isSelectedCheckboxVideo = checkboxVideo.isSelectedCheckboxElement(checkboxVideoChecked);
        if (!isSelectedCheckboxVideo) {
            checkboxVideo.setActive(checkboxVideoClicked);
        }

        CheckBox checkboxUnlimitedSMS = new CheckBox();
        boolean isSelectedCheckboxUnlimitedSMS = checkboxUnlimitedSMS.isSelectedCheckboxElement(checkboxUnlimitedSMSChecked);
        if (!isSelectedCheckboxUnlimitedSMS) {
            checkboxUnlimitedSMS.setActive(checkboxUnlimitedSMSClicked);
        }

        selectedValueInInternet("Безлимитный интернет");
        selectedValueInMobile("Безлимитные минуты");

        CheckBox checkboxModemMode = new CheckBox();
        boolean isSelectedCheckboxModemMode = checkboxModemMode.isSelectedCheckboxElement(checkboxmodemModeChecked);
        if (!isSelectedCheckboxModemMode) {
            checkboxModemMode.setActive(checkboxmodemModeClicked);
        }

    }

    private void selectedValueInInternet(String text) {
        selectInternet.click();
        String stringXpath1 = "//div[contains(@class, 'ui-dropdown-select_mobile_native')]//span[text()='" + text + "']";
        selectInternet.findElement(By.xpath(stringXpath1)).click();
    }

    private void selectedValueInMobile(String text) {
        selectMobile.click();
        String stringXpath1 = "//div[contains(@class, 'ui-dropdown-select_mobile_native')]//span[text()='" + text + "']";
        selectMobile.findElement(By.xpath(stringXpath1)).click();
    }

    public void controlNotActiveButton() {
        Button button = new Button();
        boolean enabled = button.notActiveButton(buttonOrderSimCard);
        Assert.assertFalse(enabled);
    }

    public void controlLocation(String text) {
        WebElement element = driver.findElement(By.xpath("//div[contains(@class, 'MvnoRegionConfirmation__wrapper_1Jmmm')]"));
        Assert.assertTrue(element.getText().contains(text));
    }


    public void controlDifferencePriceDefaultPackage(String firstCity, String secondCity) {
        String priceFirstCity = driver.findElement(By.xpath("//div[contains(@class, 'ui-form__field_title')]")).getText();
        changeRegion(firstCity);
        String priceSecondCity = driver.findElement(By.xpath("//div[contains(@class, 'ui-form__field_title')]")).getText();
        Assert.assertFalse(priceFirstCity.equals(priceSecondCity));
        changeRegion(secondCity);
    }

    public void controlEqualPriceMaximumPackage(String firstCity, String secondCity) {
        setMaxSettingPackage();
        String priceFirstCity = driver.findElement(By.xpath("//div[contains(@class, 'ui-form__field_title')]")).getText();
        changeRegion(firstCity);
        setMaxSettingPackage();
        String priceSecondCity = driver.findElement(By.xpath("//div[contains(@class, 'ui-form__field_title')]")).getText();
        Assert.assertTrue(priceFirstCity.equals(priceSecondCity));
        changeRegion(secondCity);
    }
}