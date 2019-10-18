package com.xpanxion.cpsat.assignments;

import com.xpanxion.cpsat.configuration.Environment;
import com.xpanxion.cpsat.driver.WebDriverUtil;
import com.xpanxion.cpsat.pages.meripustak.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Problem1 {
    private WebDriver driver;

    @BeforeMethod
    public void initializeDriver() {
        driver = new WebDriverUtil().getDriver("CHROME");
    }

    @Test
    public void verifyMeriPustakAddToCart() {
        driver.get(Environment.getValue("meripustak.url"));
        MPHeaderPage header = new MPHeaderPage(driver);
        Dimension size = header.getLogoDimension();
        System.out.println("Logo Height : " + size.height);
        System.out.println("Logo Width : " + size.width);
        MPFooterPage footer = new MPFooterPage(driver);
        System.out.println("Twitter Logo HREF : " + footer.getTwitterHref());
        MPCartPage cartPage = header.goToCartPage();
        String actualText = cartPage.getCartEmptyMesage();
        String expectedText = "No Item is Added In Cart yet.Cart is Empty!!!";
        Assert.assertEquals(actualText, expectedText, "Empty Cart Message on page does not match expected message");
        MPSearchPage searchPage = header.searchBook("Java Ee Applications On Oracle Java Cloud");
        MPProductPage productPage = searchPage.waitForSearchResults()
                .goToBookByName("Java Ee Applications On Oracle Java Cloud");
        cartPage = productPage.clickAddToCart();
        Assert.assertFalse(cartPage.isEmptyCartMessageDisplayed(),"" +
                "Empty Cart message is displayed on the page.");
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }
}
