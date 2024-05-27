package doxzilla_test;

import browser.TestData;
import browser.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;
import report.Listener;
import report.LoggerUtil;
import report.ReportManager;

import java.time.Duration;

@Listeners({Listener.class})
public class Doxzilla_BaseTest {
    protected WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void globalSetup() {
        LoggerUtil.info("************************** DOXZILLA Web Automation Execution Started ************************************");
        TestData.getJsonNode();
    }

    public void getURL(String url) {
        driver.get(url);
    }

    @BeforeMethod
    @Parameters({"browser", "env"})
    protected void setup(@Optional("chrome") String browser, @Optional("env") String env) {
        if (browser.equalsIgnoreCase("Firefox")) {
            io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
            System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            WebDriverManager.removeDriver();
            WebDriverManager.setDriver(driver);
        } else if (browser.equalsIgnoreCase("Chrome")) {
            io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
            ChromeOptions ops = new ChromeOptions();
            ops.addArguments("disable-infobars");
            ops.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(ops);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            WebDriverManager.removeDriver();
            WebDriverManager.setDriver(driver);
        } else if (browser.equalsIgnoreCase("IE")) {
            io.github.bonigarcia.wdm.WebDriverManager.iedriver().setup();
            System.setProperty("webdriver.ie.driver", "C:\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            WebDriverManager.removeDriver();
            WebDriverManager.setDriver(driver);
        } else if (browser.equalsIgnoreCase("Safari")) {
            SafariOptions options = new SafariOptions();
            driver = new SafariDriver(options);
            driver = new SafariDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            WebDriverManager.removeDriver();
            WebDriverManager.setDriver(driver);
        } else {
            System.out.println("No Browser pass, Using Chrome Browser as default");
        }
        if (System.getProperty("env") != null) {
            env = System.getProperty("env");
        }
        getURL(TestData.getDataValue("DOXZILLA_BaseUrl", env));
        System.out.println("");
        LoggerUtil.info("Test Execution Initiated");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        ReportManager.endCurrentTest();
        LoggerUtil.info("Test Execution Completed");
        System.out.println("");
    }

    @AfterSuite(alwaysRun = true)
    public void wrapAllUp(ITestContext context) {
        int total = context.getAllTestMethods().length;
        int passed = context.getPassedTests().size();
        int failed = context.getFailedTests().size();
        int skipped = context.getSkippedTests().size();
        LoggerUtil.info("************************** DOXZILLA Web Automation Execution Completed ************************************");
        int columnWidth = 10;
        LoggerUtil.info(String.format("%-" + columnWidth + "s : %s", "Total Number of testcases", total));
        LoggerUtil.info(String.format("%-" + columnWidth + "s : %s", "Number of testcases Passed", passed));
        LoggerUtil.info(String.format("%-" + columnWidth + "s : %s", "Number of testcases Failed", failed));
        LoggerUtil.info(String.format("%-" + columnWidth + "s : %s", "Number of testcases Skipped", skipped));
        /*boolean mailSent = MailUtil.sendMail(total, passed, failed, skipped);
        LoggerUtil.log("Mail sent : " + mailSent);
        System.out.println("");*/
    }
}