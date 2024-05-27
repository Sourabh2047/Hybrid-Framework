package doxzilla_pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Doxzilla_HomePage {
    @FindBy(xpath = "//button[@class='btn btn-blue button-color']")
    public WebElement acceptCookies;
    @FindBy(xpath = "//a[@class='nav-link Home active']")
    public WebElement navHome;
    @FindBy(xpath = "//a[@class='nav-link Hot-New']")
    public WebElement navHotNew;
    @FindBy(xpath = "//a[@class='nav-link Category']")
    public WebElement navAllContent;
    @FindBy(xpath = "//a[@class='nav-link My-List']")
    public WebElement navMyList;
    @FindBy(xpath = "//input[@type='text']")
    public WebElement SearchTextbox;

    //********************************** Footer Section ***************************************************
    @FindBy(xpath = "(//span[@class='text-white top-space'])[2]")
    public WebElement copyrightText;
    @FindBy(xpath = "(//a[@href='https://web-qa.doxzilla.enveu.com/en/terms-condition'])[2]")
    public WebElement termOfUseQA;
    @FindBy(xpath = "(//a[@href='https://web-uat.doxzilla.com/en/terms-condition'])[2]")
    public WebElement termOfUseUAT;
    @FindBy(xpath = "(//a[@href='https://app.doxzilla.com/en/terms-condition'])[2]")
    public WebElement termOfUsePROD;
    @FindBy(xpath = "(//a[@href='https://web-qa.doxzilla.enveu.com/en/privacy-policy'])[2]")
    public WebElement privacyPolicyQA;
    @FindBy(xpath = "(//a[@href='https://web-uat.doxzilla.com/en/privacy-policy'])[2]")
    public WebElement privacyPolicyUAT;
    @FindBy(xpath = "(//a[@href='https://app.doxzilla.com/en/privacy-policy'])[2]")
    public WebElement privacyPolicyPROD;
    @FindBy(xpath = "//a[@href='https://www.facebook.com/Doxzillaworld']")
    public WebElement connectWithFB;
    @FindBy(xpath = "//a[@href='https://twitter.com/doxzillaworld']")
    public WebElement connectWithTwitter;
    @FindBy(xpath = "//i[@class='fa fa-instagram']")
    public WebElement connectWithInstagram;
    //**************************************After Login **************************************************
    @FindBy(xpath = "//a[@class='userimg-pic']")
    public WebElement UserIMG;
    @FindBy(xpath = "(//a[@class='dropdown-item'])[4]")
    public WebElement AccSettingButton;
    @FindBy(xpath = "(//a[@class='dropdown-item'])[3]")
    public WebElement MyWatchHistory;
    //************************************* Class Level **************************************************
    WebDriver driver;
    WebDriverWait wait;
    //************************************* Onboarding Page **************************************************
    @FindBy(xpath = "(//a[@class='preview-now'])[1]")
    private WebElement previewButton;
    @FindBy(xpath = "//i[@class='fa fa-caret-down rotate down']")
    private WebElement languageDropdown;
    @FindBy(xpath = "(//a[@class='dropdown-item destopViewDropDown'])[2]")
    private WebElement selectENG;
    @FindBy(xpath = "(//a[@class='dropdown-item destopViewDropDown'])[1]")
    private WebElement selectTHAI;
    //*********************************  Header Section ***************************************************
    @FindBy(xpath = "//button[@class='btn btn-pink singin-btn']")
    private WebElement signinButton;

    public Doxzilla_HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void search(String search) throws InterruptedException {
        SearchTextbox.sendKeys(search);
        SearchTextbox.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
    }

    public void viewHome() throws InterruptedException {
        if (previewButton.isDisplayed()) {
            wait.until(ExpectedConditions.elementToBeClickable(previewButton)).click();
            Thread.sleep(3000);
            AcceptCookies();
        }
    }

    public void AcceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookies)).click();
    }

    public void changeLang(String language) throws InterruptedException {
        languageDropdown.click();
        if (language.contains("ENG")) {
            selectENG.click();
            Thread.sleep(5000);
        } else {
            selectTHAI.click();
            Thread.sleep(5000);
        }
    }

    public String verifyHomepage() throws InterruptedException {
        String Title = driver.getTitle();
        Assert.assertEquals(Title, "Doxzilla.com - Know The Unknown");
        Thread.sleep(5000);
        return driver.getCurrentUrl();
    }

    public void verifyHome() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(navHome)).click();
        Thread.sleep(3000);
    }

    public void verifyHotNew() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(navHotNew)).click();
        Thread.sleep(3000);

    }

    public void verifyAllContent() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(navAllContent)).click();
        Thread.sleep(3000);

    }

    public void openMyList() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(navMyList)).click();
        Thread.sleep(3000);
    }

    public void verifyStaticPages() throws InterruptedException {
        SearchTextbox.sendKeys("the");
        SearchTextbox.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(5000);
        String ele = wait.until(ExpectedConditions.visibilityOf(copyrightText)).getText();
        Assert.assertEquals(ele, "©2022 Doxzilla. All Rights Reserved.");
        if (driver.getCurrentUrl().contains("qa")) {
            wait.until(ExpectedConditions.elementToBeClickable(termOfUseQA)).click();
            Thread.sleep(5000);
            wait.until(ExpectedConditions.elementToBeClickable(privacyPolicyQA)).click();
            Thread.sleep(5000);
        } else if (driver.getCurrentUrl().contains("uat")) {
            wait.until(ExpectedConditions.elementToBeClickable(termOfUseUAT)).click();
            Thread.sleep(5000);
            wait.until(ExpectedConditions.elementToBeClickable(privacyPolicyUAT)).click();
            Thread.sleep(5000);
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(termOfUsePROD)).click();
            Thread.sleep(5000);
            wait.until(ExpectedConditions.elementToBeClickable(privacyPolicyPROD)).click();
            Thread.sleep(5000);
        }
    }

    public void verifyConnectWithUs() throws InterruptedException {
        SearchTextbox.sendKeys("the");
        SearchTextbox.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        wait.until(ExpectedConditions.elementToBeClickable(connectWithFB)).click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(connectWithTwitter)).click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(connectWithInstagram)).click();
        Thread.sleep(3000);
        Set<String> handlesSet = driver.getWindowHandles();
        List<String> handlesList = new ArrayList<>(handlesSet);
        driver.switchTo().window(handlesList.get(3));
        Thread.sleep(3000);
        driver.close();
        driver.switchTo().window(handlesList.get(2));
        Thread.sleep(3000);
        driver.close();
        driver.switchTo().window(handlesList.get(1));
        Thread.sleep(3000);
        driver.close();
        driver.switchTo().window(handlesList.get(0));
        Thread.sleep(3000);
    }

    public void verifyDownloadApps() throws InterruptedException {
        SearchTextbox.sendKeys("the");
        SearchTextbox.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(connectWithFB)).click();
        Thread.sleep(3000);
    }

    public void clickSignin() throws InterruptedException {
        Thread.sleep(3000);
        signinButton.click();
    }

    public void openAccountSetting() throws InterruptedException {
        Thread.sleep(3000);
        Actions ac = new Actions(driver);
        ac.moveToElement(UserIMG).click().build().perform();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(AccSettingButton)).click();
        Thread.sleep(3000);
    }

    public void openMyWatchHistory() throws InterruptedException {
        Actions ac = new Actions(driver);
        ac.moveToElement(UserIMG).click().build().perform();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(MyWatchHistory)).click();
        Thread.sleep(3000);
    }
}