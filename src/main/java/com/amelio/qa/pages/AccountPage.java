package com.amelio.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
	WebDriver driver;
	
	@FindBy(xpath="//div[@id='content']/h1")
	private WebElement accountScucessPageHeading;
	
	@FindBy(xpath="//a[text()=\"Edit your account information\"]")
	private WebElement editYourAccountInfoLink;
	
	public AccountPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean getDisplayStatusOfEditYourAccountInformationOption() {
		boolean displayStatus=editYourAccountInfoLink.isDisplayed();
		return displayStatus;
	}
	
	public String getTextOfaccountSuccessPage() {
		return accountScucessPageHeading.getText();
		
	}
	
	


}
