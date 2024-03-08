package com.test.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverUtils {
private WebDriver driver;
	

	public DriverUtils(WebDriver driver) {		
		this.driver=driver;
	}
	public void Click(String xpath){
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		By by = By.xpath(xpath);
		wait.until(ExpectedConditions.elementToBeClickable(by));
		driver.findElement(by).click();
	}

	public void Select(String xpath , String Option){

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		By by = By.xpath(xpath);
		//wait.until(ExpectedConditions.elementToBeClickable(by));
		//driver.findElement(by).click();
		new Select(driver.findElement(by)).selectByVisibleText(Option);

	}

	public void SendKeys(String xpath , String input){

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		By by = By.xpath(xpath);
		wait.until(ExpectedConditions.elementToBeClickable(by));
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(input);

	}

	public String GetText(String xpath)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		By by=By.xpath(xpath);
		wait.until(ExpectedConditions.elementToBeClickable(by));
		return driver.findElement(by).getText();
	}

	public void Actions(String xpath , String xpath1) {

		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		By by=By.xpath(xpath);
		WebElement element = driver.findElement(by);
		
		By by1 = By.xpath(xpath1);
		WebElement element1 = driver.findElement(by1);

		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
		actions.moveToElement(element1).click().build().perform();		

	}

	public void SwitchToFrame(String xpath) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		By by=By.xpath(xpath);
		WebElement element = driver.findElement(by);
		driver.switchTo().frame(element);
	}
}
