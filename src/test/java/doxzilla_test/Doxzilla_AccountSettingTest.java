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

import static doxzilla_pages.Doxzilla_SignupPage.email_Address;

public class Doxzilla_AccountSettingTest extends Doxzilla_BaseTest {

    @DataProvider(name = "Valid_Login")
    public Object[][] getValidCredentials() {
        return new Object[][]{{TestData.getDataValue("Valid_Login", "Email"),
                TestData.getDataValue("Valid_Login", "Pass")}};
    }

    @DataProvider(name = "ChangePass")
    public Object[][] getPassCredentials() {
        return new Object[][]{{TestData.getDataValue("ChangePass", "newPass"),
                TestData.getDataValue("ChangePass", "cnfPass")}};
    }

    @Test(priority = 1, dataProvider = "Valid_Login", groups = {"Sanity", "Regression"}, description =
            "Verification & Validation of Your Detail : Check user able to edit his account details or not")
    public void AS_EditYourDetails_Test(String email, String pass) throws InterruptedException {
        Screenshot.logMessage(LogStatus.INFO, "Test Case Started", "");
        Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
        Doxzilla_LoginPage loginObj = new Doxzilla_LoginPage(driver);
        Doxzilla_AccSettingsPage accObj = new Doxzilla_AccSettingsPage(driver);
        Thread.sleep(5000);
        homeObj.viewHome();
        Screenshot.logMessage(LogStatus.INFO, "Opening Login Page", "");
        homeObj.clickSignin();
        Screenshot.logMessage(LogStatus.INFO, "Entering Valid Credentials", "");
        loginObj.loginTest(email_Address, pass);
        Thread.sleep(5000);
        Screenshot.logMessage(LogStatus.INFO, "Login Success, Opening Account & Settings", "");
        homeObj.openAccountSetting();
        Screenshot.logMessage(LogStatus.INFO, "Opening Account & Settings Page", "");
        accObj.openYourDetails();
        accObj.editYourDetails("Automation", "");
        Screenshot.logMessage(LogStatus.INFO, "Validating Account Details", "");
        Screenshot.logMessage(LogStatus.INFO, "Logging Out", "");
        accObj.logout();
    }

    @Test(priority = 2, dataProvider = "Valid_Login", groups = {"Sanity", "Regression"}, description =
            "Verification of All Popup's on Change Password Page Field's with Invalid Set of Data")
    public void AS_ChangePass_PopupMsg(String email, String pass) throws InterruptedException {
        {
            Screenshot.logMessage(LogStatus.INFO, "Test Case Started", "");
            Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
            Doxzilla_LoginPage loginObj = new Doxzilla_LoginPage(driver);
            Doxzilla_AccSettingsPage accObj = new Doxzilla_AccSettingsPage(driver);
            Screenshot.logMessage(LogStatus.INFO, "Opening Vipa Website", "");
            homeObj.viewHome();
            homeObj.clickSignin();
            Screenshot.logMessage(LogStatus.INFO, "Logging-In", "");
            loginObj.loginTest(email_Address, pass);
            Screenshot.logMessage(LogStatus.INFO, "Opening Account & Settings", "");
            homeObj.openAccountSetting();
            accObj.openChangePass();
            accObj.changePass("", "");
            if (driver.getCurrentUrl().contains("th")) {
                String msg = accObj.getMessage();
                Assert.assertEquals(msg, "กรุณากรอกพาสเวิร์ดใหม่");
                Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            } else {
                String msg = accObj.getMessage();
                Assert.assertEquals(msg, "Please enter New Password");
                Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            }
            Thread.sleep(6000);
            //Password not as per regex
            accObj.changePass("123", "");
            if (driver.getCurrentUrl().contains("th")) {
                String msg = accObj.getMessage();
                Assert.assertEquals(msg, "โปรดป้อนรหัสผ่านระหว่าง 6 ถึง 20 อักขระ");
                Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            } else {
                String msg = accObj.getMessage();
                Assert.assertEquals(msg, "Please enter a password between 6 to 20 characters.");
                Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            }
            Thread.sleep(6000);
            // Blank CNF Password
            accObj.changePass("123456", "");
            if (driver.getCurrentUrl().contains("th")) {
                String msg = accObj.getMessage();
                Assert.assertEquals(msg, "ชื่อผู้ใช้หรือรหัสผ่านไม่ตรงกัน");
                Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            } else {
                String msg = accObj.getMessage();
                Assert.assertEquals(msg, "Please enter Confirm Password");
                Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            }
            Thread.sleep(6000);
            /// Wrong CNF Password
            accObj.changePass("123456", "123976");
            if (driver.getCurrentUrl().contains("th")) {
                String msg = accObj.getMessage();
                Assert.assertEquals(msg, "กรุณากรอกรหัสให้ตรงกัน");
                Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            } else {
                String msg = accObj.getMessage();
                Assert.assertEquals(msg, "Confirm Password doesn't match");
                Screenshot.logMessage(LogStatus.INFO, "Validating Error Message", msg);
            }
            Screenshot.logMessage(LogStatus.INFO, "Logging-Out", "");
            accObj.logout();
        }
    }

