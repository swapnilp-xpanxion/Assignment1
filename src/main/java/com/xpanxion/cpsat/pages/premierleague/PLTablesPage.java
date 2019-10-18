package com.xpanxion.cpsat.pages.premierleague;

import com.xpanxion.cpsat.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PLTablesPage extends BasePage {

    private By advertCloseButton = By.id("advertClose");
    private By aresenalLink = By.cssSelector("tr[data-filtered-table-row-name='Arsenal'] > td.team > a");

    public PLTablesPage(WebDriver driver) {
        super(driver);
    }

    public void closeAdvert() {
        waitForElement(advertCloseButton).click();
    }

    public void openArsenalInNewWindow() throws Exception {
        scrollToCenter(aresenalLink);
        Actions actions = new Actions(driver);
        actions.moveToElement(waitForElement(aresenalLink)).contextClick(waitForElement(aresenalLink)).perform();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}

