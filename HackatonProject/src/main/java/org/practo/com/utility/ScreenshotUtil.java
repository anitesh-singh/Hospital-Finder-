package org.practo.com.utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static void takeScreenshot(WebDriver driver, String testName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            Path folder = Path.of(System.getProperty("user.dir"), "screenshots");
            Files.createDirectories(folder);
            Files.copy(src.toPath(), folder.resolve(testName + "_" + time + ".png"));
        } catch (Exception ignored) {}
    }
}