    @Test(priority = 3, dataProvider = "ChangePass", groups = {"Sanity", "Regression"}, description = "Validation of Change Password")
    public void AS_ChangePass_Test(String newPass, String cnfPass) throws InterruptedException {
        Screenshot.logMessage(LogStatus.INFO, "Test Case Started", "");
        Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
        Doxzilla_LoginPage loginObj = new Doxzilla_LoginPage(driver);
        Doxzilla_AccSettingsPage accObj = new Doxzilla_AccSettingsPage(driver);
        Screenshot.logMessage(LogStatus.INFO, "Opening Vipa Website", "");
        homeObj.viewHome();
        homeObj.clickSignin();
        Screenshot.logMessage(LogStatus.INFO, "Logging-In", "");
        loginObj.loginTest(email_Address, newPass);
        Screenshot.logMessage(LogStatus.INFO, "Opening Account & Settings", "");
        homeObj.openAccountSetting();
        Screenshot.logMessage(LogStatus.INFO, "Changing Password", "");
        accObj.openChangePass();
        accObj.changePass(newPass, cnfPass);
        String msg = accObj.getMessage();
        Assert.assertEquals(msg, "Updated Successfully");
        Screenshot.logMessage(LogStatus.INFO, "Password Changed", msg);
        Screenshot.logMessage(LogStatus.INFO, "Logging-Out", "");
        accObj.logout();
        // Login with new Pass
        homeObj.clickSignin();
        Screenshot.logMessage(LogStatus.INFO, "Logging-In with New Password", "");
        loginObj.loginTest(email_Address, newPass);
        Screenshot.logMessage(LogStatus.INFO, "Opening Account & Settings", "");
        homeObj.openAccountSetting();
        Screenshot.logMessage(LogStatus.INFO, "Logging-Out", "");
        accObj.logout();
    }

    @Test(priority = 4, dataProvider = "ChangePass", groups = {"Sanity", "Regression"}, description =
            "Verifying & Validating Order History : Check if user have an Active Plan and it's displaying in Order History Listing or not.")
    public void AS_OrderHistory_Test(String newPass, String cnfPass) throws InterruptedException {
        Screenshot.logMessage(LogStatus.INFO, "Test Case Started", "");
        Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
        Doxzilla_LoginPage loginObj = new Doxzilla_LoginPage(driver);
        Doxzilla_AccSettingsPage accObj = new Doxzilla_AccSettingsPage(driver);
        Screenshot.logMessage(LogStatus.INFO, "Opening Vipa Website", "");
        homeObj.viewHome();
        homeObj.clickSignin();
        Screenshot.logMessage(LogStatus.INFO, "Logging-In", "");
        loginObj.loginTest(email_Address, newPass);
        Screenshot.logMessage(LogStatus.INFO, "Opening Account & Settings", "");
        homeObj.openAccountSetting();
        Screenshot.logMessage(LogStatus.INFO, "Opening Order History", "");
        accObj.verifyOrderHistory();
        Screenshot.logMessage(LogStatus.INFO, "Validating Active Plan Status", "");
    }

    @Test(priority = 5, dataProvider = "ChangePass", groups = {"Sanity", "Regression"}, description =
            "Validation of Cancel Subscription : Check user is able to Cancel Active Subscription Plan from his account setting or not")
    public void AS_ManageSub_Test(String newPass, String cnfPass) throws InterruptedException {
        Screenshot.logMessage(LogStatus.INFO, "Test Case Started", "");
        Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
        Doxzilla_LoginPage loginObj = new Doxzilla_LoginPage(driver);
        Doxzilla_AccSettingsPage accObj = new Doxzilla_AccSettingsPage(driver);
        Screenshot.logMessage(LogStatus.INFO, "Opening Vipa Website", "");
        homeObj.viewHome();
        homeObj.clickSignin();
        Screenshot.logMessage(LogStatus.INFO, "Logging-In", "");
        loginObj.loginTest(email_Address, newPass);
        Screenshot.logMessage(LogStatus.INFO, "Opening Account & Settings", "");
        homeObj.openAccountSetting();
        Screenshot.logMessage(LogStatus.INFO, "Cancelling Subscription", "");
        accObj.ManageSubButton();
        Screenshot.logMessage(LogStatus.INFO, "Logging-Out", "");
        accObj.logout();
    }

