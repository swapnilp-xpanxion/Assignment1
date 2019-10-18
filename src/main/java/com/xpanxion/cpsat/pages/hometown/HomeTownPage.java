package com.xpanxion.cpsat.pages.hometown;

import com.xpanxion.cpsat.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class HomeTownPage extends BasePage {

    private By noThanksButton = By.cssSelector("#onesignal-popover-cancel-button");
    private By electronicsLink = By.cssSelector("a[title='Electronics']");
    private By colorButton = By.xpath(".//button[text()='Color']");
    private By blackColorFilter = By.xpath(".//button[text()='Color']//following-sibling::div//label[text()='Black']");
    private By firstProduct = By.xpath(".//div[contains(@class,'ProductWrapper')]");
    private By quickViewButton = By.xpath(".//button[contains(text(),'QUICK VIEW')]");
    private By productModal = By.xpath(".//div[contains(@class,'styles_modal')]");
    private By productNameOnModal = By.xpath(".//div[contains(@class,'styles_modal')]//h1/a");
    private By closeModalButton = By.xpath(".//button[contains(@class,'styles_closeButton')]");
    private By appliedFilters = By.xpath(".//label[text()='Applied Filters']//following-sibling::div//li");

    public HomeTownPage(WebDriver driver) {
        super(driver);
    }

    public void clickNoThanksButton() {
        waitForElementsToBeClickable(noThanksButton).click();
    }

    public void clickElectronicsLink() {
        getJavaScriptExecutor().executeScript("arguments[0].click()", waitForElement(electronicsLink));
    }

    public void applyBlackColorFilter() {
        Actions actions = new Actions(driver);
        actions.moveToElement(waitForElementsToBeClickable(colorButton)).perform();
        actions.moveToElement(waitForElement(blackColorFilter)).click().perform();
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(blackColorFilter));
    }

    public void clickQuickViewOfFirstProduct() {
        Actions actions = new Actions(driver);
        actions.moveToElement(waitForElement(firstProduct)).perform();
        actions.click(waitForElement(quickViewButton)).perform();
    }

    public String getProductNameFromModal() {
        waitForElementsToBeVisible(productModal);
        return waitForElement(productNameOnModal).getText();
    }

    public void closeModal() {
        waitForElement(closeModalButton).click();
    }

    public List<String> getAppliedFilters() {
        List<WebElement> filters = waitForElements(appliedFilters);
        ArrayList<String> list = new ArrayList<>();
        for (WebElement element : filters) {
            list.add(element.getText());
        }
        return list;
    }
}

