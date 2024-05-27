package doxzilla_test;

import browser.TestData;
import com.relevantcodes.extentreports.LogStatus;
import doxzilla_pages.Doxzilla_AccSettingsPage;
import doxzilla_pages.Doxzilla_HomePage;
import doxzilla_pages.Doxzilla_LoginPage;
import doxzilla_pages.Doxzilla_SignupPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import report.Screenshot;

public class Doxzilla_SignupTest extends Doxzilla_BaseTest {
    @DataProvider(name = "Valid_Signup")
    public Object[][] getValidCredentials() {
        return new Object[][]{{TestData.getDataValue("Valid_Signup", "Name"), TestData.getDataValue("Valid_Signup", "Email"),
                TestData.getDataValue("Valid_Signup", "Pass"), TestData.getDataValue("Valid_Signup", "CnfPass"),}};
    }

    /*
        @Test(priority = 1, dataProvider = "Valid_Signup", groups = {"Sanity", "Regression"}, description =
                "Verification of All Popup's Messages on Signup Page, Validating all Field's with Invalid Set of Data")
        public void SIGNUP_VerifyAllPopup(String name, String email, String pass, String cnfpass) throws InterruptedException {
            Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
            Doxzilla_LoginPage loginObj = new Doxzilla_LoginPage(driver);
            Doxzilla_SignupPage signupObj = new Doxzilla_SignupPage(driver);
            homeObj.viewHome();
            homeObj.clickSignin();
            loginObj.clickSignupLink();
            //Blank Name
            signupObj.clickSignupContinue();
            if (driver.getCurrentUrl().contains("th")) {
                String msg = signupObj.snakbarMessage();
                Assert.assertEquals(msg, "โปรดระบุอีเมล");
                DoxzillaScreenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            } else {
                String msg = signupObj.snakbarMessage();
                Assert.assertEquals(msg, "Please enter Email");
                DoxzillaScreenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            }
            Thread.sleep(6000);
            //Valid Name, Blank Email
            signupObj.clickSignupContinue();
            if (driver.getCurrentUrl().contains("th")) {
                String msg = signupObj.snakbarMessage();
                Assert.assertEquals(msg, "โปรดระบุอีเมล");
                DoxzillaScreenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            } else {
                String msg = signupObj.snakbarMessage();
                Assert.assertEquals(msg, "Please enter Email");
                DoxzillaScreenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            }
            Thread.sleep(6000);
            //Valid Name, Invalid Email
            signupObj.emailTextBox("q");
            signupObj.clickSignupContinue();
            if (driver.getCurrentUrl().contains("th")) {
                String msg = signupObj.snakbarMessage();
                Assert.assertEquals(msg, "อีเมลไม่ถูกต้อง");
                DoxzillaScreenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            } else {
                String msg = signupObj.snakbarMessage();
                Assert.assertEquals(msg, "Invalid Email");
                DoxzillaScreenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            }
            Thread.sleep(6000);
            // Valid Name, Valid Email, Blank Password
            signupObj.emailTextBox(email);
            signupObj.clickSignupContinue();
            if (driver.getCurrentUrl().contains("th")) {
                String msg = signupObj.snakbarMessage();
                Assert.assertEquals(msg, "กรุณาใส่รหัสผ่าน");
                DoxzillaScreenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            } else {
                String msg = signupObj.snakbarMessage();
                Assert.assertEquals(msg, "Please enter Password");
                DoxzillaScreenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            }
            Thread.sleep(6000);
            //Valid Name, Valid Email, Invalid Password
            signupObj.emailTextBox(email);
            signupObj.passTextBox("12");
            signupObj.clickSignupContinue();
            if (driver.getCurrentUrl().contains("th")) {
                String msg = signupObj.snakbarMessage();
                Assert.assertEquals(msg, "ยืนยันรหัสผ่านต้องไม่เว้นว่าง");
                DoxzillaScreenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            } else {
                String msg = signupObj.snakbarMessage();
                Assert.assertEquals(msg, "Please enter a password between 6 to 20 characters");
                DoxzillaScreenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            }
            Thread.sleep(6000);
            //Valid Name, Valid Email, Valid Password, Blank CNF Password
            signupObj.emailTextBox(email);
            signupObj.passTextBox(pass);
            signupObj.clickSignupContinue();
            if (driver.getCurrentUrl().contains("th")) {
                String msg = signupObj.snakbarMessage();
                Assert.assertEquals(msg, "ยืนยันรหัสผ่านต้องไม่เว้นว่าง");
                DoxzillaScreenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            } else {
                String msg = signupObj.snakbarMessage();
                Assert.assertEquals(msg, "Please enter Confirm Password");
                DoxzillaScreenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            }
            Thread.sleep(6000);
            //Valid Name, Valid Email, Valid Password, Invalid CNF Password
            signupObj.emailTextBox(email);
            signupObj.passTextBox(pass);
            signupObj.cnfPassTextBox("Test@2312");
            signupObj.clickSignupContinue();
            if (driver.getCurrentUrl().contains("th")) {
                String msg = signupObj.snakbarMessage();
                Assert.assertEquals(msg, "กรุณากรอกรหัสให้ตรงกัน");
                DoxzillaScreenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            } else {
                String msg = signupObj.snakbarMessage();
                Assert.assertEquals(msg, "Confirm Password doesn't match");
                DoxzillaScreenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            }
            Thread.sleep(2000);
        }

        @Test(priority = 2, dataProvider = "Valid_Signup", groups = {"Sanity", "Regression"}, description =
                "Validation of Signup Email TextBox Field with Invalid Email address")
        public void SIGNUP_DuplicateAccount(String name, String email, String pass, String cnfpass) throws InterruptedException {
            Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
            Doxzilla_LoginPage loginObj = new Doxzilla_LoginPage(driver);
            Doxzilla_SignupPage signupObj = new Doxzilla_SignupPage(driver);
            homeObj.viewHome();
            homeObj.clickSignin();
            loginObj.clickSignupLink();
            signupObj.FillSignupData1("sourabh.kedar@enveu.com", pass, cnfpass);
            if (driver.getCurrentUrl().contains("th")) {
                String msg = signupObj.snakbarMessage();
                Assert.assertEquals(msg, "ผู้ใช้งานมีบัญชีที่ใช้อีเมลหรือข้อมูลรับรองนี้แล้ว");
                DoxzillaScreenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            } else {
                String msg = signupObj.snakbarMessage();
                Assert.assertEquals(msg, "User already has an account with this email or credential.");
                DoxzillaScreenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            }
            Thread.sleep(2000);
        }
    */
    @Test(priority = 3, dataProvider = "Valid_Signup", groups = {"Sanity", "Regression"}, description =
            "End-to-end Signup Test with Valid Set of Data")
    public void SIGNUP_E2E_Flow1Test(String name, String email, String pass, String cnfPass) throws InterruptedException {
        Screenshot.logMessage(LogStatus.INFO, "Test Case Started", "");
        Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
        Doxzilla_LoginPage loginObj = new Doxzilla_LoginPage(driver);
        Doxzilla_SignupPage signupObj = new Doxzilla_SignupPage(driver);
        Doxzilla_AccSettingsPage accObj = new Doxzilla_AccSettingsPage(driver);
        Screenshot.logMessage(LogStatus.INFO, "Opening Doxzilla Website", "");
        homeObj.viewHome();
        Screenshot.logMessage(LogStatus.INFO, "Opening Login Page", "");
        homeObj.clickSignin();
        Screenshot.logMessage(LogStatus.INFO, "Opening Signup Page", "");
        loginObj.clickSignupLink();
        Screenshot.logMessage(LogStatus.INFO, "Filling Up Signup Data", "");
        signupObj.FillSignupData(pass, cnfPass);
        String code = signupObj.getConfirmationCodeFromYopmail(driver);
        Screenshot.logMessage(LogStatus.INFO, "Retrieving Code from Email", "");
        signupObj.getConfirmationForSignUp(driver, code);
        Thread.sleep(5000);
        Screenshot.logMessage(LogStatus.INFO, "Selecting Plan", "");
        signupObj.SelectPlan("uat");
        signupObj.proceedToPayment();
        Screenshot.logMessage(LogStatus.INFO, "Filling Up Card Details", "");
        signupObj.FillCardDetails();
        String transactionID = signupObj.getTransnactionID();
        Screenshot.logMessage(LogStatus.INFO, "Payment is Completed, Transaction ID", transactionID);
        Screenshot.logMessage(LogStatus.INFO, "Opening Account & Settings", "");
        homeObj.openAccountSetting();
        Screenshot.logMessage(LogStatus.INFO, "Logging-Out", "");
        accObj.logout();
    }

    @Test(priority = 4, groups = {"Sanity", "Regression"}, description = "End-to-end Signup Test")
    public void SIGNUP_E2E_Flow2Test() {
        Assert.assertEquals("Not Implemented Yet", "Pass");
    }
}