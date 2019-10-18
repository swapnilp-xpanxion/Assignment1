package com.xpanxion.cpsat.pages.meripustak;

import com.xpanxion.cpsat.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class MPHeaderPage extends BasePage {

    private By logo = By.cssSelector("#mpstkLogo img");
    private By shoppingCartLink = By.cssSelector(".shopping_cart a");
    private By searchBox = By.cssSelector(".search_box #txtsearch");
    private By searchButton = By.cssSelector(".search_box #btnsearch");


    public MPHeaderPage(WebDriver driver) {
        super(driver);
    }

    public Dimension getLogoDimension() {
        return waitForElement(logo).getSize();
    }

    public MPCartPage goToCartPage(){
        waitForElement(shoppingCartLink).click();
        return new MPCartPage(driver);
    }

    public MPSearchPage searchBook(String name){
        waitForElement(searchBox).sendKeys(name);
        waitForElement(searchButton).click();
        return new MPSearchPage(driver);
    }
}
