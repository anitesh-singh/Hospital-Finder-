package org.practo.com.tests;

import org.practo.com.baseclass.baseclass;
import org.practo.com.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_Validate_HomePage extends baseclass {
    @Test(priority = 1)
    public void validateHomePage() { Assert.assertTrue(new HomePage(driver).isHomePageOpened(), "Home page is not opened"); }
}
