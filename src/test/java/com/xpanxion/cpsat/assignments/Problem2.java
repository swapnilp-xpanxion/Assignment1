package com.xpanxion.cpsat.assignments;

import com.xpanxion.cpsat.configuration.Environment;
import com.xpanxion.cpsat.driver.WebDriverUtil;
import com.xpanxion.cpsat.pages.cii.CIIRegistrationPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/*
2) Using Junit and WebDriver script, open https://www.cii.in/OnlineRegistration.aspx in Firefox and do the below
    1. Select “Number of Attendees” as 3
    2. Assert the row count is 3
    3. Select 1st-row title as ‘Admiral’(Please note use different method for every selection)
    4. Select 2nd-row title as ‘CA’ (Please note use different method for every selection)
    5. Select 3rd-row title as ‘CS’(Please note use different method for every selection)
    6. Print all the options that are available in the title
*/
public class Problem2 {
    private WebDriver driver;

    @Before
    public void initializeDriver() {
        driver = new WebDriverUtil().getDriver("FIREFOX");
    }

    @Test
    public void verifyAttendees() {
        //open https://www.cii.in/OnlineRegistration.aspx in Firefox
        driver.get(Environment.getValue("cii.registration.url"));

        //Select “Number of Attendees” as 3
        CIIRegistrationPage registrationPage = new CIIRegistrationPage(driver);
        registrationPage.selectNumberOfAttendee(3);
        registrationPage.waitForNumOfAttendeeTableRowsToBe(3);

        //Select 1st-row title as ‘Admiral’ by using Value
        registrationPage.selectAttendee1TitleByValue("Admiral");

        //Select 2nd-row title as ‘CA’ by visible text
        registrationPage.selectAttendee2TitleByVisibleText("CA");

        //Select 3rd-row title as ‘CS’ using index
        registrationPage.selectAttendee3TitleByIndex(18);

        //Print all the options that are available in the title
        System.out.println("Options in Title: " + registrationPage.getTitleOptions());
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

}
