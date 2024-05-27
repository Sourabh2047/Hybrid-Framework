package doxzilla_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class Doxzilla_SignupPage {
    public static String email_Address;
    public static String parentEmailAddress = "Auto";
    WebDriverWait wait;
    WebDriver driver;
    @FindBy(xpath = "//h2[text()[normalize-space()='Create a password to start your membership']]")
    private WebElement SignupTitle;
    @FindBy(xpath = "//div[@id='snackbar']")
    private WebElement snakbar;
    //************************************** Signup Page ****************************************
    @FindBy(xpath = "//input[@name='Email']")
    private WebElement EmailTextBox;
    @FindBy(xpath = "//input[@name='Password']")
    private WebElement PasswordTextBox;
    @FindBy(xpath = "//input[@name='Confirm password']")
    private WebElement cnfPasswordTextBox;
    @FindBy(xpath = "//button[@class='btn-box-continue']")
    private WebElement ContinueButton;
    //******************************************* OTP Page ******************************************
    @FindBy(id = "otp-number-input-2")
    private WebElement enterOTPBox;
    @FindBy(xpath = "//button[@class='otp-box-continue']")
    private WebElement page2ContinueButton;
    @FindBy(xpath = "signout-btn")
    private WebElement logout;
    //*********************************************** Choose Package ********************************************
    @FindBy(xpath = "//button[contains(.,'Monthly service fee 99 per month')]")
    private WebElement selectMontlyPackage;
    @FindBy(xpath = "//button[contains(.,'Annual service fee 999 per year')]")
    private WebElement selectAnnualPackage;
    @FindBy(xpath = "//input[@class='form-control form-control-lg apply ng-untouched ng-pristine ng-valid']")
    private WebElement promoCodeTextBox;
    @FindBy(xpath = "btn-apply-continue button-radius")
    private WebElement applyPromoCode;
    @FindBy(xpath = "//button[@id='vouchercode']")
    private WebElement voucherCode;
    @FindBy(xpath = "//input[@class='form-control form-control-lg apply ng-untouched ng-pristine ng-valid']")
    private WebElement voucherCodeTextBox;
    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement tnCcheckbox;
    @FindBy(xpath = "//button[@class='btn-box-continue']")
    private WebElement ContinueToPayButton;
    @FindBy(xpath = "(//a[@class='privacytext'])[2]")
    private WebElement privacyLink;
    @FindBy(xpath = "(//a[@class='privacytext'])[1]")
    private WebElement tncLink;
    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement checkbox3;
    @FindBy(xpath = "//button[@class='btn-box-coupon-continue']")
    private WebElement page4ContinueButton;
    @FindBy(xpath = "//span[@class='resend-otp-p']")
    private WebElement resendOTPButton;
    //*********************************************** Stripe Payment Gateway **************************
    @FindBy(xpath = "//input[@name='cardNumber']")
    private WebElement cardTextBox;
    @FindBy(xpath = "//input[@name='expyear']")
    private WebElement cardExpiry;
    @FindBy(xpath = "//input[@name='cvv']")
    private WebElement cardCVV;
    @FindBy(xpath = "//input[@name='name']")
    private WebElement cardName;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submit;
    @FindBy(xpath = "//input[@name='otp']")
    private WebElement OTP;
    @FindBy(xpath = "//input[@name='continue']")
    private WebElement continueButton;

    public Doxzilla_SignupPage(WebDriver driver) {
        super();
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void clickSignupContinue() {
        ContinueButton.click();
    }

    public void emailTextBox(String email) {
        EmailTextBox.clear();
        EmailTextBox.sendKeys(email);
    }

    public void passTextBox(String pass) {
        PasswordTextBox.clear();
        PasswordTextBox.sendKeys(pass);
    }

    public void cnfPassTextBox(String pass) {
        cnfPasswordTextBox.clear();
        cnfPasswordTextBox.sendKeys(pass);
    }

    public String FillSignupData(String pass, String cnfPass) throws InterruptedException {
        email_Address = parentEmailAddress + Doxzilla_BasePage.getAlphaNumericString(6) + "@yopmail.com";
        EmailTextBox.sendKeys(email_Address);
        PasswordTextBox.sendKeys(pass);
        cnfPasswordTextBox.sendKeys(cnfPass);
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(200,500)");
        ContinueButton.click();
        return email_Address;
    }

    public void FillSignupData1(String email, String pass, String cnfPass) throws InterruptedException {
        EmailTextBox.sendKeys(email);
        PasswordTextBox.sendKeys(pass);
        cnfPasswordTextBox.sendKeys(cnfPass);
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(200,400)");
        ContinueButton.click();
    }

    public String getConfirmationCodeFromYopmail(WebDriver driver) throws InterruptedException {
        Thread.sleep(1000);
        String code = null;
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.navigate().to("https://yopmail.com/en/");
        //if (){} //close ads
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='ycptcpt']"))).click();
        WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='login']")));
        email.sendKeys(email_Address);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//i[@class='material-icons-outlined f36']")).click();
        driver.switchTo().frame("ifmail");
        //logic for checking OTP mail
        String msg = driver.findElement(By.xpath("//div[contains(text(),'Account Verification')]")).getText();
        if (msg.contains("Doxzilla: Account Verification")) {
            code = driver.findElement(By.xpath("//td[contains(text(),'The one-time password')]")).getText();
            code = code.substring(code.indexOf(":") + 2).trim();
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Doxzilla Customer Support')])[2]"))).click();
        }
        Thread.sleep(10000);
        driver.switchTo().window(tabs.get(0));
        Thread.sleep(5000);
        return code;
    }
