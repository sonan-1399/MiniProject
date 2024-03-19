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

public class AutomationDemoSite {

	static WebDriver driver;
	static DriverUtils util;
	static Map<String, String> excelValues = new HashMap<>();

	@Test
	public static void scenario2() throws IOException
	{
		readExcelValues();
		skipSignIn();
		selectSwitchTo();
		swtParentFrame();
		txtInFrame();
		swtDefaultContent();
		selectWidgetsAndDate();
	}

	private static void readExcelValues() throws IOException {
		FileInputStream fs = new FileInputStream("C:/Users/snthadev/MiniProjectData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet = workbook.getSheetAt(0);

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

	@BeforeTest
	private static WebDriver OpenApplication() {
		System.setProperty("webdriver.chrome.exe", "C:/Users/snthadev/Documents/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://demo.automationtesting.in/Index.html");
		driver.manage().window().maximize();
		util = new DriverUtils(driver);
		System.out.println("Application Opened");
		return driver;
	}

	private static void skipSignIn() {
		util.Click(excelValues.get("skipSignIn"));
	}

	public static void selectSwitchTo() {
		util.Actions(excelValues.get("switchTo"), excelValues.get("frames"));
	}

	public static void swtParentFrame() {
		driver.switchTo().parentFrame();
	}

	public static void txtInFrame() {
		util.Click(excelValues.get("withInIframe"));
		util.SwitchToFrame(excelValues.get("parentFrame"));
		util.SwitchToFrame(excelValues.get("childFrame"));
		util.SendKeys(excelValues.get("textBox"), excelValues.get("textBox_Value"));
		System.out.println("Text has been inputted");
	}

	public static void swtDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public static void selectWidgetsAndDate() {
		util.Actions(excelValues.get("widgets"), excelValues.get("datePicker"));
		util.Click(excelValues.get("pickDate"));
		util.Select(excelValues.get("month"), excelValues.get("month_Value"));
		util.Click(excelValues.get("date"));
		System.out.println("Date has been selected");
	}

	@AfterTest
	public static void closeApplication() {
		driver.quit();
		System.out.println("Application closed");
	}
}
