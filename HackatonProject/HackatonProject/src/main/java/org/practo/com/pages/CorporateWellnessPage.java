package org.practo.com.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class CorporateWellnessPage {
    WebDriver driver;
    @FindBy(xpath = "(//input[@id='name'][@name='name'])[1]") WebElement nameInput;
    @FindBy(xpath = "(//input[@id='organizationName'])[1]") WebElement organizationInput;
    @FindBy(xpath = "(//input[@id='contactNumber'])[1]") WebElement phoneInput;
    @FindBy(xpath = "(//input[@id='officialEmailId'])[1]") WebElement emailInput;
    @FindBy(id = "organizationSize") WebElement organizationSizeDropdown;
    @FindBy(xpath = "//button[contains(text(),'Schedule')]") WebElement scheduleButton;

    public CorporateWellnessPage(WebDriver driver) { this.driver = driver; PageFactory.initElements(driver, this); }
    public boolean isCorporateWellnessPageOpened() { return driver.getCurrentUrl().contains("/plus/corporate") || driver.getPageSource().toLowerCase().contains("schedule"); }

    public String submitInvalidDetailsAndGetWarning() {
        nameInput.sendKeys("12345"); organizationInput.sendKeys("@@@"); phoneInput.sendKeys("abcd123"); emailInput.sendKeys("invalid-email");
        fillDropdowns();
        if (!scheduleButton.isEnabled()) return "Schedule Demo button is disabled";
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", scheduleButton);
        String msg = validationMessage(emailInput); if (!msg.isEmpty()) return msg;
        msg = validationMessage(phoneInput); if (!msg.isEmpty()) return msg;
        List<WebElement> errors = driver.findElements(By.xpath("//*[contains(@class,'error') or contains(text(),'valid') or contains(text(),'required') or contains(text(),'invalid')]"));
        for (WebElement error : errors) { String text = error.getText().trim(); if (!text.isEmpty()) return text; }
        return "Warning message not displayed";
    }

    private void fillDropdowns() {
        try { new Select(organizationSizeDropdown).selectByIndex(1); } catch (Exception ignored) {}
        for (WebElement selectElement : driver.findElements(By.tagName("select"))) {
            try { Select select = new Select(selectElement); if (select.getOptions().size() > 1) select.selectByIndex(1); }
            catch (Exception ignored) {}
        }
    }
    private String validationMessage(WebElement element) {
        try { String msg = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].validationMessage;", element); return msg == null ? "" : msg.trim(); }
        catch (Exception e) { return ""; }
    }
}