/*
    public String getConfirmationCodeFromGmail(WebDriver driver) {
        String code = null;
        try {
            ((JavascriptExecutor) driver).executeScript("window.open()");
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            driver.navigate().to("https://accounts.google.com/AccountChooser/signinchooser?service=mail&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&flowName=GlifWebSignIn&flowEntry=AccountChooser");
            WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.id("identifierId")));
            email.sendKeys(parentAddress);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']"))).sendKeys("Sourabh@123#");
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']"))).click();
            Thread.sleep(10000);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Account Verification ')]"))).click();
            code = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'verify your account ')]/parent::td"))).getText();
            code = code.split("The one-time password(OTP) to verify your email on Doxzilla is : ")[1];
            code = code.split("\n")[1];
            driver.switchTo().window(tabs.get(0));
        } catch (NullPointerException e) {
            e.getMessage();
        } catch (InterruptedException e) {
            e.getMessage();
        }
        return code;
    }
 */

    public void getConfirmationForSignUp(WebDriver driver, String code) throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        char[] c = code.substring(0, 6).toCharArray();
        WebElement OTP0 = driver.findElement(By.id("otp-number-input-1"));
        OTP0.click();
        OTP0.clear();
        OTP0.sendKeys(String.valueOf(c[0]));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("otp-number-input-2"))).sendKeys(String.valueOf(c[1]));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("otp-number-input-3"))).sendKeys(String.valueOf(c[2]));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("otp-number-input-4"))).sendKeys(String.valueOf(c[3]));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("otp-number-input-5"))).sendKeys(String.valueOf(c[4]));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("otp-number-input-6"))).sendKeys(String.valueOf(c[5]));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='otp-submit']"))).click();
        Thread.sleep(3000);
    }

    public void FillCardDetails() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='cardNumber']"))).sendKeys("5555 5555 5555 4444");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='cardExpiry']"))).sendKeys("09 / 29");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='cardCvc']"))).sendKeys("123");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='billingName']"))).sendKeys("Sourabh");
        Select objSelect = new Select(driver.findElement(By.id("billingCountry")));
        objSelect.selectByValue("US");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='billingPostalCode']"))).sendKeys("78229");
        WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='SubmitButton-IconContainer']")));
        Actions ac = new Actions(driver);
        ac.moveToElement(ele).click();
        ac.build().perform();
        Thread.sleep(5000);
    }

    public String getTransnactionID() {
        String trID = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='ordertext'])[4]/span"))).getText();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-pink']"))).click();
        return trID;
    }

    public void SelectPlan(String plan) {
        if (plan.contains("MONTHLY")) {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn-box-one btn-box-two']"))).click();
        } else if (plan.contains("ANNUALLY")) {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn-box-one'])[1]"))).click();
        } else if (plan.contains("PROMOCODE")) {
            promoCodeTextBox.sendKeys("");
            wait.until(ExpectedConditions.elementToBeClickable(applyPromoCode)).click();
        } else if (plan.contains("VOUCHER")) {
            wait.until(ExpectedConditions.elementToBeClickable(voucherCode)).click();
            voucherCodeTextBox.sendKeys("");
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='btn-box-one'])[3]"))).click();
        }
    }

    public void proceedToPayment() {
        wait.until(ExpectedConditions.elementToBeClickable(tnCcheckbox)).click();
        wait.until(ExpectedConditions.elementToBeClickable(ContinueToPayButton)).click();

    }

    public String snakbarMessage() {
        wait.until(ExpectedConditions.visibilityOf(snakbar));
        return snakbar.getText();
    }

    public String newAccount() {
        return email_Address;
    }
}