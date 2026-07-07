package org.practo.com.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver) { this.driver = driver; }

    public boolean isHomePageOpened() {
        return driver.getCurrentUrl().contains("practo.com") || driver.getTitle().toLowerCase().contains("practo");
    }
    public void clickLabTests() { driver.get("https://www.practo.com/tests"); }
    public void clickSearchForHospitals() { driver.get("https://www.practo.com/bangalore/hospitals"); }
    public void openCorporateWellnessAndSwitchToNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open('https://www.practo.com/plus/corporate','_blank');");
        for (String window : driver.getWindowHandles()) driver.switchTo().window(window);
    }
}
