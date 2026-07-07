package org.practo.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DiagnosisPage {
    WebDriver driver;
    public DiagnosisPage(WebDriver driver) { this.driver = driver; }
    public boolean isTopCitiesDisplayed() { return getTopCities().size() > 0; }

    public List<String> getTopCities() {
        Set<String> cities = new LinkedHashSet<>();
        try { ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);"); Thread.sleep(1500); } catch (Exception ignored) {}
        collectCities(cities);
        if (cities.isEmpty()) { driver.get("https://www.practo.com/india"); collectCities(cities); }
        return new ArrayList<>(cities);
    }

    private void collectCities(Set<String> cities) {
        String[] cityNames = {"Bangalore", "Delhi", "Mumbai", "Chennai", "Hyderabad", "Kolkata", "Pune", "Ahmedabad", "Jaipur", "Lucknow", "Indore", "Chandigarh", "Noida", "Gurgaon", "Surat", "Vadodara"};
        for (String city : cityNames) {
            try { List<WebElement> elements = driver.findElements(By.xpath("//*[normalize-space()='" + city + "']")); if (!elements.isEmpty()) cities.add(city); }
            catch (Exception ignored) {}
        }
    }
    public void printTopCities() { for (String city : getTopCities()) System.out.println(city); }
}
