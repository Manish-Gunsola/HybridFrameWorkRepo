package com.amelio.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[text()=\"My Account\"]")
	private WebElement myaccountDropMenu;
	
	@FindBy(xpath="//a[text()=\"Login\"]")
	private WebElement login;
	
	@FindBy(xpath="//ul[@class=\"dropdown-menu dropdown-menu-right\"]//a[text()=\"Register\"]")
	private WebElement registerOption;
	
	//Actions
	public void selectLoginOption() {
		login.click();
		
	}
	
	public void clickOnMyAccount() {
		myaccountDropMenu.click();
	}
	
	public void selectRegisterOption() {
		registerOption.click();
	}
	

}
