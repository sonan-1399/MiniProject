package com.check;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class FrameNWidgetDemo {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.exe", "C:/Users/snthadev/Documents/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://demo.automationtesting.in/Index.html");
		driver.manage().window().maximize();
		
		WebElement skipSignIn = driver.findElement(By.xpath("//button[@type='button' and text()='Skip Sign In']"));
		skipSignIn.click();
		
		WebElement switchTo = driver.findElement(By.xpath("//li[@class='dropdown']//a[@data-toggle='dropdown' and text()='SwitchTo']"));
		WebElement frames = driver.findElement(By.xpath("//a[text()='Frames']"));

		
		Actions actions=new Actions(driver);
		actions.moveToElement(switchTo).perform();
		actions.moveToElement(frames).click().build().perform();
		
		driver.switchTo().parentFrame();
		
		WebElement withInIframe = driver.findElement(By.xpath("//div[@class='tabpane']//a[text()='Iframe with in an Iframe']"));
		withInIframe.click();

		
		WebElement parentFrame = driver.findElement(By.xpath("//iframe[@src='MultipleFrames.html']"));
		driver.switchTo().frame(parentFrame);
		
		WebElement childFrame = driver.findElement(By.xpath("//iframe[@src='SingleFrame.html']"));
		driver.switchTo().frame(childFrame);
		
		WebElement textBox = driver.findElement(By.xpath("//input[@type='text']"));
		textBox.clear();
		textBox.sendKeys("ABCDEFGH");
		
		driver.switchTo().defaultContent();
		
		//driver.switchTo().frame(parentFrame);
		
		WebElement widgets = driver.findElement(By.xpath("//li[@class='dropdown ']//a[text()='Widgets']"));
		WebElement datePicker = driver.findElement(By.xpath("//a[text()=' Datepicker ']"));

		actions.moveToElement(widgets).perform();
		actions.moveToElement(datePicker).click().build().perform();
		
		WebElement pickDate = driver.findElement(By.xpath("//input[@id='datepicker2']"));
		pickDate.click();
		
		WebElement month = driver.findElement(By.xpath("//select[@title='Change the month']"));
		Select monthSelect = new Select(month);
		monthSelect.selectByVisibleText("March");
		
//		WebElement year = driver.findElement(By.xpath("//select[@title='Change the month']"));
//		Select yearSelect = new Select(year);
//		yearSelect.selectByVisibleText("2024");
		
		WebElement date = driver.findElement(By.xpath("//table//a[text()='8']"));
		date.click();
		
		System.out.println("Operations performed");
				
		driver.quit();
		
	}

}
