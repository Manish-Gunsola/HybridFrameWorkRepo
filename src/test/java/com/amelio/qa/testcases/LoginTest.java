package com.amelio.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amelio.qa.base.Base;
import com.amelio.qa.pages.AccountPage;
import com.amelio.qa.pages.HomePage;
import com.amelio.qa.pages.LoginPage;
import com.amelio.qa.utils.Utilities;

public class LoginTest extends Base {
	WebDriver driver;

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccount();
		homepage.selectLoginOption();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "validCredentialSupplier")
	public void verifyLoginWithValidCredentials(String email, String Password) {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(email);
		loginpage.enterPassword(Password);
		loginpage.clickOnLogin();

		AccountPage accountpage = new AccountPage(driver);

		Assert.assertTrue(accountpage.getDisplayStatusOfEditYourAccountInformationOption(),
				"Edit your link is not displayed");
	}

	@DataProvider(name = "validCredentialSupplier")
	public Object[][] supplyTestData() {
		// Object[][] data=
		// {{"gunsolamanish@yopmail.com","Artimanish@123"},{"amotooricap1@gmail.com","12345"},{"amotooricap1@gmail.com","12345"}};
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;

	}

	@Test(priority = 2)
	public void LoginWithInvalidEmailAndValidPasswordCredential() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginpage.enterPassword(prop.getProperty("validPass"));
		loginpage.clickOnLogin();

		String warningMessageActual = loginpage.reteriveemailPasswordNotMatchingWarningMessageText();

		Assert.assertEquals(warningMessageActual, dataProp.getProperty("emailPasswordNoMatchWarning"),
				"Waringn message not displayed");

	}

	@Test(priority = 3)
	public void LoginWithValidMailAndInvalidPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(prop.getProperty("validEmail"));
		loginpage.enterPassword(dataProp.getProperty("invalidPass"));
		loginpage.clickOnLogin();

		String warningMessageActual = loginpage.reteriveemailPasswordNotMatchingWarningMessageText();

		Assert.assertEquals(warningMessageActual, dataProp.getProperty("emailPasswordNoMatchWarning"),
				"Waringn message not displayed");

	}

	@Test(priority = 4)
	public void LoginWithEmptyUserNameAndPassword() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.clickOnLogin();

		String warningMessageActual = loginpage.reteriveemailPasswordNotMatchingWarningMessageText();

		Assert.assertEquals(warningMessageActual, dataProp.getProperty("emailPasswordNoMatchWarning"),
				"Waringn message not displayed");

	}

}
