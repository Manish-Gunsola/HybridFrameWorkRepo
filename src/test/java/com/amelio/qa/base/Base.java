package com.amelio.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.amelio.qa.utils.Utilities;

public class Base {
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	public  Base() {
		
		dataProp= new Properties();
		File datFile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\amelio\\qa\\testdata\\testdata.properties");
		try {
		FileInputStream fis2= new FileInputStream(datFile);
		dataProp.load(fis2);
		}catch(Throwable e) {
			e.getStackTrace();
		}
		
		
		 prop = new Properties();
		File propFile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\amelio\\qa\\config\\config.properties");
		try {
		FileInputStream fis= new FileInputStream(propFile);
		prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	
	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {
		
		if(browserName.equals("edge")) {
			driver= new EdgeDriver();
		}else if(browserName.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(browserName.equals("fireFox")) {
			driver=new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}

}
