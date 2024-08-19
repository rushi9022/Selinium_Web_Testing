package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ScreenshotUtil;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
    	WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        // Code to initialize WebDriver, e.g., driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Capture screenshot if test fails
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtil.captureScreenshot(driver, result.getName());
        }
        // Close browser
        if (driver != null) {
            driver.quit();
        }
    }
}
