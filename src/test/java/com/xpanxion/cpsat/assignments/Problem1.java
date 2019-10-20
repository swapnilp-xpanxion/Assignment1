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

/*
Using TestNG and WebDriver script, open https://www.meripustak.com/ in Google Chrome and do the below:
    1. Print the width and height of the logo
    2. Under Follow us section on the bottom, extract and print the href of ‘twitter’ social media
    icon (Right a script in such a way, if the position of ‘twitter’ icon is changed tomorrow in the
    social media icons, our script should work)
    3. Click on the shopping cart when an item in the cart is 0
    4. Assert the message in the shopping cart table “No Item is Added In Cart yet. Cart is
    Empty!!!”
    5. Add anyone java book in cart
    6. Verify if this message exists in the shopping cart table “No Item is Added In Cart yet. Cart is
    Empty!!!”
*/
public class Problem1 {
    private WebDriver driver;

    @BeforeMethod
    public void initializeDriver() {
        driver = new WebDriverUtil().getDriver("CHROME");
    }

    @Test
    public void verifyMeriPustakAddToCart() {
        //open https://www.meripustak.com/ in Google Chrome
        driver.get(Environment.getValue("meripustak.url"));

        //Get and Print the width and height of the logo
        MPHeaderPage header = new MPHeaderPage(driver);
        Dimension size = header.getLogoDimension();
        System.out.println("Logo Height : " + size.height);
        System.out.println("Logo Width : " + size.width);

        //print the href of ‘twitter’ social media icon
        MPFooterPage footer = new MPFooterPage(driver);
        System.out.println("Twitter Logo HREF : " + footer.getTwitterHref());

        //Click on the shopping cart
        MPCartPage cartPage = header.goToCartPage();

        //Assert the message in the shopping cart table “No Item is Added In Cart yet. Cart is Empty!!!”
        String actualText = cartPage.getCartEmptyMesage();
        String expectedText = "No Item is Added In Cart yet.Cart is Empty!!!";
        Assert.assertEquals(actualText, expectedText, "Empty Cart Message on page does not match expected message");

        //Add anyone java book in cart
        MPSearchPage searchPage = header.searchBook("Java Ee Applications On Oracle Java Cloud");
        MPProductPage productPage = searchPage.waitForSearchResults()
                .goToBookByName("Java Ee Applications On Oracle Java Cloud");
        cartPage = productPage.clickAddToCart();

        //Verify if this message exists in the shopping cart table “No Item is Added In Cart yet. Cart is Empty!!!”
        Assert.assertFalse(cartPage.isEmptyCartMessageDisplayed(), "" +
                "Empty Cart message is displayed on the page.");
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }
}
