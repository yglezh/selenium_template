package com.secookbook.examples.chapter1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.secookbook.examples.page.GoogleSearchPage;

public class GoogleSearch1Test {
	private WebDriver driver;
	private GoogleSearchPage page;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		
	//System.setProperty("webdriver.firefox.marionette","./src/test/resources/geckodriver.exe");
	//driver = new FirefoxDriver();
		
		page = new GoogleSearchPage(driver);
		driver.get("https://www.google.com/");
	}

	@Test
	public void test() throws InterruptedException {
		page.search();
		Thread.sleep(3000);
		System.out.println(driver.getTitle());
		assertEquals("Selenium - Google Search", driver.getTitle());
	}
	
	@After
	public void tearDown() throws Exception {
	//	driver.close();
		
	}


}
