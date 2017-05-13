package com.secookbook.examples.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchPage {
	
	By googleInputLocator = By.id("lst-ib");
	By googleButtonLocator = By.name("btnK");
	private WebDriver driver;
	
	public GoogleSearchPage(WebDriver driver) {
		
		this.driver = driver;	
	}
	
	public void search() {
		
		
		WebElement input = driver.findElement(googleInputLocator);
		input.clear();
		input.sendKeys("Selenium");
		input.submit();
		
	/*	WebElement btn = driver.findElement(googleButtonLocator);
		isElementClickable(googleButtonLocator, 30);
		btn.click();*/
		
	}
	
	private void waitFor(ExpectedCondition<WebElement> condition, Integer timeout){
		timeout = timeout != null ? timeout : 5;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(condition);
		}
	
	 public Boolean isElementClickable(By locator, int maxWaitTime) {
		 try {
		 waitFor(ExpectedConditions.elementToBeClickable(locator), maxWaitTime);
		 } catch (Exception e) {
		 e.printStackTrace();
		 return false;
		 }
		 return true;
		 }
			

}
