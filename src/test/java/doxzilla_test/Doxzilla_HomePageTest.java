package doxzilla_test;

import com.relevantcodes.extentreports.LogStatus;
import doxzilla_pages.Doxzilla_HomePage;
import org.testng.annotations.Test;
import report.Screenshot;

public class Doxzilla_HomePageTest extends Doxzilla_BaseTest {

    @Test(priority = 1, groups = {"Smoke"},
            description = "Verification & Validation of Page Title : Verifying URL & Page Title is correct or not.")
    public void HOME_Verify_PageTitle() throws InterruptedException {
        {
            Screenshot.logMessage(LogStatus.INFO, "Test Case Started", "");
            Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
            Screenshot.logMessage(LogStatus.INFO, "Opening Doxzilla Website", "");
            homeObj.viewHome();
            Screenshot.logMessage(LogStatus.INFO, "Homepage Loaded", "");
            String URL = homeObj.verifyHomepage();
            Screenshot.logMessage(LogStatus.INFO, "Validating Page URL", URL);
            Screenshot.logMessage(LogStatus.INFO, "Validating Page Title", driver.getTitle());
        }
    }

    @Test(priority = 4, groups = {"Sanity", "Regression"},
            description = "Verification & Validation of HomePage Contents : Check Contents on HomePage is opening and Propely displaying or not.")
    public void HOME_Verify_HomeContents() throws InterruptedException {
        {
            Screenshot.logMessage(LogStatus.INFO, "Test Case Started", "");
            Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
            Screenshot.logMessage(LogStatus.INFO, "Opening Doxzilla Website", "");
            homeObj.viewHome();
            Screenshot.logMessage(LogStatus.INFO, "Homepage Loaded", "");
            homeObj.verifyHome();
            Screenshot.logMessage(LogStatus.INFO, "Validating HomePage Contents", "");
        }
    }

    @Test(priority = 3, groups = {"Sanity", "Regression"},
            description = "Verification & Validation of Page Header Section : Check weather HotnNew Page is opening and Propely displaying or not.")
    public void HOME_Verify_HotNew_Section() throws InterruptedException {
        {
            Screenshot.logMessage(LogStatus.INFO, "Test Case Started", "");
            Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
            Screenshot.logMessage(LogStatus.INFO, "Opening Doxzilla Website", "");
            homeObj.viewHome();
            Screenshot.logMessage(LogStatus.INFO, "Homepage Loaded", "");
            homeObj.verifyHotNew();
            Screenshot.logMessage(LogStatus.INFO, "Validating HotnNew Page Contents", "");
        }
    }

    @Test(priority = 2, groups = {"Sanity", "Regression"},
            description = "Verification & Validation of Page Header Section : Check weather AllContent page is opening and Propely displaying or not.")
    public void HOME_Verify_AllContent_Section() throws InterruptedException {
        {
            Screenshot.logMessage(LogStatus.INFO, "Test Case Started", "");
            Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
            Screenshot.logMessage(LogStatus.INFO, "Opening Doxzilla Website", "");
            homeObj.viewHome();
            Screenshot.logMessage(LogStatus.INFO, "Homepage Loaded", "");
            homeObj.verifyAllContent();
            Screenshot.logMessage(LogStatus.INFO, "Validating AllContents Page Contents", "");
        }
    }

    @Test(priority = 5, groups = {"Sanity", "Regression"},
            description = "Verification & Validation of Page Header Section : Check weather MyList Page is opening and Propely displaying or not.")
    public void HOME_Verify_MyList_Section() throws InterruptedException {
        {
            Screenshot.logMessage(LogStatus.INFO, "Test Case Started", "");
            Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
            Screenshot.logMessage(LogStatus.INFO, "Opening Doxzilla Website", "");
            homeObj.viewHome();
            Screenshot.logMessage(LogStatus.INFO, "Homepage Loaded", "");
            homeObj.openMyList();
            Screenshot.logMessage(LogStatus.INFO, "Validating MyList Page Contents", "");
        }
    }

    @Test(priority = 6, groups = {"Sanity", "Regression"},
            description = "Verification & Validation of Page Footer Section : Verifying Copyright Text and check Whether all static pages links on footer section are working or not.")
    public void HOME_Verify_StaticPages_Links() throws InterruptedException {
        {
            Screenshot.logMessage(LogStatus.INFO, "Test Case Started", "");
            Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
            Screenshot.logMessage(LogStatus.INFO, "Opening Doxzilla Website", "");
            homeObj.viewHome();
            Screenshot.logMessage(LogStatus.INFO, "Homepage Loaded", "");
            homeObj.verifyStaticPages();
            Screenshot.logMessage(LogStatus.INFO, "Validating All Static Pages Links on Footer", "");
        }
    }

    @Test(priority = 7, groups = {"Sanity", "Regression"},
            description = "Verification & Validation of Page Footer Section : Verifying Text and check Whether all connect with us links on footer section are working or not.")
    public void HOME_Verify_ConnectWithUs() throws InterruptedException {
        {
            Screenshot.logMessage(LogStatus.INFO, "Test Case Started", "");
            Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
            Screenshot.logMessage(LogStatus.INFO, "Opening Doxzilla Website", "");
            homeObj.viewHome();
            Screenshot.logMessage(LogStatus.INFO, "Homepage Loaded", "");
            homeObj.verifyConnectWithUs();
            Screenshot.logMessage(LogStatus.INFO, "Validating All Connect With us Links on Footer", "");
        }
    }

    @Test(priority = 8, groups = {"Sanity", "Regression"},
            description = "Verification & Validation of Page Footer Section : Verifying Text and check Whether Download App links on footer section are working or not.")
    public void HOME_Verify_DownloadApps_Section() throws InterruptedException {
        {
            Screenshot.logMessage(LogStatus.INFO, "Test Case Started", "");
            Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
            Screenshot.logMessage(LogStatus.INFO, "Opening Doxzilla Website", "");
            homeObj.viewHome();
            Screenshot.logMessage(LogStatus.INFO, "Homepage Loaded", "");
            homeObj.verifyDownloadApps();
            Screenshot.logMessage(LogStatus.INFO, "Validating Download App Links on Footer", "");
        }
    }
}