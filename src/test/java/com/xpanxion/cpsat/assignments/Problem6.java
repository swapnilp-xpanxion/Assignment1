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

/*
6) Open in chrome browser https://www.woodlandworldwide.com/, now search for the items related to the
below-given product names.
    1. Test1: Use the product name “Bags”
    2. Test2: Use product name “Shoes” and
    3. Test3: Use the product name “Tshirts”.
    4. These product names are to be saved in an Excel file, read the product names from this file
    and pass to the search box.
    5. Select the filter ‘High to Low’ and test whether the first 8 products are in descending order
    of the price.
    6. Write a script for Google chrome in TestNG using WebDriver.
 */
public class Problem6 {
    private WebDriver driver;

    //Dataprovider to get product names from excel sheet
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
        //Open in chrome browser https://www.woodlandworldwide.com/
        driver.get(Environment.getValue("woodland.url"));

        //Search the product
        WLHomePage homePage = new WLHomePage(driver);
        WLSearchPage searchPage = homePage.searchProduct(productName);
        searchPage.waitTillResultsDisplayed();

        //Select the filter ‘High to Low’
        searchPage.applyHighToLowFilter();

        //test whether the first 8 products are in descending order of the price.
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
