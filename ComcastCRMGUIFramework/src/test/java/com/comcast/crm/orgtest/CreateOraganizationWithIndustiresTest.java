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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;

public class CreateOraganizationWithIndustiresTest
{

	public static void main(String[] args) throws Throwable 
	{
		//[READING] the [COMMONDATA] from properties file
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


		//[READING] the [TEST SCRIPT DATA] from xless file
		String orgName = excelUtility.getDataFromExcel("Sheet1", 4, 2)+javaUtility.getRandonNumber();
		String industry = excelUtility.getDataFromExcel("Sheet1", 4, 3);
		String type = excelUtility.getDataFromExcel("Sheet1", 4, 4);
		


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

		System.out.println(1);
		//Reading Industry drop-down
		WebElement industry_drop_down =chromeDriver.findElement(By.name("industry"));
		Select select = new Select(industry_drop_down);
		select.selectByVisibleText(industry);
		System.out.println(2);

		//Reading Type drop-down
		WebElement type_drop_down =chromeDriver.findElement(By.name("accounttype"));
		Select select1 = new Select(type_drop_down);
		select1.selectByVisibleText(type);
		System.out.println(3);

		//Saving the details
		chromeDriver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		//After clicking on save button Verify Industries and type 
		String actual_indus = chromeDriver.findElement(By.id("dtlview_Industry")).getText();
		String actual_type = chromeDriver.findElement(By.id("dtlview_Type")).getText();
		if(actual_indus.equals(industry))
		{
			System.out.println(industry+" is verified ====[PASS]=====");
		}
		else
		{
			System.out.println(industry+" is not verified ====[FAIL]=====");
		}
		if(actual_type.equals(type))
		{
			System.out.println(type+" is verified ====[PASS]=====");
		}
		else
		{
			System.out.println(type+" is not verified ====[FAIL]=====");
		}






		// have to logout ?

		chromeDriver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]")).click();
		Actions actions = new Actions(chromeDriver);
		actions.moveToElement(chromeDriver.findElement(By.cssSelector("img[src=\"themes/softed/images/user.PNG\"]"))).perform();
		actions.click(chromeDriver.findElement(By.xpath("//a[.='Sign Out']"))).perform();

		chromeDriver.quit();
	}
}
