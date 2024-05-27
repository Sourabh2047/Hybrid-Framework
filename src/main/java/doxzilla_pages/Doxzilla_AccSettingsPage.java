package doxzilla_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Doxzilla_AccSettingsPage {
    WebDriverWait wait;
    WebDriver driver;
    @FindBy(xpath = "(//button[@class='btn-pink'])[1]")
    private WebElement openYourDetail;
    @FindBy(xpath = "(//input[@type='text'])[6]")
    private WebElement editName;
    @FindBy(xpath = "(//input[@type='text'])[8]")
    private WebElement editMobile;
    @FindBy(xpath = "(//div[@class='chngpasChange-password']/button)[1]")
    private WebElement saveProfile;
    @FindBy(xpath = "(//button[@class='btn-pink'])[3]")
    private WebElement editPassButton;
    @FindBy(xpath = "(//input[@type='password'])[3]")
    private WebElement NewPass;
    @FindBy(xpath = "(//input[@type='password'])[4]")
    private WebElement CnfPass;
    @FindBy(xpath = "(//button[@class='btn-pink'])[4]")
    private WebElement savePass;
    @FindBy(xpath = "//button[@class='btn-pink orderHistory']/i")
    private WebElement OrderHistoryButton;
    @FindBy(xpath = "(//span[contains(text(),'ACTIVE')])[1]")
    private WebElement ActiveStatus;
    @FindBy(xpath = "(//i[@class='material-icons'])[7]")
    private WebElement BackToAccSetting;
    @FindBy(xpath = "(//button[@class='btn-pink'])[5]")
    private WebElement ManageSubButton;
    @FindBy(xpath = "//button[@class='btn btn-pink back-btn']")
    private WebElement cancelSubNo;
    @FindBy(xpath = "//button[@class='btn btn-pink cancel-btn']")
    private WebElement cancelSubYes;
    @FindBy(xpath = "//button[@id='logoutUser']")
    private WebElement LogoutButton;
    @FindBy(xpath = "(//button[@class='btn-pink'])[9]")
    private WebElement DelButton;
    @FindBy(xpath = "//button[@class='btn btn-pink back-btn']")
    private WebElement Del;
    @FindBy(xpath = "//button[@class='btn btn-pink cancel-btn']")
    private WebElement DelCancel;
    @FindBy(xpath = "//a[@id='ngb-tab-0']/div")
    private WebElement YourAccount;
    @FindBy(xpath = "//a[@id='ngb-tab-1']/div")
    private WebElement VideoQuality;
    @FindBy(xpath = "//input[@value='Medium']")
    private WebElement MediumQuality;
    @FindBy(xpath = "//div[@class='actionbtn']/button[1]")
    private WebElement update;
    @FindBy(xpath = "//a[@id='ngb-tab-2']/div")
    private WebElement ContentPref;
    @FindBy(xpath = "//div[@id='snackbar']")
    private WebElement toastMsg;

    public Doxzilla_AccSettingsPage(WebDriver driver) {
        //super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void openYourDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(openYourDetail)).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(200,400)");
    }

    public void editYourDetails(String name, String mobile) {
        Actions ac = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(200,200)");
        editName.sendKeys(name);
        editMobile.sendKeys(mobile);
        ac.moveToElement(saveProfile).click().build().perform();
    }

    public String getMessage() {
        return wait.until(ExpectedConditions.visibilityOf(toastMsg)).getText();
    }

    public void openChangePass() {
        wait.until(ExpectedConditions.elementToBeClickable(editPassButton)).click();
    }

    public void changePass(String newPass, String cnfPass) {
        NewPass.clear();
        NewPass.sendKeys(newPass);
        CnfPass.clear();
        CnfPass.sendKeys(cnfPass);
        wait.until(ExpectedConditions.elementToBeClickable(savePass)).click();
    }

    public void verifyOrderHistory() throws InterruptedException {

        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("window.scrollBy(0,100)");
        OrderHistoryButton.click();
        Thread.sleep(3000);
        String status = wait.until(ExpectedConditions.visibilityOf(ActiveStatus)).getText();
        Assert.assertTrue(status.contains("ACTIVE"));
        Actions ac = new Actions(driver);
        ac.moveToElement(BackToAccSetting).click();
        Thread.sleep(2000);
    }

    public void ManageSubButton() {
        wait.until(ExpectedConditions.elementToBeClickable(ManageSubButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(cancelSubYes));
    }

    public void logout() {
        Actions ac = new Actions(driver);
        WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(LogoutButton));
        ac.moveToElement(ele);
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("window.scrollBy(0,250)");
        ac.click(ele);
        ac.build().perform();
    }

    public void DelAccButton() throws InterruptedException {
        Actions a = new Actions(driver);
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("window.scrollBy(0,400)");
        Thread.sleep(1000);
        a.moveToElement(DelButton).click();
        a.build().perform();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(Del)).click();
    }

    public void ChangeVideoQuality() throws InterruptedException {
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("window.scrollBy(0,150)");
        Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
        homeObj.AcceptCookies();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(VideoQuality)).click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", VideoQuality);
        WebElement radioBtn1 = driver.findElement(By.xpath("//input[@value='Medium']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", radioBtn1);
        Actions ac = new Actions(driver);
        ac.moveToElement(MediumQuality);
        ac.click();
        ac.build().perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", update);
        Thread.sleep(1000);
    }

    public void ChangeContPref() {
        Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
        homeObj.AcceptCookies();
        wait.until(ExpectedConditions.elementToBeClickable(ContentPref)).click();
        //wait.until(ExpectedConditions.elementToBeClickable(YourAccount)).click();
    }
}