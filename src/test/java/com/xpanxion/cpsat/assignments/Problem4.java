package com.xpanxion.cpsat.assignments;

import com.xpanxion.cpsat.configuration.Environment;
import com.xpanxion.cpsat.driver.WebDriverUtil;
import com.xpanxion.cpsat.pages.hometown.HomeTownPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/*
4) Using Junit and WebDriver, open ‘https://www.hometown.in/’ in Firefox and do the below.
    1. Select Electronics from ‘More’ menu
    2. From Filter, section select the color as ‘Black’
    3. When you go to the first product image, you will see Quick View option, click on that
    4. Assert that product name contains Black in a name.
    5. Close the Quick view window and verify if Black is also present in Applied filters
 */
public class Problem4 {
    private WebDriver driver;

    @Before
    public void initializeDriver() {
        driver = new WebDriverUtil().getDriver("FIREFOX");
    }

    @Test
    public void verifyHomeTownProduct() {
        //open ‘https://www.hometown.in/’ in Firefox
        driver.get(Environment.getValue("hometown.url"));

        //Select Electronics from ‘More’ menu
        HomeTownPage htPage = new HomeTownPage(driver);
        htPage.clickElectronicsLink();
        htPage.clickNoThanksButton();

        //From Filter, section select the color as ‘Black’
        htPage.applyBlackColorFilter();

        //When you go to the first product image, you will see Quick View option, click on that
        htPage.clickQuickViewOfFirstProduct();

        //Assert that product name contains Black in a name.
        String actualProductText = htPage.getProductNameFromModal();
        Assert.assertTrue("Product name does not contain the text BLACK. Product name:" +
                actualProductText, actualProductText.contains("Black"));

        //Close the Quick view window
        htPage.closeModal();

        //verify if Black is also present in Applied filters
        Assert.assertTrue(" Black is not present in Applied filters. Filters:" +
                htPage.getAppliedFilters(), htPage.getAppliedFilters().contains("Black"));
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

}
