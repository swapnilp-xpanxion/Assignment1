package com.xpanxion.cpsat.pages.meripustak;

import com.xpanxion.cpsat.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MPSearchPage extends BasePage {

    private By bookName = By.cssSelector(".book_list_name a");
    private By bookList = By.id("book_list");

    MPSearchPage(WebDriver driver) {
        super(driver);
    }

    public MPSearchPage waitForSearchResults(){
        waitForElementsToBeVisible(bookList);
        return this;
    }

    public MPProductPage goToBookByName(String name) {
        List<WebElement> bookNames = waitForElements(bookName);
        for(WebElement bookName: bookNames){
            if(bookName.getText().equalsIgnoreCase(name)){
                bookName.click();
                break;
            }
        }
        return new MPProductPage(driver);
    }
}
