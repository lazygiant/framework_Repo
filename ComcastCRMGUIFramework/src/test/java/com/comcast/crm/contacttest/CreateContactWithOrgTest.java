package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;
import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;

public class CreateContactWithOrgTest {

	public static void main(String[] args) throws Throwable
	{
		//[READING] the [DATA] from ====[PROPERTIES FILE]====
		FileUtility fileUtility = new FileUtility();
		ExcelUtility excelUtility = new ExcelUtility();
		JavaUtility javaUtility = new JavaUtility();
		WebDriverUtility driverUtility = new WebDriverUtility();

		String Browser = fileUtility.getDataFromPropertiesFile("browser");
		String url = fileUtility.getDataFromPropertiesFile("url");
		String username = fileUtility.getDataFromPropertiesFile("username");
		String password = fileUtility.getDataFromPropertiesFile("password");

		System.out.println(Browser+" "+url+" "+username+" "+password);


		//[READING] the [TEST SCRIPT DATA] from xless file
		String lastName = excelUtility.getDataFromExcel("Sheet1", 7, 2);
		String orgName = excelUtility.getDataFromExcel("Sheet1", 7, 3)+javaUtility.getRandonNumber();


		//[SELENIUM] Program [EXECUTION]
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		WebDriver chromeDriver = null;

		if(Browser.equals("Chrome"))
		{
			chromeDriver = new ChromeDriver(option);
		}
		else if( Browser.equals("Firefox"))
		{
			chromeDriver=new FirefoxDriver();
		}
		else if( Browser.equals("edge"))
		{
			chromeDriver=new EdgeDriver();
		}

		chromeDriver.manage().window().maximize();
	    driverUtility.waitForPageToLoad(chromeDriver);
		chromeDriver.get(url);
		chromeDriver.findElement(By.name("user_name")).sendKeys(username);
		chromeDriver.findElement(By.name("user_password")).sendKeys(password);

		chromeDriver.findElement(By.id("submitButton")).click();

		//navigated to Oraganization done
		chromeDriver.findElement(By.linkText("Organizations")).click();

		//clicking on organization button
		chromeDriver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		//Filling the details
		chromeDriver.findElement(By.name("accountname")).sendKeys(orgName);
		//Saving the details
		chromeDriver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		Thread.sleep(3000);

		//navigated to Contact done
		chromeDriver.findElement(By.linkText("Contacts")).click();

		//clicking on Contact button
		chromeDriver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		//Filling the details
		chromeDriver.findElement(By.name("lastname")).sendKeys(lastName);

		//look-up window
		chromeDriver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		//Switch to child window
		driverUtility.switchToTabOnURL(chromeDriver, "module=Accounts");

		chromeDriver.findElement(By.name("search_text")).sendKeys(orgName);
		chromeDriver.findElement(By.name("search")).click();
		chromeDriver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();


		//Switch to parent wind 
		driverUtility.switchToTabOnURL(chromeDriver, "Contacts&action");


		//Verify Header phone Number into expected Result
		String headerInfo = chromeDriver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		if(headerInfo.equals(orgName))
		{
			System.out.println(orgName+" header verified == [PASS]");	
		}
		else
		{
			System.out.println(orgName+" header is not verified == [FAIL]");	
		}


	}

}
