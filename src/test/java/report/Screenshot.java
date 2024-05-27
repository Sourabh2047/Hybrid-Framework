package report;

import browser.WebDriverManager;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Screenshot {
    public static void addScreenShot(String message) {
        String base64Image = "data:image/png;base64," + ((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
        ReportManager.getCurrentTest().log(LogStatus.INFO, message,
                ReportManager.getCurrentTest().addBase64ScreenShot(base64Image));
    }

    public static void addScreenShot(LogStatus status, String message) {
        String base64Image = "data:image/png;base64," + ((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
        ReportManager.getCurrentTest().log(status, message,
                ReportManager.getCurrentTest().addBase64ScreenShot(base64Image));
    }

    public static void logMessage(LogStatus status, String message, String details) {
        ReportManager.getCurrentTest().log(status, message, details);
    }
}