package com.amelio.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amelio.qa.base.Base;
import com.amelio.qa.pages.HomePage;
import com.amelio.qa.utils.Utilities;

public class RegisterTest extends Base {

	public RegisterTest() {
		super();
	}
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homepage= new HomePage(driver);
		homepage.clickOnMyAccount();
		homepage.selectRegisterOption();
		
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void verifyRegistratingAccountWithMandotoryFields() {
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPass"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPass"));
		driver.findElement(By.xpath("//input[@type=\"checkbox\" and @value=\"1\"]")).click();
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		
		String actualConfirmedAccountCreationMessage=driver.findElement(By.xpath("//div[@id=\"content\"]//h1")).getText();
		Assert.assertEquals(actualConfirmedAccountCreationMessage, dataProp.getProperty("accountSuccessfullyCreatedHeading"),"account not created");
	}
	
	@Test(priority=2)
	public void verifyRegisteringAccountByProvidingAllFields() {
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPass"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPass"));
		driver.findElement(By.xpath("//input[@name=\"newsletter\" and @value=\"1\"]")).click();
		driver.findElement(By.xpath("//input[@type=\"checkbox\" and @value=\"1\"]")).click();
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		
		String actualConfirmedAccountCreationMessage=driver.findElement(By.xpath("//div[@id=\"content\"]//h1")).getText();
		Assert.assertEquals(actualConfirmedAccountCreationMessage, dataProp.getProperty("accountSuccessfullyCreatedHeading"),"account not created");
	}
	@Test(priority=3)
	public void verifyRegisterAccountWithExistingEmailAddress() {
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPass"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPass"));
		driver.findElement(By.xpath("//input[@name=\"newsletter\" and @value=\"1\"]")).click();
		driver.findElement(By.xpath("//input[@type=\"checkbox\" and @value=\"1\"]")).click();
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		
		String warningMessageForAlreadyExistMail=driver.findElement(By.xpath("//div[@class=\"alert alert-danger alert-dismissible\"]")).getText();
		System.out.println(warningMessageForAlreadyExistMail);
		Assert.assertEquals(warningMessageForAlreadyExistMail, dataProp.getProperty("duplicateEmailWarning"));
	}
	
	@Test(priority=4)
	public void verifyRegisterWithoutAnyDetails() {
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		
		String privacyPolicyWarningMessage=driver.findElement(By.xpath("//div[@class=\"alert alert-danger alert-dismissible\"]")).getText();
		Assert.assertTrue(privacyPolicyWarningMessage.contains(dataProp.getProperty("privacyPolicyWarning")));
		
		String firstNameWarningMessage=driver.findElement(By.xpath("//div[contains(text(),'First Name must be')]")).getText();
		Assert.assertTrue(firstNameWarningMessage.contains(dataProp.getProperty("firstNameWarning")));
		
		String lastNameWarningMessage=driver.findElement(By.xpath("//div[contains(text(),'Last Name must be')]")).getText();
		Assert.assertTrue(lastNameWarningMessage.contains(dataProp.getProperty("lastNameWarning")));
		
		String passwordWarningMessage=driver.findElement(By.xpath("//div[contains(text(),'Password must be')]")).getText();
		Assert.assertTrue(passwordWarningMessage.contains(dataProp.getProperty("passwordWarning")));
		
		
	}
	
}
