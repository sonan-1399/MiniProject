package com.check;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.test.utils.DriverUtils;

public class CPUValueTest {
	static WebDriver driver;
	static Map<String, String> excelValues = new HashMap<>();
	static DriverUtils util;

	@BeforeTest
	private static void openApplication() {
		util = new DriverUtils(driver);
		util.detailsToOpenApp("https://practice.expandtesting.com/dynamic-table");
	}

	@Test
	public static void scenario4() throws IOException {
		readExcelValues();
		getCPUValue();
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
			return String.valueOf(cell.getNumericCellValue());
		} else {
			return null;
		}
	}	

	public static void getCPUValue() {
		String temp = util.getText(excelValues.get("cpuTableValue"));
		String expected = "Chrome CPU: " +temp;
		String actual = util.getText(excelValues.get("cpuHighLtValue"));
		Assert.assertEquals(actual, expected, "CPU Load values are different");		
	}

	@AfterTest
	public static void closeApplication() {
		util.closeApp();
	}
}
