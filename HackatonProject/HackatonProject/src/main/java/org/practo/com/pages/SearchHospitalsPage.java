package org.practo.com.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import java.util.List;

public class SearchHospitalsPage {
    WebDriver driver;
    public SearchHospitalsPage(WebDriver driver) { this.driver = driver; }

    public void openBangaloreHospitals() { driver.get("https://www.practo.com/bangalore/hospitals"); handlePopup(); }

    public boolean isHospitalDisplayed() {
        handlePopup();
        return driver.getCurrentUrl().contains("bangalore/hospitals") || driver.getPageSource().toLowerCase().contains("hospitals in bangalore");
    }

    public List<String> getHospitalNames() {
        handlePopup();
        List<String> names = new ArrayList<>();
        List<WebElement> cards = driver.findElements(By.xpath("//div[.//h2 or .//h3][contains(.,'Open 24x7') or contains(.,'Open 24 x 7') or contains(.,'Patient Stories')]"));
        for (WebElement card : cards) {
            try {
                String text = card.getText();
                if (!text.toLowerCase().contains("open 24")) continue;
                String rating = getRating(text);
                if (!rating.isEmpty() && Double.parseDouble(rating) > 3.5) names.add(card.findElement(By.xpath(".//h2 | .//h3")).getText());
            } catch (Exception ignored) {}
        }
        return names;
    }

    public void printHospitals(List<String> hospitals) { for (String hospital : hospitals) System.out.println(hospital); }

    private String getRating(String text) {
        for (String word : text.split("\\s+")) {
            try { double value = Double.parseDouble(word); if (value > 3.5 && value <= 5.0) return word; }
            catch (Exception ignored) {}
        }
        return "";
    }

    private void handlePopup() {
        try { new Actions(driver).sendKeys(Keys.ESCAPE).perform(); } catch (Exception ignored) {}
        String[] closeButtons = {"//button[@aria-label='Close']", "//button[contains(text(),'×')]", "//span[contains(text(),'×')]", "//button[contains(text(),'Skip')]", "//button[contains(text(),'Not now')]", "//button[contains(text(),'Later')]", "//*[contains(@class,'close')]"};
        for (String xpath : closeButtons) {
            try { WebElement close = driver.findElement(By.xpath(xpath)); if (close.isDisplayed()) { close.click(); return; } }
            catch (Exception ignored) {}
        }
        try { ((JavascriptExecutor) driver).executeScript("document.querySelectorAll('[role=\\\"dialog\\\"],.modal,.overlay,.ReactModal__Overlay').forEach(e=>e.remove());document.body.style.overflow='auto';"); }
        catch (Exception ignored) {}
    }
}
