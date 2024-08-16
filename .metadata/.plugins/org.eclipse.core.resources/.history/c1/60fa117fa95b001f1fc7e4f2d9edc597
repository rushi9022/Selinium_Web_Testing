package base;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    protected WebDriver driver;
    protected Properties prop;

    @BeforeTest
    public void setup() {
        prop = new Properties();

        try {
            // Load properties from config file
            FileInputStream fileInput = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\configfiles\\config.properties");
            prop.load(fileInput);

            // Get the browser from the properties file
            String browser = prop.getProperty("browser");

            // Set up WebDriver based on the browser specified in the config
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else {
                System.out.println("Browser not supported!");
                return;
            }

            // Maximize the browser window
            driver.manage().window().maximize();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
