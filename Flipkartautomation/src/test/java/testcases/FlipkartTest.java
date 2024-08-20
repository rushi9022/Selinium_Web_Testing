package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;

public class FlipkartTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set up the ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Navigate to Flipkart
        driver.get("https://www.flipkart.com");

        // Close the login popup if present
        try {
            WebElement closeButton = driver.findElement(By.xpath("//button[contains(text(), '✕')]"));
            closeButton.click();
        } catch (Exception e) {
            // If the login popup is not present, do nothing
        }
    }

    @Test
    public void testAddToCart() {
        // Search for a product
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("realme p1 5g");
        searchBox.submit();

        // Wait for results to load
        try {
            Thread.sleep(2000); // Consider using WebDriverWait for better synchronization
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Select the first product from the search results
        WebElement firstProduct = driver.findElement(By.xpath("//div[normalize-space()='realme P1 5G (Peacock Green, 256 GB)']"));

        String originalHandle = driver.getWindowHandle();
        firstProduct.click();

        // Switch to the new tab
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // Wait for the product page to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Locate and extract product highlights
        List<WebElement> highlightsList = driver.findElements(By.xpath("//div[@class='vN8oQA']")); // Adjust XPath if needed
        boolean highlightFound = false;
        String requiredHighlight = "8 GB RAM | 256 GB ROM";

        // Check if any highlight contains the required text
        for (WebElement highlight : highlightsList) {
            String highlightText = highlight.getText();
            if (highlightText.contains(requiredHighlight)) {
                highlightFound = true;
                break;
            }
        }

        if (highlightFound) {
            // Add the product to the cart
            WebElement addToCartButton = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']")); // Adjust XPath if needed
            addToCartButton.click();

            // Wait for the cart page to load
            WebElement cartIcon = driver.findElement(By.xpath("//a[@href='/cart']"));
            cartIcon.click();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            WebElement cartProduct = driver.findElement(By.xpath("//div[contains(@class, '_1AtVbE')]"));
            Assert.assertTrue(cartProduct.isDisplayed(), "Product was not added to cart successfully");
        } else {
        	WebElement cartIcon = driver.findElement(By.xpath("//a[@class='_9Wy27C']"));
            cartIcon.click();
            System.out.println("Due to mismatch in highlights product is not added to cart");
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
