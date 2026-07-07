package org.practo.com.baseclass;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.practo.com.utility.ScreenshotUtil;
import org.practo.com.utility.Utility;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class baseclass {
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeMethod(alwaysRun = true)
    public void BrowserSetup() throws Exception {
        String browser = Utility.fetchPropertyValue("browser").toLowerCase();
        if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup(); driver = new EdgeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup(); driver = new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get(Utility.fetchPropertyValue("baseUrl"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        try {
            if (driver != null) {
                String name = result.getMethod().getMethodName();
                ScreenshotUtil.takeScreenshot(driver, name + (result.getStatus() == ITestResult.FAILURE ? "_FAILED" : "_PASSED"));
            }
        } catch (Exception ignored) {}
        finally { if (driver != null) driver.quit(); }
    }
}
