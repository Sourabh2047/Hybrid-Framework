package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import static report.ReportManager.startTest;

public class Listener extends TestListenerAdapter implements ITestListener {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest logger;

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName()
                : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    public void onStart(ITestContext testContext) {
        //String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //Time stamp
        String repName = "Test-Report.html";

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/" + repName);
        htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/src/main/resources/extent-config.xml");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host name", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", "Sourabh_QA");

        htmlReporter.config().setDocumentTitle("Test Automation Report"); // Tile of report
        htmlReporter.config().setReportName("Functional Test Report"); // name of the report
        //htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart/
        htmlReporter.config().setTheme(Theme.STANDARD);
    }

    public void onTestStart(ITestResult result) {
        startTest(getTestName(result), getTestDescription(result));
        LoggerUtil.info(getTestName(result) + ": Test started");
    }

    public void onTestSuccess(ITestResult result) {
        logger = extent.createTest(result.getName());
        logger.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
        Screenshot.logMessage(LogStatus.PASS, "Test Case Completed", "Test Passed");
        LoggerUtil.info(getTestName(result) + " : Test Passed");
        //CrLiveScreenshot.addScreenShot(LogStatus.PASS, "Test Passed");//to add SS in Pass Test
    }

    public void onTestFailure(ITestResult result) {
        logger = extent.createTest(result.getName()); // create new entry in th report
        logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED)); // send the passed information to the report with GREEN color highlighted
        Throwable t = result.getThrowable();
        String cause = "";
        if (t != null)
            cause = t.getMessage();
        Screenshot.addScreenShot(LogStatus.FAIL, "Test Failed : " + cause);
        LoggerUtil.error(getTestName(result) + " : Test Failed : \n" + cause);
    }

    public void onTestSkipped(ITestResult result) {
        logger = extent.createTest(result.getName()); // create new entry in th report
        logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
        Screenshot.logMessage(LogStatus.SKIP, "Test Ignored : Unable to Proceed", "Test Skipped");
        LoggerUtil.warn(getTestName(result) + " : Test Skipped");
    }

    public void onFinish(ITestContext testContext) {
        //extent.flush();
        ReportManager.endCurrentTest();
        ReportManager.getExtentReports().flush();
    }
}