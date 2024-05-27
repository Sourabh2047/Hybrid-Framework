package doxzilla_test;

import browser.TestData;
import com.relevantcodes.extentreports.LogStatus;
import doxzilla_pages.Doxzilla_AccSettingsPage;
import doxzilla_pages.Doxzilla_HomePage;
import doxzilla_pages.Doxzilla_LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import report.Screenshot;

@Test(groups = {"Smoke", "Sanity", "Regression"})
public class Doxzilla_LoginTest extends Doxzilla_BaseTest {
    @DataProvider(name = "Valid_Login")
    public Object[][] getValidCredentials() {
        return new Object[][]{{TestData.getDataValue("Valid_Login", "Email"),
                TestData.getDataValue("Valid_Login", "Pass")}};
    }

    @Test(priority = 3, dataProvider = "Valid_Login", groups = {"Sanity", "Regression"}, description =
            "Valid_LoginTest: Verify & Validate User is able to Login with Valid Credentials or not")
    public void LOGIN_Valid_LoginTest(String email, String pass) throws InterruptedException {
        Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
        Doxzilla_LoginPage loginObj = new Doxzilla_LoginPage(driver);
        Doxzilla_AccSettingsPage accObj = new Doxzilla_AccSettingsPage(driver);
        Thread.sleep(5000);
        homeObj.viewHome();
        Screenshot.logMessage(LogStatus.INFO, "Opening Login Page", "");
        homeObj.clickSignin();
        Screenshot.logMessage(LogStatus.INFO, "Entering Valid Credentials", "");
        loginObj.loginTest(email, pass);
        Thread.sleep(5000);
        Screenshot.logMessage(LogStatus.INFO, "Login Success, Opening Account & Settings", "");
        homeObj.openAccountSetting();
        Screenshot.logMessage(LogStatus.INFO, "Validating Account Details", "");
        accObj.openYourDetails();
        Screenshot.logMessage(LogStatus.INFO, "Logging Out", "");
        accObj.logout();
    }

    @Test(priority = 2, groups = {"Sanity"}, description =
            "InValid_LoginTest: Check if user is able to login with invalid credential or not")
    public void LOGIN_Invalid_LoginTest() throws InterruptedException {
        Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
        Doxzilla_LoginPage loginObj = new Doxzilla_LoginPage(driver);
        Thread.sleep(5000);
        homeObj.viewHome();
        Screenshot.logMessage(LogStatus.INFO, "Opening Login Page", "");
        homeObj.clickSignin();
        Screenshot.logMessage(LogStatus.INFO, "Entering Invalid Credentials", "");
        loginObj.enterEmail("sanjay@enveu.com");
        loginObj.enterPassword("1234567890");
        loginObj.signInSubmit();
        Thread.sleep(5000);
        if (driver.getCurrentUrl().contains("th")) {
            String msg = loginObj.snakbarMessage();
            Assert.assertEquals(msg, "ชื่อผู้ใช้หรือรหัสผ่านไม่ตรงกัน");
            Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
        } else {
            String msg = loginObj.snakbarMessage();
            Assert.assertEquals(msg, "Username or Password doesn't match.");
            Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
        }
    }

    @Test(priority = 1, groups = {"Sanity"}, description =
            "Verification of All Popup's Messages on Login Page, Validating all Field's with Invalid Set of Data")
    public void LOGIN_Verify_AllPopupMessages() throws InterruptedException {
        Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
        Doxzilla_LoginPage loginObj = new Doxzilla_LoginPage(driver);
        Thread.sleep(5000);
        homeObj.viewHome();
        Screenshot.logMessage(LogStatus.INFO, "Opening Login Page", "");
        homeObj.clickSignin();
        //Blank Email TextBox
        Screenshot.logMessage(LogStatus.INFO, "Entering Invalid Credentials", "");
        loginObj.enterEmail("");
        loginObj.enterPassword("1234567");
        loginObj.signInSubmit();
        Thread.sleep(3000);
        if (driver.getCurrentUrl().contains("th")) {
            String msg = loginObj.snakbarMessage();
            Assert.assertEquals(msg, "รหัสอีเมล์ไม่รองรับเว้นวรรค");
            Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
        } else {
            String msg = loginObj.snakbarMessage();
            Assert.assertEquals(msg, "Please enter Email");
            Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
        }
        Thread.sleep(6000);
        //Invalid Email TextBox
        Screenshot.logMessage(LogStatus.INFO, "Entering Invalid Credentials", "");
        loginObj.enterEmail("s@s@h.com");
        loginObj.enterPassword("1234567");
        loginObj.signInSubmit();
        Thread.sleep(3000);
        if (driver.getCurrentUrl().contains("th")) {
            String msg = loginObj.snakbarMessage();
            Assert.assertEquals(msg, "อีเมลไม่ถูกต้อง");
            Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
        } else {
            String msg = loginObj.snakbarMessage();
            Assert.assertEquals(msg, "Invalid Email");
            Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
        }
        Thread.sleep(6000);
        // Valid Email but Blank Password
        Screenshot.logMessage(LogStatus.INFO, "Entering Invalid Credentials", "");
        loginObj.enterEmail("sanjay@enveu.com");
        loginObj.enterPassword("");
        loginObj.signInSubmit();
        Thread.sleep(3000);
        if (driver.getCurrentUrl().contains("th")) {
            String msg = loginObj.snakbarMessage();
            Assert.assertEquals(msg, "รหัสผ่านไม่รองรับเว้นวรรค");
            Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
        } else {
            String msg = loginObj.snakbarMessage();
            Assert.assertEquals(msg, "Please enter Password");
            Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
        }
        Thread.sleep(6000);
        //Valid Email Wrong Password
        Screenshot.logMessage(LogStatus.INFO, "Entering Invalid Credentials", "");
        loginObj.enterEmail("sanjay@enveu.com");
        loginObj.enterPassword("123");
        loginObj.signInSubmit();
        Thread.sleep(3000);
        if (driver.getCurrentUrl().contains("th")) {
            String msg = loginObj.snakbarMessage();
            Assert.assertEquals(msg, "โปรดป้อนรหัสผ่านระหว่าง 6 ถึง 20");
            Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
        } else {
            String msg = loginObj.snakbarMessage();
            Assert.assertEquals(msg, "Please enter a password between 6 to 20 characters");
            Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
        }
        Thread.sleep(6000);
        // Account Doesn't Exist
        Screenshot.logMessage(LogStatus.INFO, "Entering Invalid Credentials", "");
        loginObj.enterEmail("vikram@enveu.com");
        loginObj.enterPassword("1234567");
        loginObj.signInSubmit();
        Thread.sleep(3000);
        if (driver.getCurrentUrl().contains("th")) {
            String msg = loginObj.snakbarMessage();
            Assert.assertEquals(msg, "ไม่พบอีเมลของคุณในระบบ");
            Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
        } else {
            String msg = loginObj.snakbarMessage();
            Assert.assertEquals(msg, "Username or Password doesn't match.");
            Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
        }
    }
}