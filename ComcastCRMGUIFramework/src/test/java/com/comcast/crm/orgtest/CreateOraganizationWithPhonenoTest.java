package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
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
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;

public class CreateOraganizationWithPhonenoTest {

	public static void main(String[] args) throws Throwable
	{
		//[Utility] --> [OBJECT]
		FileUtility fileUtility = new FileUtility();
		ExcelUtility excelUtility = new ExcelUtility();
		JavaUtility javaUtility = new JavaUtility();

		//[READING] the [COMMONDATA] from properties file
		String Browser = fileUtility.getDataFromPropertiesFile("browser");
		String url = fileUtility.getDataFromPropertiesFile("url");
		String username = fileUtility.getDataFromPropertiesFile("username");
		String password = fileUtility.getDataFromPropertiesFile("password");

		System.out.println(Browser+" "+url+" "+username+" "+password);


		//[READING] the [TEST SCRIPT DATA] from xlss file
		String orgName = excelUtility.getDataFromExcel("Sheet1", 4, 2)+javaUtility.getRandonNumber();
		String phoneNo = excelUtility.getDataFromExcel("Sheet1", 4, 5);
	


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
		chromeDriver.findElement(By.linkText("Organizations")).click();

		//clicking on organization button
		chromeDriver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		//Filling the details
		chromeDriver.findElement(By.name("accountname")).sendKeys(orgName);

		//Using Phone no
		chromeDriver.findElement(By.id("phone")).sendKeys(phoneNo);

		//Saving the details
		chromeDriver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		//Validation for PhoneNO
		String Phone= chromeDriver.findElement(By.id("dtlview_Phone")).getText();
		if(Phone.equals(phoneNo))
		{
			System.out.println("PhoneNo is verifed");
		}

	}

}
