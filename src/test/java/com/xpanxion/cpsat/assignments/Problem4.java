package com.xpanxion.cpsat.assignments;

import com.xpanxion.cpsat.configuration.Environment;
import com.xpanxion.cpsat.driver.WebDriverUtil;
import com.xpanxion.cpsat.pages.hometown.HomeTownPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class Problem4 {
    private WebDriver driver;

    @Before
    public void initializeDriver() {
        driver = new WebDriverUtil().getDriver("FIREFOX");
    }

    @Test
    public void verifyHomeTownProduct() {
        driver.get(Environment.getValue("hometown.url"));
        HomeTownPage htPage = new HomeTownPage(driver);
        htPage.clickElectronicsLink();
        htPage.clickNoThanksButton();
        htPage.applyBlackColorFilter();
        htPage.clickQuickViewOfFirstProduct();
        String actualProductText = htPage.getProductNameFromModal();
        Assert.assertTrue("Product name does not contain the text BLACK. Product name:" +
                actualProductText, actualProductText.contains("Black"));
        htPage.closeModal();
        Assert.assertTrue(" Black is not present in Applied filters. Filters:" +
                htPage.getAppliedFilters(), htPage.getAppliedFilters().contains("Black"));
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

}
