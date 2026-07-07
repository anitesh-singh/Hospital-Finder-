package org.practo.com.tests;

import org.practo.com.baseclass.baseclass;
import org.practo.com.pages.SearchHospitalsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class TC002_Search_Hospitals extends baseclass {
    @Test(priority = 2)
    public void validateSearchHospitals() {
        SearchHospitalsPage page = new SearchHospitalsPage(driver);
        page.openBangaloreHospitals();
        Assert.assertTrue(page.isHospitalDisplayed(), "Hospital page is not opened");
    }
    @Test(priority = 3)
    public void printHospitalNames() {
        SearchHospitalsPage page = new SearchHospitalsPage(driver);
        page.openBangaloreHospitals();
        List<String> hospitals = page.getHospitalNames();
        page.printHospitals(hospitals);
        Assert.assertTrue(hospitals.size() > 0, "No hospitals found");
    }
}
