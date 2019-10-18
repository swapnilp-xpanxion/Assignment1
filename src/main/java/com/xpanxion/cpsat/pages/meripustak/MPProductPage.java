package com.xpanxion.cpsat.pages.meripustak;

import com.xpanxion.cpsat.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MPProductPage extends BasePage {

    private By addToCartButton = By.id("ContentPlaceHolder1_AddtoCart");

    MPProductPage(WebDriver driver) {
        super(driver);
    }

    public MPCartPage clickAddToCart() {
        waitForElement(addToCartButton).click();
        return new MPCartPage(driver);
    }
}
