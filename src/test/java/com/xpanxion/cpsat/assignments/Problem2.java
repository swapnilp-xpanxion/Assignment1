package com.xpanxion.cpsat.assignments;

import com.xpanxion.cpsat.configuration.Environment;
import com.xpanxion.cpsat.driver.WebDriverUtil;
import com.xpanxion.cpsat.pages.cii.CIIRegistrationPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class Problem2 {
    private WebDriver driver;

    @Before
    public void initializeDriver() {
        driver = new WebDriverUtil().getDriver("FIREFOX");
    }

    @Test
    public void verifyAttendees() {
        driver.get(Environment.getValue("cii.registration.url"));
        CIIRegistrationPage registrationPage = new CIIRegistrationPage(driver);
        registrationPage.selectNumberOfAttendee(3);
        registrationPage.waitForNumOfAttendeeTableRowsToBe(3);
        registrationPage.selectAttendee1TitleByValue("Admiral");
        registrationPage.selectAttendee2TitleByVisibleText("CA");
        registrationPage.selectAttendee3TitleByIndex(18);
        System.out.println("Options in Title: " + registrationPage.getTitleOptions());
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

}
