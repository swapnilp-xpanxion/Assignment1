package com.xpanxion.cpsat.pages.woodland;

import com.xpanxion.cpsat.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WLHomePage extends BasePage {

    private By searchIcon = By.cssSelector(".search-icon.searchIcon");
    private By searchBox = By.id("searchkey");
    private By searchButton = By.id("searchBtn");

    public WLHomePage(WebDriver driver) {
        super(driver);
    }

    public WLSearchPage searchProduct(String product) {
        waitForElement(searchIcon).click();
        waitForElement(searchBox).sendKeys(product);
        waitForElement(searchButton).click();
        return new WLSearchPage(driver);
    }
}
