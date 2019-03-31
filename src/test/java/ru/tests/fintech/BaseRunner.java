package ru.tests.fintech;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;


public class BaseRunner {
    WebDriver driver;
    public String browserName = System.getProperty("browser");

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

    private WebDriver getDriver() {
        try {
            BrowsersFactory.valueOf(System.getProperty("browser"));
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("Укажите наименование браузера в командной строке");
        }
        return BrowsersFactory.valueOf(browserName).create();
    }

    public void selectNonResident() {
        WebElement residentElem = driver.findElement(By.xpath("//div[@class='ui-select__title ui-select__title_columned ui-select__title_fade']"));
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

    public void openSite(String address){
        driver.get(address);
    }

    public void writeInFieldFindGoogle(By locator, String text) {
        WebElement element = driver.findElement(locator);
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

    public void openSiteInFindGoogle(By locator) {
        WebElement element = driver.findElement(locator);
        element.click();
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

    public void controlTitle(String text) {
        String title = driver.getTitle();
        Assert.assertTrue(text.equals(title));
    }

    public void controlLocation(By locator, String text) {
        WebElement element = driver.findElement(locator);
        Assert.assertTrue(element.getText().contains(text));
    }

    protected void clickButton(By locator) {
        WebElement element = driver.findElement(locator);
        element.click();
    }

    private void setMaxSettingPackage() {

        WebElement checkboxMessengers = driver.findElement(By.xpath("//div[@class='CheckboxSet__root_3OLWA']//label[contains(text(),'Мессенджеры')]/..//div[@class='Checkbox__inputOuter_5tJV0']//input"));
        if (!checkboxMessengers.isSelected()) {
            driver.findElement(By.xpath("//div[@class='CheckboxSet__root_3OLWA']//label[contains(text(),'Мессенджеры')]")).click();
        }

        WebElement checkboxSocialNetworks = driver.findElement(By.xpath("//div[@class='CheckboxSet__root_3OLWA']//label[contains(text(),'Социальные сети')]/..//div[@class='Checkbox__inputOuter_5tJV0']//input"));
        if (!checkboxSocialNetworks.isSelected()) {
            driver.findElement(By.xpath("//div[@class='CheckboxSet__root_3OLWA']//label[contains(text(),'Социальные сети')]")).click();
        }

        WebElement checkboxMusic = driver.findElement(By.xpath("//div[@class='CheckboxSet__root_3OLWA']//label[contains(text(),'Музыка')]/..//div[@class='Checkbox__inputOuter_5tJV0']//input"));
        if (!checkboxMusic.isSelected()) {
            driver.findElement(By.xpath("//div[@class='CheckboxSet__root_3OLWA']//label[contains(text(),'Музыка')]")).click();
        }

        WebElement checkboxVideo = driver.findElement(By.xpath("//div[@class='CheckboxSet__root_3OLWA']//label[contains(text(),'Видео')]/..//div[@class='Checkbox__inputOuter_5tJV0']//input"));
        if (!checkboxVideo.isSelected()) {
            driver.findElement(By.xpath("//div[@class='CheckboxSet__root_3OLWA']//label[contains(text(),'Видео')]")).click();
        }

        WebElement checkboxUnlimitedSMS = driver.findElement(By.xpath("//div[@class='CheckboxSet__root_3OLWA']//label[contains(text(),'Безлимитные СМС')]/..//div[@class='Checkbox__inputOuter_5tJV0']//input"));
        if (!checkboxUnlimitedSMS.isSelected()) {
            driver.findElement(By.xpath("//div[@class='CheckboxSet__root_3OLWA']//label[contains(text(),'Безлимитные СМС')]")).click();

        }

        driver.findElement(By.xpath("//div[contains(@class, 'ui-dropdown-select_mobile_native')]//span[contains(text(),'Интернет')]")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'ui-dropdown-select_mobile_native')]//span[contains(text(),'Безлимитный интернет')]")).click();


        driver.findElement(By.xpath("//div[contains(@class, 'ui-dropdown-select_mobile_native')]//span[contains(text(),'Звонки')]")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'ui-dropdown-select_mobile_native')]//span[contains(text(),'Безлимитные минуты')]")).click();


        WebElement modemMode = driver.findElement(By.xpath("//div[@class='CheckboxSet__root_3OLWA']//label[contains(text(),'Режим модема')]/..//div[@class='Checkbox__inputOuter_5tJV0']//input"));
        if (!modemMode.isSelected()) {
            driver.findElement(By.xpath("//div[@class='CheckboxSet__root_3OLWA']//label[contains(text(),'Режим модема')]")).click();
        }

    }

    public void controlDifferencePriceDefaultPackage(String firstCity, String secondCity) {
        String priceFirstCity = driver.findElement(By.xpath("//div[@class='ui-form__field ui-form__field_title']")).getText();


        driver.findElement(By.xpath("//div[contains(@class, 'MvnoRegionConfirmation__wrapper_1Jmmm')]")).click();


        driver.findElement(By.xpath("//div[@class='MobileOperatorRegionsPopup__regionsContainer_18pns']//div[contains(text(),'Краснодарский')]")).click();

        String priceSecondCity = driver.findElement(By.xpath("//div[@class='ui-form__field ui-form__field_title']")).getText();

        Assert.assertFalse(priceFirstCity.equals(priceSecondCity));


        // открытие списка и выбор москва
        driver.findElement(By.xpath("//div[contains(@class, 'MvnoRegionConfirmation__wrapper_1Jmmm')]")).click();
        driver.findElement(By.xpath("//div[@class='MobileOperatorRegionsPopup__regionsContainer_18pns']//div[contains(text(),'Москва')]")).click();
    }

    public void controlDifferencePriceMaximumPackage(String firstCity, String secondCity) {

        // установка галок и слайдер
        setMaxSettingPackage();

        String priceFirstCity = driver.findElement(By.xpath("//div[@class='ui-form__field ui-form__field_title']")).getText();


        driver.findElement(By.xpath("//div[contains(@class, 'MvnoRegionConfirmation__wrapper_1Jmmm')]")).click();


        driver.findElement(By.xpath("//div[@class='MobileOperatorRegionsPopup__regionsContainer_18pns']//div[contains(text(),'Краснодарский')]")).click();

        // установка галок и слайдер
        setMaxSettingPackage();

        String priceSecondCity = driver.findElement(By.xpath("//div[@class='ui-form__field ui-form__field_title']")).getText();

        Assert.assertTrue(priceFirstCity.equals(priceSecondCity));


        // открытие списка и выбор москва
        driver.findElement(By.xpath("//div[contains(@class, 'MvnoRegionConfirmation__wrapper_1Jmmm')]")).click();
        driver.findElement(By.xpath("//div[@class='MobileOperatorRegionsPopup__regionsContainer_18pns']//div[contains(text(),'Москва')]")).click();
    }
}