    @Test(priority = 6, groups = {"Sanity", "Regression"}, dataProvider = "Valid_Login", description =
            "Verification & Validation of Content Preferences : Check user is able to set/change his account preferences or not")
    public void AS_ContentPref_Test(String email, String pass) throws InterruptedException {
        {
            Screenshot.logMessage(LogStatus.INFO, "Test Case Started", "");
            Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
            Doxzilla_LoginPage loginObj = new Doxzilla_LoginPage(driver);
            Screenshot.logMessage(LogStatus.INFO, "Opening Vipa Website", "");
            homeObj.viewHome();
            homeObj.clickSignin();
            Screenshot.logMessage(LogStatus.INFO, "Logging-In", "");
            loginObj.loginTest(email_Address, pass);
            Screenshot.logMessage(LogStatus.INFO, "Opening Account & Settings", "");
            homeObj.openAccountSetting();
            Assert.assertEquals("Not Yet Implemented", "Pass");
        }
    }

    @Test(priority = 7, groups = {"Sanity", "Regression"}, dataProvider = "Valid_Login", description =
            "Verification & Validation of Video Quality : Check user is able to Set Video Quality from Setting or not")
    public void AS_VideoQuality_Test(String email, String pass) throws InterruptedException {
        {
            Screenshot.logMessage(LogStatus.INFO, "Test Case Started", "");
            Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
            Doxzilla_LoginPage loginObj = new Doxzilla_LoginPage(driver);
            Screenshot.logMessage(LogStatus.INFO, "Opening Vipa Website", "");
            homeObj.viewHome();
            homeObj.clickSignin();
            Screenshot.logMessage(LogStatus.INFO, "Logging-In", "");
            loginObj.loginTest(email_Address, pass);
            Screenshot.logMessage(LogStatus.INFO, "Opening Account & Settings", "");
            homeObj.openAccountSetting();
            Assert.assertEquals("Not Yet Implemented", "Pass");
        }
    }

    @Test(priority = 8, groups = {"Sanity", "Regression"}, dataProvider = "Valid_Login", description =
            "Verification & Validation of MyFav : Check User able to Add or Remove Contents in My Watch History or not")
    public void AS_MyWatchHistory_Test(String email, String pass) throws InterruptedException {
        {
            Screenshot.logMessage(LogStatus.INFO, "Test Case Started", "");
            Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
            Doxzilla_LoginPage loginObj = new Doxzilla_LoginPage(driver);
            Screenshot.logMessage(LogStatus.INFO, "Opening Vipa Website", "");
            homeObj.viewHome();
            homeObj.clickSignin();
            Screenshot.logMessage(LogStatus.INFO, "Logging-In", "");
            loginObj.loginTest(email_Address, pass);
            Screenshot.logMessage(LogStatus.INFO, "Opening My Watch History", "");
            homeObj.openMyWatchHistory();
            Assert.assertEquals("Not Yet Implemented", "Pass");
        }
    }

    @Test(priority = 9, dataProvider = "ChangePass", groups = {"Sanity", "Regression"}, description = "Validation of Delete Account")
    public void AS_AccountDeletion_Test(String newPass, String cnfPass) throws InterruptedException {
        Screenshot.logMessage(LogStatus.INFO, "Test Case Started", "");
        Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
        Doxzilla_LoginPage loginObj = new Doxzilla_LoginPage(driver);
        Doxzilla_AccSettingsPage accObj = new Doxzilla_AccSettingsPage(driver);
        Screenshot.logMessage(LogStatus.INFO, "Opening Vipa Website", "");
        homeObj.viewHome();
        homeObj.clickSignin();
        Screenshot.logMessage(LogStatus.INFO, "Logging-In", "");
        loginObj.loginTest(email_Address, newPass);
        Screenshot.logMessage(LogStatus.INFO, "Opening Account & Settings", "");
        homeObj.openAccountSetting();
        Screenshot.logMessage(LogStatus.INFO, "Raising Account Deletion Request", "");
        accObj.DelAccButton();
    }
}