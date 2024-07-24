package com.amelio.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-firstname")
	WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	WebElement lastNameField;
	
	@FindBy(id="input-email")
	WebElement emailfield;
	
	@FindBy(id="input-telephone")
	WebElement telephoneField;
	
	@FindBy(id="input-password")
	WebElement passwordfield;
	
	@FindBy(id="input-confirm")
	WebElement passwordConfirmField;
	
	@FindBy(xpath="//input[@type=\"checkbox\" and @value=\"1\"]")
	WebElement privacypolicyField;
	
	@FindBy(xpath="//input[@name=\"newsletter\" and @value=\"1\"]")
	WebElement slectNewsLetterYes;
	
	@FindBy(xpath="//input[@type=\"submit\"]")
	WebElement continueButton;
	
	@FindBy(xpath="//div[@class=\"alert alert-danger alert-dismissible\"]")
	WebElement duplicateEmailAddresswarning;
	
	//Actins
	
	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}
	
	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}
	
	public void enterEmailAddress(String emailAddress) {
		emailfield.sendKeys(emailAddress);
	}
	
	public void enterTelephone(String telePhoneText) {
		telephoneField.sendKeys(telePhoneText);
	}
	
	public void enterPassword(String passwordText) {
		passwordfield.sendKeys(passwordText);
	}
	
	public void enterConfirmPassword(String confirmPasswordText) {
		passwordConfirmField.sendKeys(confirmPasswordText);
	}

	public void selectPrivacypolicyButton() {
		privacypolicyField.click();
	}
	
	public void selectSubmitButton() {
		continueButton.click();
	}
	
	public void selectyesForNewsLetter() {
		slectNewsLetterYes.click();
	}
	
	public String getDuplicateEmailText() {
		return duplicateEmailAddresswarning.getText();
	}
}
