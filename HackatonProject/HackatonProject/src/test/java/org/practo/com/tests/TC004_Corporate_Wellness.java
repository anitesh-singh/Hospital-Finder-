package org.practo.com.tests;

import org.practo.com.baseclass.baseclass;
import org.practo.com.pages.CorporateWellnessPage;
import org.practo.com.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC004_Corporate_Wellness extends baseclass {
    @Test(priority = 5)
    public void validateCorporateWellnessInvalidForm() {
        new HomePage(driver).openCorporateWellnessAndSwitchToNewTab();
        CorporateWellnessPage page = new CorporateWellnessPage(driver);
        Assert.assertTrue(page.isCorporateWellnessPageOpened(), "Corporate Wellness page not opened");
        String msg = page.submitInvalidDetailsAndGetWarning();
        System.out.println("Warning Message: " + msg);
        Assert.assertFalse(msg.equalsIgnoreCase("Warning message not displayed"), "Warning message not captured");
    }
}
