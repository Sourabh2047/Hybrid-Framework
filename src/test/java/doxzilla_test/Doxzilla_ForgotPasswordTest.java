package doxzilla_test;

import browser.TestData;
import com.relevantcodes.extentreports.LogStatus;
import doxzilla_pages.Doxzilla_AccSettingsPage;
import doxzilla_pages.Doxzilla_ForgotPassPage;
import doxzilla_pages.Doxzilla_HomePage;
import doxzilla_pages.Doxzilla_LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import report.Screenshot;

@Test(groups = {"Smoke", "Sanity", "Regression"})
public class Doxzilla_ForgotPasswordTest extends Doxzilla_BaseTest {
    String email_Address = "sourabh47@yopmail.com";

    @DataProvider(name = "Valid_Login")
    public Object[][] getValidCredentials() {
        return new Object[][]{{TestData.getDataValue("Valid_Login", "Email"), TestData.getDataValue("Valid_Login", "Pass")}};
    }

    @Test(priority = 1, groups = {"Sanity", "Regression"}, description =
            "Validation of All Popup's on Forgot Password Page Field's with Invalid Set of Data")
    public void Verify_AllPopupMessages() throws InterruptedException {
        Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
        Doxzilla_LoginPage loginObj = new Doxzilla_LoginPage(driver);
        Doxzilla_ForgotPassPage forgotObj = new Doxzilla_ForgotPassPage(driver);
        homeObj.viewHome();
        //Blank Email TextBox
        Screenshot.logMessage(LogStatus.INFO, "Opening Login Page", "");
        homeObj.clickSignin();
        Screenshot.logMessage(LogStatus.INFO, "Opening Forgot Password Page", "");
        loginObj.clickForgotPass();
        Screenshot.logMessage(LogStatus.INFO, "Leaving Email Blank", "");
        forgotObj.emailTextBox("");
        Thread.sleep(3000);
        forgotObj.submitButton();
        if (driver.getCurrentUrl().contains("th")) {
            String msg = forgotObj.getMessage();
            Assert.assertEquals(msg, "โปรดระบุอีเมล");
            Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
        } else {
            String msg = forgotObj.getMessage();
            Assert.assertEquals(msg, "Please enter Email");
            Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
        }
        Thread.sleep(6000);
        //Invalid Email
        Screenshot.logMessage(LogStatus.INFO, "Entering Invalid Email", "");
        forgotObj.emailTextBox("some@s@s.c.om");
        forgotObj.submitButton();
        if (driver.getCurrentUrl().contains("th")) {
            String msg = forgotObj.getMessage();
            Assert.assertEquals(msg, "อีเมลไม่ถูกต้อง");
            Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
        } else {
            String msg = forgotObj.getMessage();
            Assert.assertEquals(msg, "Invalid Email");
            Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
        }
        Thread.sleep(6000);
        //Random Email Address
        Screenshot.logMessage(LogStatus.INFO, "Entering Random Email", "");
        forgotObj.emailTextBox("Some@gmail.com");
        Thread.sleep(3000);
        forgotObj.submitButton();
        if (driver.getCurrentUrl().contains("th")) {
            String msg = forgotObj.getMessage();
            Assert.assertEquals(msg, "ระบบจะส่งข้อความไปยังอีเมล์ของท่านเพื่อทำการยืนยันตัวตน กรุณาตั้งค่ารหัสผ่านใหม่เพื่อเข้าสู่ระบบอีกครั้ง ข้อความที่ตอบกลับไปอาจจะอยู่ในแฟ้มจดหมาย Spam หรือ Junk ได้ กรุณาตรวจสอบในแฟ้มดังกล่าว");
            Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
        } else {
            String msg = forgotObj.getMessage();
            Assert.assertEquals(msg, "Thanks for putting the request. If we find the email address in our system, we will send the reset link over the mail");
            Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
        }
        Thread.sleep(1000);
    }

    @Test(priority = 2, dataProvider = "Valid_Login", groups = {"Sanity", "Regression"}, description = "" +
            "Validation of Forgot Password TextBox with Valid Registered, Email.")
    public void RESETPASS_E2E_Test(String email, String pass) throws InterruptedException {
        Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
        Doxzilla_LoginPage loginObj = new Doxzilla_LoginPage(driver);
        Doxzilla_AccSettingsPage accObj = new Doxzilla_AccSettingsPage(driver);
        Doxzilla_ForgotPassPage forgotObj = new Doxzilla_ForgotPassPage(driver);
        homeObj.viewHome();
        Screenshot.logMessage(LogStatus.INFO, "Opening Signup Page", "");
        homeObj.clickSignin();
        Screenshot.logMessage(LogStatus.INFO, "Opening Forgot Password Page", "");
        loginObj.clickForgotPass();
        forgotObj.emailTextBox(email_Address);
        Screenshot.logMessage(LogStatus.INFO, "Requesting Reset Link", "");
        forgotObj.submitButton();
        Screenshot.logMessage(LogStatus.INFO, "Opening Yopmail Email Page", "");
        forgotObj.getResetPasswordLinkFromMail(email_Address);
        Screenshot.logMessage(LogStatus.INFO, "Creating New Password", "");
        forgotObj.setPassword(pass, pass);
        Screenshot.logMessage(LogStatus.INFO, "Opening Login Page", "");
        forgotObj.openSignin();
        Screenshot.logMessage(LogStatus.INFO, "Login After Resetting Password", "Email: " + email_Address);
        loginObj.loginTest(email_Address, pass);
        Screenshot.logMessage(LogStatus.INFO, "Login Success, Opening Account & Settings", "");
        homeObj.openAccountSetting();
        Screenshot.logMessage(LogStatus.INFO, "Logging Out", "");
        accObj.logout();
        Thread.sleep(1000);
    }
}