package com.xpanxion.cpsat.pages.cii;

import com.xpanxion.cpsat.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CIIRegistrationPage extends BasePage {

    private By numOfAttendeeDropdown = By.id("drpAttendee");
    private By attendeeTableRow = By.xpath(".//table[@id='Gridview1']/tbody/tr");
    private By attendee1TitleDropdown = By.id("Gridview1_ctl02_drpTitle");
    private By attendee2TitleDropdown = By.id("Gridview1_ctl03_drpTitle");
    private By attendee3TitleDropdown = By.id("Gridview1_ctl04_drpTitle");

    public CIIRegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void selectNumberOfAttendee(int i) {
        Select dropdown = new Select(waitForElement(numOfAttendeeDropdown));
        dropdown.selectByValue(String.valueOf(i));
    }

    public void waitForNumOfAttendeeTableRowsToBe(int i) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(driver -> waitForElements(attendeeTableRow).size() == i + 1);
    }

    public void selectAttendee1TitleByValue(String title) {
        Select dropdown = new Select(waitForElement(attendee1TitleDropdown));
        dropdown.selectByValue(title);
    }

    public void selectAttendee2TitleByVisibleText(String title) {
        Select dropdown = new Select(waitForElement(attendee2TitleDropdown));
        dropdown.selectByVisibleText(title);
    }

    public void selectAttendee3TitleByIndex(int index) {
        Select dropdown = new Select(waitForElement(attendee3TitleDropdown));
        dropdown.selectByIndex(index);
    }

    public List<String> getTitleOptions() {
        Select dropdown = new Select(waitForElement(attendee1TitleDropdown));
        List<WebElement> options = dropdown.getOptions();
        ArrayList<String> list = new ArrayList<>();
        for (WebElement element : options) {
            list.add(element.getText());
        }
        return list;
    }
}

