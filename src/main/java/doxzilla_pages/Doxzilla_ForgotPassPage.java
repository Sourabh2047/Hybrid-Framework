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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Doxzilla_ForgotPassPage {
    WebDriverWait wait;
    WebDriver driver;
    //************************************ Forgot Password Page **************************************
    @FindBy(xpath = "//span[@class='sign']")
    private WebElement resetPassSigninButton;
    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailTextBox;
    @FindBy(xpath = "//button[@class='forget-botton']")
    private WebElement continueButton;
    @FindBy(xpath = "//div[@id='snackbar']")
    private WebElement snakbarMsg;
    //************************************ Set New Password Page **************************************
    @FindBy(xpath = "//input[@id='changePassword']")
    private WebElement NewPassTextBox;
    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement CnfPassTextBox;
    @FindBy(xpath = "//button[@type='btn btn-green']")
    private WebElement ResetButton;

    public Doxzilla_ForgotPassPage(WebDriver driver) {
        super();
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void emailTextBox(String email) {
        emailTextBox.clear();
        wait.until(ExpectedConditions.elementToBeClickable(emailTextBox)).click();
        wait.until(ExpectedConditions.visibilityOf(emailTextBox)).sendKeys(email);
    }

    public void openSignin() {
        resetPassSigninButton.click();
    }

    public void submitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public String getMessage() {
        return wait.until(ExpectedConditions.visibilityOf(snakbarMsg)).getText();
    }

    public void getResetPasswordLinkFromMail(String email) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.navigate().to("https://yopmail.com/en/");
        //if (){} //close ads
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='ycptcpt']"))).click();
        WebElement enterEmail = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='login']")));
        enterEmail.sendKeys(email);
        driver.findElement(By.xpath("//i[@class='material-icons-outlined f36']")).click();
        Thread.sleep(3000);
        driver.switchTo().frame("ifmail");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Reset')]"))).click();
        Thread.sleep(3000);
        Set<String> handlesSet = driver.getWindowHandles();
        List<String> handlesList = new ArrayList<>(handlesSet);
        Thread.sleep(3000);
        driver.switchTo().window(handlesList.get(2));
        wait.until(ExpectedConditions.visibilityOf(NewPassTextBox));
    }

    public void setPassword(String pass, String cnfpass) throws InterruptedException {
        Actions ac = new Actions(driver);
        NewPassTextBox.sendKeys(pass);
        CnfPassTextBox.sendKeys(cnfpass);
        Thread.sleep(2000);
        ac.moveToElement(ResetButton).click().build().perform();
        if (driver.getCurrentUrl().contains("th")) {
            String msg = getMessage();
            Assert.assertEquals(msg, "อัปเดตสำเร็จ");
        } else {
            String msg = getMessage();
            Assert.assertEquals(msg, "Password Changed");
        }
        Set<String> handlesSet = driver.getWindowHandles();
        List<String> handlesList = new ArrayList<>(handlesSet);
        Thread.sleep(1000);
        driver.switchTo().window(handlesList.get(0));
    }
}