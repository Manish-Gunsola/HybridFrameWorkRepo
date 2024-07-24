package com.amelio.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amelio.qa.base.Base;
import com.amelio.qa.pages.AccountPage;
import com.amelio.qa.pages.HomePage;
import com.amelio.qa.pages.RegisterPage;
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
	
	@Test(priority=1)
	public void verifyRegistratingAccountWithMandotoryFields() throws InterruptedException {
		RegisterPage rp= new RegisterPage(driver);
		rp.enterFirstName(dataProp.getProperty("firstName"));
		rp.enterLastName(dataProp.getProperty("lastName"));
		rp.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		rp.enterTelephone(dataProp.getProperty("telephoneNumber"));
		rp.enterPassword(prop.getProperty("validPass"));
		rp.enterConfirmPassword(prop.getProperty("validPass"));
		rp.selectPrivacypolicyButton();
		rp.selectSubmitButton();
		
		AccountPage ap= new AccountPage(driver);
		String actualConfirmedAccountCreationMessage=ap.getTextOfaccountSuccessPage();
		Assert.assertEquals(actualConfirmedAccountCreationMessage, dataProp.getProperty("accountSuccessfullyCreatedHeading"),"account not created");
	}
	
	@Test(priority=2)
	public void verifyRegisteringAccountByProvidingAllFields() {
		RegisterPage rp= new RegisterPage(driver);
		rp.enterFirstName(dataProp.getProperty("firstName"));
		rp.enterLastName(dataProp.getProperty("lastName"));
		rp.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		rp.enterTelephone(dataProp.getProperty("telephoneNumber"));
		rp.enterPassword(prop.getProperty("validPass"));
		rp.enterConfirmPassword(prop.getProperty("validPass"));
		rp.selectyesForNewsLetter();
		rp.selectPrivacypolicyButton();
		rp.selectSubmitButton();
		
		AccountPage ap= new AccountPage(driver);
		String actualConfirmedAccountCreationMessage=ap.getTextOfaccountSuccessPage();
		Assert.assertEquals(actualConfirmedAccountCreationMessage, dataProp.getProperty("accountSuccessfullyCreatedHeading"),"account not created");
	}
	@Test(priority=3)
	public void verifyRegisterAccountWithExistingEmailAddress() throws InterruptedException {
		RegisterPage rp= new RegisterPage(driver);
		rp.enterFirstName(dataProp.getProperty("firstName"));
		rp.enterLastName(dataProp.getProperty("lastName"));
		rp.enterEmailAddress(prop.getProperty("validEmail"));
		rp.enterTelephone(dataProp.getProperty("telephoneNumber"));
		rp.enterPassword(prop.getProperty("validPass"));
		rp.enterConfirmPassword(prop.getProperty("validPass"));
		rp.selectPrivacypolicyButton();
		rp.selectSubmitButton();
		
		String warningMessageForAlreadyExistMail=rp.getDuplicateEmailText();
		System.out.println(warningMessageForAlreadyExistMail);
		Assert.assertEquals(warningMessageForAlreadyExistMail, dataProp.getProperty("duplicateEmailWarning"));
	}
	
	@Test(priority=4)
	public void verifyRegisterWithoutAnyDetails() {
		RegisterPage rp= new RegisterPage(driver);
		rp.selectSubmitButton();
		
		
		
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
