package ru.tests.fintech;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;


public class BaseRunner {
    WebDriver driver;
    private String browserName = System.getProperty("browser");

    @Before
    public void setUp() {
        driver = getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public WebDriver getDriver() {
        try {
            BrowsersFactory.valueOf(System.getProperty("browser"));
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("В командной строке не указано наименование браузера, поэтому выбран Google Chrome");
            return BrowsersFactory.valueOf("chrome").create();
        }
        return BrowsersFactory.valueOf(browserName).create();
    }

    public void selectNonResident() {
        WebElement residentElem = driver.findElement(By.xpath("//div[contains(@class, 'ui-select__title_columned ui-select__title_fade')]"));
        residentElem.click();
        residentElem.sendKeys(Keys.DOWN);
        residentElem.sendKeys(Keys.DOWN);
        residentElem.sendKeys(Keys.ENTER);
    }

    public void writeInField(By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.click();
        element.clear();
        element.sendKeys(text);
        element.sendKeys(Keys.TAB);
    }

    public void writeInField(String fio, String mobile, String email, String country) {
        TextInput textInput = new TextInput(driver);
        textInput.writeFio(fio);
        textInput.writeMobile(mobile);
        textInput.writeEmail(email);
        textInput.writeCountry(country);
    }


    public void controlErrorMessage(By locator, String text) {
        WebElement element = driver.findElement(locator);
        Assert.assertTrue(text.equals(element.getText()));
    }

    public void clearField(By locator) {
        WebElement element = driver.findElement(locator);
        element.click();
        element.clear();
        element.sendKeys(Keys.TAB);
    }

    public void openPage(String address){
        driver.get(address);
    }

    public void writeInFieldFindGoogle(String text) {
        WebElement element = driver.findElement(By.xpath("//form[contains(@class, 'tsf')]//input[@name='q']"));
        element.click();
        element.clear();
        element.sendKeys(text);
        selectionInFieldFindGoogle(By.xpath("//ul[@class='erkvQe']//span[contains(.,'тарифы')]"));
    }

    private void selectionInFieldFindGoogle(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).click();
    }

    public void openSiteInFindGoogle(String url) {
        driver.findElement(By.xpath("//a[starts-with(@href, '" + url + "')]")).click();
    }

    public void controlUrlWindow(String text) {
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains(text));
    }

    public void switchToAndCloseWindow(String idWindow) {
        driver.switchTo().window(idWindow);
        driver.close();
    }

    public void switchToWindow(String idWindow) {
        driver.switchTo().window(idWindow);
    }

    public String getMobileWindowHandle(String googleWindow) {
        Set<String> allWindows = driver.getWindowHandles();
        String mobileWindow = null;
        allWindows.remove(googleWindow);
        for (String i : allWindows)
            mobileWindow = i;
        return mobileWindow;
    }

    public String getGoogleWindowHandle(){
        return driver.getWindowHandle();
    }

    public void controlTitle(String text) {
        String title = driver.getTitle();
        Assert.assertTrue(text.equals(title));
    }

    public void controlLocation(String text) {
        WebElement element = driver.findElement(By.xpath("//div[contains(@class, 'MvnoRegionConfirmation__wrapper_1Jmmm')]"));
        Assert.assertTrue(element.getText().contains(text));
    }


    public void setMaxSettingPackage() {
        CheckBox checkboxMessengers = new CheckBox(driver,"Мессенджеры");
        checkboxMessengers.setActive();

        CheckBox checkboxSocialNetworks = new CheckBox(driver, "Социальные сети");
        checkboxSocialNetworks.setActive();

        CheckBox checkboxMusic = new CheckBox(driver, "Музыка");
        checkboxMusic.setActive();

        CheckBox checkboxVideo = new CheckBox(driver, "Видео");
        checkboxVideo.setActive();

        CheckBox checkboxUnlimitedSMS = new CheckBox(driver, "Безлимитные СМС");
        checkboxUnlimitedSMS.setActive();

        Select selectInternet = new Select(driver, "Интернет");
        selectInternet.selectionValue("Безлимитный интернет");

        Select selectTelephone = new Select(driver, "Звонки");
        selectTelephone.selectionValue("Безлимитные минуты");

        CheckBox modemMode = new CheckBox(driver, "Режим модема");
        modemMode.setActive();
    }


    public void setMinSettingPackage() {
        CheckBox checkboxMessengers = new CheckBox(driver,"Мессенджеры");
        checkboxMessengers.unsetActive();

        CheckBox checkboxSocialNetworks = new CheckBox(driver, "Социальные сети");
        checkboxSocialNetworks.unsetActive();

        CheckBox checkboxMusic = new CheckBox(driver, "Музыка");
        checkboxMusic.unsetActive();

        CheckBox checkboxVideo = new CheckBox(driver, "Видео");
        checkboxVideo.unsetActive();

        CheckBox checkboxUnlimitedSMS = new CheckBox(driver, "Безлимитные СМС");
        checkboxUnlimitedSMS.unsetActive();

        Select selectInternet = new Select(driver, "Интернет");
        selectInternet.selectionValue("0 ГБ");

        Select selectTelephone = new Select(driver, "Звонки");
        selectTelephone.selectionValue("0 минут");
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


    public void cancelFindRegion(){
        try {
            driver.findElement(By.xpath("//div[@name='desktopMvnoRegionConfirmation']//span[@class='MvnoRegionConfirmation__option_v9PfP']")).click();
        } catch (NoSuchElementException e){ }
    }


    public void changeRegion(String nameCity) {
        driver.findElement(By.xpath("//div[contains(@class, 'MvnoRegionConfirmation__wrapper_1Jmmm')]")).click();
        driver.findElement(By.xpath("//div[@class='MobileOperatorRegionsPopup__regionsContainer_18pns']//div[contains(text(),'" + nameCity + "')]")).click();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void controlNotActiveButton() {
        Button button = new Button(driver);
        boolean enabled = button.notActiveButton();
        Assert.assertFalse(enabled);
    }
}