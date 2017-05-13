package com.secookbook.examples.page;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Base {
	
	private WebDriver driver;
	private List<Error> verificationErrors = new ArrayList<Error>();
	
	public Base(WebDriver driver){
		
		this.driver = driver;
	}
	
	public WebDriver getDriver(){
		return driver;
	}
	
	public WebDriver chromeDriverConnection(){
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}
	
	public WebDriver firefoxDriverConnection(){
		System.setProperty("webdriver.firefox.marionette","./src/test/resources/drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		return driver;
	}
	
	public String getText(By locator){
		return driver.findElement(locator).getText();
	}
	
	public String getText(WebElement element){
		return element.getText();
	}
	
	public Boolean containsText(By locator,String text){
		return driver.findElement(locator).getText().contains(text);
	}
	
	public boolean addError(Error e) {
		return verificationErrors.add(e);
	}
	
	public void visit(String url){
		driver.get(url);
	}
	
	public WebElement findElement(By locator){
		return driver.findElement(locator);
	}
	
	public List <WebElement> findElements(By locator){
		return driver.findElements(locator);
	}
	
	public void click(By locator){
		findElement(locator).click();
	}
	
	public void click(WebElement element){
		element.click();
	}
	
	public void type(String inputText, By locator){
		findElement(locator).sendKeys(inputText);
	}
	
	public void clear(By locator){
		findElement(locator).clear();
	}
	
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	public TargetLocator switchTo() {
		return driver.switchTo();
	}
	
	public Boolean isDisplayed(By locator){
		try {
			return findElement(locator).isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException exception) {
			return false;
		}
	}
	
	public Boolean isDisplayed(By locator, int maxWaitTime){
		try {
			waitFor(ExpectedConditions.visibilityOfElementLocated(locator), maxWaitTime);
		} catch (org.openqa.selenium.TimeoutException exception) {
			return false;
		}
		return true;
	}
	
	public Boolean isDisplayed(WebElement element, int maxWaitTime){
		try {
			waitFor(ExpectedConditions.visibilityOf(element), maxWaitTime);
		} catch (org.openqa.selenium.TimeoutException exception) {
			return false;
		}
		return true;
	}
	
	public Boolean isElementClickable(By locator, int maxWaitTime){
		try {
			waitFor(ExpectedConditions.elementToBeClickable(locator), maxWaitTime);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Boolean isElementClickable(WebElement element, int maxWaitTime){
		try {
			waitFor(ExpectedConditions.elementToBeClickable(element), maxWaitTime);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean verify_element(By by, String text){
		boolean var = false;
		try {
			if (isElementPresent(by) && (findElement(by).getText().equals(text))) {
				var = true;
			}

		} catch (Error e) {
			addError(e);
			var = false;
		}
		return var;
	}
	
	private void waitFor(ExpectedCondition<WebElement> condition, Integer timeout){
		timeout = timeout != null ? timeout : 5;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(condition);
	}
	
	public Options manage(){
		return driver.manage();
	}
	
	public void maximize(){
		manage().window().maximize();
	}
	
	public void takePage_ScreenShot(String fileDestinationd) throws Exception{
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(fileDestinationd));
	}
	
	public String getCurrentDate(){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("M/dd/yyyy");
		System.out.println("Current date:" + format.format(date));
		return format.format(date);
	}
	public int getCurrentDateDay(){
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
}
