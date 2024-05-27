package doxzilla_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Doxzilla_LoginPage {
    WebDriverWait wait;
    WebDriver driver;
    @FindBy(xpath = "//input[@name='email']")
    private WebElement EmailTextBox;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement PasswordTextBox;
    @FindBy(xpath = "//button[@class='signin-botton']")
    private WebElement SigninSubmit;
    @FindBy(xpath = "//div[@id='snackbar']")
    private WebElement Snakbar;
    @FindBy(xpath = "//span[@class='forgetpass']")
    private WebElement ForgotPassLink;
    @FindBy(xpath = "//span[@class='signup']")
    private WebElement SignupLink;

    public Doxzilla_LoginPage(WebDriver driver) {
        super();
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void clickForgotPass() {
        wait.until(ExpectedConditions.elementToBeClickable(ForgotPassLink)).click();
    }

    public void clickSignupLink() {
        wait.until(ExpectedConditions.elementToBeClickable(SignupLink)).click();
    }

    public void signInSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(SigninSubmit)).click();
    }

    public void loginTest(String username, String password) throws InterruptedException {
        EmailTextBox.sendKeys(username);
        PasswordTextBox.sendKeys(password);
        Thread.sleep(1000);
        signInSubmit();
    }

    public void enterEmail(String username) {
        EmailTextBox.clear();
        EmailTextBox.sendKeys(username);
    }

    public void enterPassword(String pwd) {
        PasswordTextBox.clear();
        PasswordTextBox.sendKeys(pwd);
    }

    public String snakbarMessage() {
        wait.until(ExpectedConditions.visibilityOf(Snakbar));
        return Snakbar.getText();
    }
}