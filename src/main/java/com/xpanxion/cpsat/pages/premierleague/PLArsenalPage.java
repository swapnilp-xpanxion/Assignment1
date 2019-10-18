package com.xpanxion.cpsat.pages.premierleague;

import com.xpanxion.cpsat.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PLArsenalPage extends BasePage {

    private By officialWebSiteURL = By.cssSelector(".website a");

    public PLArsenalPage(WebDriver driver) {
        super(driver);
    }

    public String getOfficialWebSiteURL() {
        return waitForElement(officialWebSiteURL).getText();
    }
}

