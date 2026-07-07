package org.practo.com.tests;

import org.practo.com.baseclass.baseclass;
import org.practo.com.pages.DiagnosisPage;
import org.practo.com.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class TC003_Print_Top_Cities extends baseclass {
    @Test(priority = 4)
    public void printTopCities() {
        new HomePage(driver).clickLabTests();
        DiagnosisPage diagnosisPage = new DiagnosisPage(driver);
        List<String> cities = diagnosisPage.getTopCities();
        for (String city : cities) System.out.println(city);
        Assert.assertTrue(cities.size() > 0, "Top Cities list not found");
    }
}
