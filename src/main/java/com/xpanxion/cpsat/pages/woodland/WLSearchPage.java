package com.xpanxion.cpsat.pages.woodland;

import com.xpanxion.cpsat.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class WLSearchPage extends BasePage {

    private By resultContainer = By.cssSelector("section.categoryProduct.category-product");
    private By highToLowFilter = By.xpath(".//input[@id='prcHTL_sort']//parent::li");
    private By mrp = By.cssSelector("span.mrp");

    WLSearchPage(WebDriver driver) {
        super(driver);
    }

    public void waitTillResultsDisplayed() {
        waitForElement(resultContainer);
    }

    public void applyHighToLowFilter() {
        waitForElement(highToLowFilter).click();
        waitTillResultsDisplayed();
    }

    public ArrayList<Integer> getPriceOfFirstEightProducts() {
        ArrayList<Integer> priceList = new ArrayList<>();
        int maxSize = 8;
        if (waitForElements(mrp).size() < 8) {
            maxSize = waitForElements(mrp).size();
        }
        for (int i = 0; i < maxSize; i++) {
            String price = waitForElements(mrp).get(i).getText().replace("R ", "").trim();
            priceList.add(Integer.parseInt(price));
        }
        return priceList;
    }
}
