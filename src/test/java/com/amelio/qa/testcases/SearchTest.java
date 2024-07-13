package com.amelio.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amelio.qa.base.Base;

public class SearchTest extends Base {
	
	public SearchTest() {
		super();
	}
	
	WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct"));
		driver.findElement(By.xpath("//button[@class=\"btn btn-default btn-lg\"]")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()=\"HP LP3065\"]")).isDisplayed());

	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidProduct"));
		driver.findElement(By.xpath("//button[@class=\"btn btn-default btn-lg\"]")).click();

		String actualSearchMessage = driver.findElement(By.xpath("//div[@id=\"content\"]/h2/following-sibling::p"))
				.getText();
		Assert.assertEquals(actualSearchMessage, dataProp.getProperty("noProductTextInSearchResult"));
	}

	@Test(priority = 3)
	public void verifySearchWithNoProduct() {
		driver.findElement(By.xpath("//button[@class=\"btn btn-default btn-lg\"]")).click();

		String actualSearchMessage = driver.findElement(By.xpath("//div[@id=\"content\"]/h2/following-sibling::p"))
				.getText();
		
		Assert.assertEquals(actualSearchMessage, dataProp.getProperty("noProductTextInSearchResult"));
	}

}
