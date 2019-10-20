package com.xpanxion.cpsat.assignments;

import com.xpanxion.cpsat.configuration.Environment;
import com.xpanxion.cpsat.driver.WebDriverUtil;
import com.xpanxion.cpsat.pages.premierleague.PLArsenalPage;
import com.xpanxion.cpsat.pages.premierleague.PLHomePage;
import com.xpanxion.cpsat.pages.premierleague.PLTablesPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/*
3) Using Junit and WebDriver script, open https://www.premierleague.com/ in Firefox and do the below
    1. Click on Tables from the header
    2. From the tables, open ‘Arsenal’ club in a new window via context-click
    3. Find official website URL on the page and print on the console from the newly opened
    window
    4. Print the page title of the newly opened window
    5. Go back to the main window
    6. Print the page title again
 */
public class Problem3 {
    private WebDriver driver;

    @Before
    public void initializeDriver() {
        driver = new WebDriverUtil().getDriver("FIREFOX");
    }

    @Test
    public void verifyPageTitles() throws Exception {

        //open https://www.premierleague.com/ in Firefox
        driver.get(Environment.getValue("premierleague.url"));
        PLHomePage premierLeaguePage = new PLHomePage(driver);
        premierLeaguePage.closeAdvert().acceptCookies();

        //Click on Tables from the header
        PLTablesPage tablesPage = premierLeaguePage.clickTablesMenu();
        tablesPage.closeAdvert();

        //From the tables, open ‘Arsenal’ club in a new window via context-click
        String parentWindow = tablesPage.getCurrentWindowHandle();
        tablesPage.openArsenalInNewWindow();
        tablesPage.waitForNumberOfWindowsToBe(2);
        tablesPage.switchToNextWindow(parentWindow);
        PLArsenalPage arsenalPage = new PLArsenalPage(driver);

        //Find official website URL on the page and print on the console from the newly opened window
        System.out.println("Official WebSite URL:" + arsenalPage.getOfficialWebSiteURL());

        //Print the page title of the newly opened window
        System.out.println("New Window Page Title:" + arsenalPage.getPageTitle());

        //Go back to the main window
        arsenalPage.switchToWindow(parentWindow);

        //Print the page title
        System.out.println("Main Window Page Title:" + arsenalPage.getPageTitle());
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

}
