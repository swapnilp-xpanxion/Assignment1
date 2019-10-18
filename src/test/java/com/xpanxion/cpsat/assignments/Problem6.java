package com.xpanxion.cpsat.assignments;

import com.xpanxion.cpsat.configuration.Environment;
import com.xpanxion.cpsat.driver.WebDriverUtil;
import com.xpanxion.cpsat.pages.woodland.WLHomePage;
import com.xpanxion.cpsat.pages.woodland.WLSearchPage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Problem6 {
    private WebDriver driver;

    @DataProvider(name = "getProductNames")
    public static Object[][] getProductNames() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src//test//resources//Woodland_TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet worksheet = workbook.getSheet("Sheet1");
        XSSFRow row = worksheet.getRow(0);
        int rownum = worksheet.getPhysicalNumberOfRows();
        int colnum = row.getLastCellNum();
        Object[][] data = new Object[rownum - 1][colnum];
        for (int i = 0; i < rownum - 1; i++) {
            XSSFRow tempRow = worksheet.getRow(i + 1);
            for (int j = 0; j < colnum; j++) {
                if (tempRow == null)
                    data[i][j] = "";
                else {
                    XSSFCell cell = tempRow.getCell(j);
                    if (cell == null)
                        data[i][j] = "";
                    else {
                        String value = new DataFormatter().formatCellValue(cell);
                        data[i][j] = value;
                    }
                }
            }
        }
        return data;
    }

    @BeforeMethod
    public void initializeDriver() {
        driver = new WebDriverUtil().getDriver("CHROME");
    }

    @Test(dataProvider = "getProductNames")
    public void verifySortOnWoodlandWebsite(String productName) {
        driver.get(Environment.getValue("woodland.url"));
        WLHomePage homePage = new WLHomePage(driver);
        WLSearchPage searchPage = homePage.searchProduct(productName);
        searchPage.waitTillResultsDisplayed();
        searchPage.applyHighToLowFilter();
        ArrayList<Integer> actualList = searchPage.getPriceOfFirstEightProducts();
        ArrayList<Integer> expectedList = new ArrayList<>(actualList);
        Collections.sort(expectedList);
        Collections.reverse(expectedList);
        Assert.assertEquals(actualList, expectedList, "Products are not sorted by Price: High to Low. " +
                "Actual List:" + actualList);
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }
}
