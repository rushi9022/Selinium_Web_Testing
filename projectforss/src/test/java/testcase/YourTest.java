package testcase;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class YourTest extends BaseTest {

    @Test
    public void sampleTest() {
        driver.get("https://www.flipkart.com");
        // Intentional failure for demonstration
        Assert.assertTrue(false, "Failing this test intentionally");
    }
}
