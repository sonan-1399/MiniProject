package com.check;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.utils.DriverUtils;

public class CPUValueTest {
	
	static WebDriver driver;
	static DriverUtils util;
	static Map<String, String> excelValues = new HashMap<>();
	//static String cpuHighLtValue = "//p[@id='chrome-cpu']";
	//static String cpuTableValue = "(//td[text()='Chrome']//following::td[contains(text(),'%')])[1]";
	

	@BeforeTest
	private static WebDriver OpenApplication() {
		System.setProperty("webdriver.chrome.exe", "C:/Users/snthadev/Documents/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://practice.expandtesting.com/dynamic-table");
		driver.manage().window().maximize();
		util = new DriverUtils(driver);
		System.out.println("Application Opened");
		return driver;
	}
	
	@Test
	public static void Scenario4() throws IOException {
		readExcelValues();
		GetCPUValue();
	}
	
	private static void readExcelValues() throws IOException {
        FileInputStream fs = new FileInputStream("C:/Users/snthadev/MiniProjectData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(1);
 
        for (Row row : sheet) {
            String element = row.getCell(0).getStringCellValue();
            Cell valueCell = row.getCell(1);
 
            excelValues.put(element, getStringCellValue(valueCell));
        }
        workbook.close();
    }
	
	private static String getStringCellValue(Cell cell) {
        if (cell == null) {
            return null;
        } else if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            // here converting numeric value to string
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return null;
        }
    }	
	
	public static void GetCPUValue() {
		String temp = util.GetText(excelValues.get("cpuTableValue"));
		String expected = "Chrome CPU: " +temp;
		String actual = util.GetText(excelValues.get("cpuHighLtValue"));
		Assert.assertEquals(actual, expected, "CPU Load values are different");		
	}

	@AfterTest
	public static void CloseApplication() {
		driver.quit();
		System.out.println("Application closed");
	}
}
