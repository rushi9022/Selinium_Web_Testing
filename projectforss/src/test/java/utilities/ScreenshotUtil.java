package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static void captureScreenshot(WebDriver driver, String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String destFile = "screenshots/" + testName + "_" + timestamp + ".png";

        try {
            FileUtils.copyFile(srcFile, new File(destFile));
            System.out.println("Screenshot saved at: " + destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
