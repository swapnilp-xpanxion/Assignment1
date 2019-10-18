package com.xpanxion.cpsat.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected Wait<WebDriver> getWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
    }

    protected WebElement waitForElement(By by) {
        getWait().until(ExpectedConditions.elementToBeClickable(by));
        return driver.findElement(by);
    }

    protected List<WebElement> waitForElements(By by) {
        getWait().until(ExpectedConditions.elementToBeClickable(by));
        return driver.findElements(by);
    }

    protected void waitForElementsToBeVisible(By by) {
        getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    protected WebElement waitForElementsToBeClickable(By by) {
        getWait().until(ExpectedConditions.elementToBeClickable(by));
        return driver.findElement(by);
    }

    protected boolean isElementPresent(By by) {
        return driver.findElements(by).size() > 0;
    }

    public String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    public void switchToNextWindow(String parentWindow) {
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equalsIgnoreCase(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public void switchToWindow(String parentWindow) {
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (window.equalsIgnoreCase(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public void waitForNumberOfWindowsToBe(int i) {
        getWait().until(ExpectedConditions.numberOfWindowsToBe(i));
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    protected JavascriptExecutor getJavaScriptExecutor() {
        return (JavascriptExecutor) driver;
    }

    protected void scrollToCenter(By by) {
        getJavaScriptExecutor().executeScript("arguments[0].scrollIntoView({block: 'center'});", waitForElement(by));
    }
}
