package testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class firstTest extends BaseTest {

    @Test
    
    public void testZohoSignIn() {
        try {
            // Get the test URL from the properties file
            String testUrl = prop.getProperty("testurl");

            // Open the specified URL
            driver.get(testUrl);

            // Click on the Sign in button
            WebElement signInButton = driver.findElement(By.linkText("Sign in"));
            signInButton.click();

            // Wait for the sign-in page to load
            Thread.sleep(2000);

            // Enter email address or mobile number
            WebElement emailField = driver.findElement(By.id("login_id"));
            emailField.sendKeys("rushi.goggle@gmail.com");

            // Click on the Next button
            WebElement nextButton = driver.findElement(By.id("nextbtn"));
            nextButton.click();

            // Wait for the password field to load
            Thread.sleep(2000);

            // Enter password
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("Pass@2524");

            // Click on the Sign in button
            WebElement submitButton = driver.findElement(By.id("nextbtn"));
            submitButton.click();

            // Wait for sign-in to complete
            Thread.sleep(5000);

            // Verify the sign-in by checking if a certain element is present after login
            boolean isLoginSuccessful = driver.findElement(By.id("element-after-login")) != null;

            // Assertion to verify login success
            Assert.assertTrue(isLoginSuccessful, "Sign-in was not successful.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
