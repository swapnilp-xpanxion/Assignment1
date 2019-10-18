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

public class Problem3 {
    private WebDriver driver;

    @Before
    public void initializeDriver() {
        driver = new WebDriverUtil().getDriver("FIREFOX");
    }

    @Test
    public void verifyPageTitles() throws Exception {
        driver.get(Environment.getValue("premierleague.url"));
        PLHomePage premierLeaguePage = new PLHomePage(driver);
        premierLeaguePage.closeAdvert().acceptCookies();
        PLTablesPage tablesPage = premierLeaguePage.clickTablesMenu();
        tablesPage.closeAdvert();
        String parentWindow = tablesPage.getCurrentWindowHandle();
        tablesPage.openArsenalInNewWindow();
        tablesPage.waitForNumberOfWindowsToBe(2);
        tablesPage.switchToNextWindow(parentWindow);
        PLArsenalPage arsenalPage = new PLArsenalPage(driver);
        System.out.println("Official WebSite URL:" + arsenalPage.getOfficialWebSiteURL());
        System.out.println("New Window Page Title:" + arsenalPage.getPageTitle());
        arsenalPage.switchToWindow(parentWindow);
        System.out.println("Main Window Page Title:" + arsenalPage.getPageTitle());
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

}
