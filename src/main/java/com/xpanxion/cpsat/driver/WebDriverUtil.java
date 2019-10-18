package com.xpanxion.cpsat.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverUtil {

    private WebDriver driver;

    public WebDriver getDriver(String browser) {
        if (driver == null) {
            switch (browser.toUpperCase()) {
                case "CHROME":
                    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
                    driver = new ChromeDriver();
                    break;
                case "FIREFOX":
                    System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new RuntimeException("Invalid Browser Name: " + browser);
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }

}
