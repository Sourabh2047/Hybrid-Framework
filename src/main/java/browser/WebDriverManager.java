package browser;

import org.openqa.selenium.WebDriver;

public class WebDriverManager {
    private static final InheritableThreadLocal<WebDriver> driverinstance = new InheritableThreadLocal<>();

    public static WebDriver getDriver() {
        if (driverinstance.get() == null) {
            throw new IllegalStateException("WebDriver has not been set, Please set WebDriver instance by WebDriverManager.setDriver...");
        } else {
            return driverinstance.get();
        }
    }

    public static void setDriver(WebDriver driver) {
        driverinstance.set(driver);
    }

    public static void removeDriver() {
        driverinstance.remove();
    }
}