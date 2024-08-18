package testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import utilities.ReadXLdata;

public class firsttestfw extends BaseTest {

    @Test(dataProviderClass=ReadXLdata.class,dataProvider="logindata")
    public void testZohoSignIn(String username, String password) {
        try {
            // Get the test URL from the config properties file
            String testUrl = configProp.getProperty("testurl");

            // Open the specified URL
            driver.get(testUrl);

            // Click on the Sign in button using locator from locators.properties
            WebElement signInButton = driver.findElement(By.linkText(locatorsProp.getProperty("sign_in")));
            signInButton.click();

            // Wait for the sign-in page to load
            Thread.sleep(2000);

            // Enter email address or mobile number using locator from locators.properties
            WebElement emailField = driver.findElement(By.id(locatorsProp.getProperty("email_id")));
            emailField.sendKeys(username);

            // Click on the Next button using locator from locators.properties
            WebElement nextButton = driver.findElement(By.id(locatorsProp.getProperty("next_btn")));
            nextButton.click();

            // Wait for the password field to load
            Thread.sleep(2000);

            // Enter password using locator from locators.properties
            WebElement passwordField = driver.findElement(By.id(locatorsProp.getProperty("pwd")));
            passwordField.sendKeys(password);

            // Click on the Sign in button using locator from locators.properties
            WebElement submitButton = driver.findElement(By.id(locatorsProp.getProperty("login_nextbtn")));
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
