package com.xpanxion.cpsat.pages.premierleague;

import com.xpanxion.cpsat.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PLHomePage extends BasePage {

    private By acceptCookiesButton = By.cssSelector(".btn-primary.cookies-notice-accept");
    private By advertCloseButton = By.id("advertClose");
    private By tablesLink = By.xpath(".//nav[@class='subNav']//a[text()='Tables']");

    public PLHomePage(WebDriver driver) {
        super(driver);
    }

    public PLHomePage closeAdvert() {
        waitForElement(advertCloseButton).click();
        return this;
    }

    public void acceptCookies() {
        waitForElement(acceptCookiesButton).click();
    }

    public PLTablesPage clickTablesMenu() {
        waitForElement(tablesLink).click();
        return new PLTablesPage(driver);
    }
}

