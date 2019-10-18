package com.xpanxion.cpsat.pages.meripustak;

import com.xpanxion.cpsat.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MPCartPage extends BasePage {

    private By emptyCartMessage = By.cssSelector(".book_description.box_border h4");

    MPCartPage(WebDriver driver) {
        super(driver);
    }

    public String getCartEmptyMesage() {
        return waitForElement(emptyCartMessage).getText();
    }

    public boolean isEmptyCartMessageDisplayed() {
        return isElementPresent(emptyCartMessage);
    }
}
