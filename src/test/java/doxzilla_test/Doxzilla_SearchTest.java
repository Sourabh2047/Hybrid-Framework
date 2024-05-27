package doxzilla_test;

import browser.TestData;
import com.relevantcodes.extentreports.LogStatus;
import doxzilla_pages.Doxzilla_HomePage;
import doxzilla_pages.Doxzilla_SearchPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import report.Screenshot;

public class Doxzilla_SearchTest extends Doxzilla_BaseTest {

    @DataProvider(name = "Doxzilla_Search_Data")
    public Object[][] getSearchData() {
        return new Object[][]{{TestData.getDataValue("Doxzilla_Search_Data", "Series"), TestData.getDataValue("Doxzilla_Search_Data", "Movie"),
                TestData.getDataValue("Doxzilla_Search_Data", "Episode"), TestData.getDataValue("Doxzilla_Search_Data", "Data")}};
    }

    @Test(priority = 1, dataProvider = "Doxzilla_Search_Data", groups = {"Smoke", "Sanity", "Regression"},
            description = "Verification & Validation of Search Functionality : Verifying User able to use Search and Validating Search Result")
    public void SEARCH_Verify_MovieResult(String series, String movie, String episode, String data) throws InterruptedException {
        {
            Screenshot.logMessage(LogStatus.INFO, "Test Case Started", "");
            Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
            Doxzilla_SearchPage searchObj = new Doxzilla_SearchPage(driver);
            Screenshot.logMessage(LogStatus.INFO, "Opening Doxzilla Website", "");
            homeObj.viewHome();
            Screenshot.logMessage(LogStatus.INFO, "Searching Movie Content", movie);
            homeObj.search(movie);
            String contentTitle = searchObj.openFirstRail_FirstContent();
            Screenshot.logMessage(LogStatus.INFO, "Opening First Content from Search Result", contentTitle);
        }
    }

    @Test(priority = 2, dataProvider = "Doxzilla_Search_Data", groups = {"Smoke", "Sanity", "Regression"},
            description = "Verification & Validation of Search Functionality : Verifying User able to use Search and Validating Search Result")
    public void SEARCH_Verify_SeriesResult(String series, String movie, String episode, String data) throws InterruptedException {
        {
            Screenshot.logMessage(LogStatus.INFO, "Test Case Started", "");
            Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
            Doxzilla_SearchPage searchObj = new Doxzilla_SearchPage(driver);
            Screenshot.logMessage(LogStatus.INFO, "Opening Doxzilla Website", "");
            homeObj.viewHome();
            Screenshot.logMessage(LogStatus.INFO, "Searching Series Content", series);
            homeObj.search(series);
            String contentTitle = searchObj.openFirstRail_FirstContent();
            Screenshot.logMessage(LogStatus.INFO, "Opening First Content from Search Result", contentTitle);
        }
    }

    @Test(priority = 3, dataProvider = "Doxzilla_Search_Data", groups = {"Smoke", "Sanity", "Regression"},
            description = "Verification & Validation of Search Functionality : Verifying User able to use Search and Validating Search Result")
    public void SEARCH_Verify_EpisodeResult(String series, String movie, String episode, String data) throws InterruptedException {
        {
            Screenshot.logMessage(LogStatus.INFO, "Test Case Started", "");
            Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
            Doxzilla_SearchPage searchObj = new Doxzilla_SearchPage(driver);
            Screenshot.logMessage(LogStatus.INFO, "Opening Doxzilla Website", "");
            homeObj.viewHome();
            Screenshot.logMessage(LogStatus.INFO, "Searching Episode Content", episode);
            homeObj.search(episode);
            String contentTitle = searchObj.openSecondRail_FirstContent();
            Screenshot.logMessage(LogStatus.INFO, "Opening First Episode Content from Search Result", contentTitle);
        }
    }

    @Test(priority = 4, groups = {"Sanity"}, dataProvider = "Doxzilla_Search_Data",
            description = "Verification & Validation of Search Result Page : Checking Search Result when entered Invalid Inputs")
    public void SEARCH_Verify_NoSearchResult(String series, String movie, String episode, String data) throws InterruptedException {
        {
            Screenshot.logMessage(LogStatus.INFO, "Test Case Started", "");
            Doxzilla_HomePage homeObj = new Doxzilla_HomePage(driver);
            Doxzilla_SearchPage searchObj = new Doxzilla_SearchPage(driver);
            Screenshot.logMessage(LogStatus.INFO, "Opening Doxzilla Website", "");
            homeObj.viewHome();
            homeObj.search(data);
            String msg = searchObj.getSearchResultText();
            Screenshot.logMessage(LogStatus.INFO, "Search Result for " + data, msg);
            if (driver.getCurrentUrl().contains("th")) {
                Assert.assertEquals(msg, "ไม่พบเนื้อหาที่ต้องการ");
            } else {
                Assert.assertEquals(msg, "No Results Found");
            }
            Screenshot.logMessage(LogStatus.INFO, "Validating Search Result", "");
        }
    }
}