package com.amelio.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@type=\"submit\"]")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[@class=\"alert alert-danger alert-dismissible\"]")
	private WebElement emailPasswordNotMatchingWarning;
	
	//Actions
	public void enterEmailAddress(String email) {
		emailAddressField.sendKeys(email);
	}
	
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	
	public void clickOnLogin() {
		loginButton.click();
	}
	
	public String reteriveemailPasswordNotMatchingWarningMessageText() {
		String warningText=emailPasswordNotMatchingWarning.getText();
		return warningText;
	}

}
