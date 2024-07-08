package com.comcast.crm.contacttest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;

public class CreateContactWithSupportDateTestWithFull_CODE {

	public static void main(String[] args) throws Throwable
	{
		//[READING] the [DATA] from ====[PROPERTIES FILE]====
		FileUtility fileUtility = new FileUtility();
		ExcelUtility excelUtility = new ExcelUtility();
		JavaUtility javaUtility = new JavaUtility();

		String Browser = fileUtility.getDataFromPropertiesFile("browser");
		String url = fileUtility.getDataFromPropertiesFile("url");
		String username = fileUtility.getDataFromPropertiesFile("username");
		String password = fileUtility.getDataFromPropertiesFile("password");

		System.out.println(Browser+" "+url+" "+username+" "+password);

		//[READING] the [TEST SCRIPT DATA] from xless file
		String lastName = excelUtility.getDataFromExcel("Sheet1", 7, 2)+javaUtility.getRandonNumber();


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
		chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		chromeDriver.get(url);
		chromeDriver.findElement(By.name("user_name")).sendKeys(username);
		chromeDriver.findElement(By.name("user_password")).sendKeys(password);
		chromeDriver.findElement(By.id("submitButton")).click();

		//navigated to Oraganization done
		chromeDriver.findElement(By.linkText("Contacts")).click();

		//clicking on organization button
		chromeDriver.findElement(By.xpath("//img[@title='Create Contact...']")).click();


		//[JAVA BASICS] -> [TO GET THE CURRENT DATA AND 30 DAYS AFTER DATE]
		String startDate = javaUtility.getSystemDataYYYYMMDD();
		
		// GET Date After 30 DAYS
		String endDate = javaUtility.getRequiredDataYYYYMMDD(30);
		

		//Filling the details
		chromeDriver.findElement(By.name("lastname")).sendKeys(lastName);


		// Getting Start-Date
		chromeDriver.findElement(By.name("support_start_date")).clear();
		chromeDriver.findElement(By.name("support_start_date")).sendKeys(startDate);

		// Getting End-Date
		chromeDriver.findElement(By.name("support_end_date")).clear();
		chromeDriver.findElement(By.name("support_end_date")).sendKeys(endDate);


		//Saving the details
		chromeDriver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		//Validation for Contact, StartDate and EndDate
		String actualLastName= chromeDriver.findElement(By.id("dtlview_Last Name")).getText();

		if(actualLastName.equals(lastName))
		{
			System.out.println(lastName+ " lastname is verifed");
		}
		else
		{
			System.out.println(lastName+ " lastname is not verifed");
		}

		String startDate_ = chromeDriver.findElement(By.id("dtlview_Support Start Date")).getText();

		if(startDate.equals(startDate_))
		{
			System.out.println(startDate_+ " is verifed");
		}
		else
		{
			System.out.println(startDate_+ " is not verifed");
		}

		String endDate_ = chromeDriver.findElement(By.id("dtlview_Support End Date")).getText();

		if(endDate.equals(endDate_))
		{
			System.out.println(endDate_+ " is verifed");
		}
		else
		{
			System.out.println(endDate_+ " is not verifed");
		}
	}
}
