package com.xpanxion.cpsat.pages.meripustak;

import com.xpanxion.cpsat.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MPFooterPage extends BasePage {

    private By twitterLink = By.xpath(".//i[contains(@class,'fa-twitter')]//parent::a");

    public MPFooterPage(WebDriver driver) {
        super(driver);
    }

    public String getTwitterHref() {
        return waitForElement(twitterLink).getAttribute("href");
    }
}
