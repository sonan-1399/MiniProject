package com.check;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CpuValueCheckDemo {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.exe", "C:/Users/snthadev/Documents/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://practice.expandtesting.com/dynamic-table");
		driver.manage().window().maximize();
		
		WebElement cpuTableValue = driver.findElement(By.xpath("(//td[text()='Chrome']//following::td[contains(text(),'%')])[1]"));
		String actual = cpuTableValue.getText();
		System.out.println(actual);
		
		WebElement cpuHighLtValue = driver.findElement(By.xpath("//p[@id='chrome-cpu']"));
		String temp = cpuHighLtValue.getText();
		System.out.println(temp);
			
		driver.quit();
		
	}

}
