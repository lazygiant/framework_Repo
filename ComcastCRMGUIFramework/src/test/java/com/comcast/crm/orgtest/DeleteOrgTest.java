package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;
import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrgPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DeleteOrgTest
{
	public static void main(String[] args) throws Throwable
	{
		//[Utility] --> [OBJECT]
		FileUtility fileUtility = new FileUtility();
		ExcelUtility excelUtility = new ExcelUtility();
		JavaUtility javaUtility = new JavaUtility();
		WebDriverUtility webUtility = new WebDriverUtility();
		
		//[READING] the [COMMONDATA] from properties file
		String Browser = fileUtility.getDataFromPropertiesFile("browser");
		String url = fileUtility.getDataFromPropertiesFile("url");
		String username = fileUtility.getDataFromPropertiesFile("username");
		String password = fileUtility.getDataFromPropertiesFile("password");

		System.out.println(Browser+" "+url+" "+username+" "+password);


		//[READING] the [TEST SCRIPT DATA] from xlss file
		String orgName = excelUtility.getDataFromExcel("Sheet1", 10, 2)+javaUtility.getRandonNumber();



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
		//	chromeDriver.manage().window().maximize();
		chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		chromeDriver.get(url);

		LoginPage loginPage = new LoginPage(chromeDriver);
		loginPage.loginToApp("admin", "root");

		System.out.println("loginPage Executed...");


		//			loginPage.getEle1().sendKeys("admin");
		//			loginPage.getEle2().sendKeys("root");
		//			loginPage.getEle3().click();

		//			chromeDriver.findElement(By.name("user_name")).sendKeys(username);
		//			chromeDriver.findElement(By.name("user_password")).sendKeys(password);
		//			chromeDriver.findElement(By.id("submitButton")).click();

		//navigated to Oraganization done
		//chromeDriver.findElement(By.linkText("Organizations")).click();

		HomePage homePage = new HomePage(chromeDriver);
		homePage.getOrganization().click();

		System.out.println("Homepage Executed...");

		Thread.sleep(3000);
		//clicking on organization button
		//chromeDriver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		OrganizationsPage organizationsPage = new OrganizationsPage(chromeDriver);
		organizationsPage.getCreateNewOrg().click();

		System.out.println("Creating Organization Executed...");


		//Filling the details
		//chromeDriver.findElement(By.name("accountname")).sendKeys(orgName);
		//Saving the details
		//chromeDriver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		CreatingNewOrgPage creatingNewOrgPage = new CreatingNewOrgPage(chromeDriver);
		creatingNewOrgPage.createOrg(orgName);

		System.out.println("Saved the created Organization ...");

		//Verify Header Msg Expected Result
		//			String headerInfo = chromeDriver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		//
		//			if(headerInfo.contains("orgName"))
		//			{
		//				System.out.println(orgName+" is created===[PASS]");
		//			}
		//			else
		//			{
		//				System.out.println(orgName+" is not created===[FAIL]");
		//			}

		//Verify Header orgName Expected Result
		//String actualOrgName=chromeDriver.findElement(By.id("dtlview_Organization Name")).getText();

		OrganizationInfoPage actualOrgName = new OrganizationInfoPage(chromeDriver);
		String headerText = actualOrgName.getHeaderMsg().getText();

		if(headerText.contains(orgName))
		{
			System.out.println(orgName+" is verified BE HAPPY==[PASS]");
		}
		else
		{
			System.out.println(orgName+" is not verified AS ALWAYS [HEHEHE]===[FAIL]");
		}


		// have to logout ?

		//			chromeDriver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]")).click();
		//			Actions actions = new Actions(chromeDriver);
		//			actions.moveToElement(chromeDriver.findElement(By.cssSelector("img[src=\"themes/softed/images/user.PNG\"]"))).perform();
		//			actions.click(chromeDriver.findElement(By.xpath("//a[.='Sign Out']"))).perform();

		// -> go back to Organization Page
		homePage.getOrganization().click();
		
		// -> search for Organization
		organizationsPage.getSearchEdt().sendKeys(orgName);
		webUtility.select(organizationsPage.getSearchDD(), "Organization Name");
		organizationsPage.getSearchButton().click();
		
		// -> In dynamic webtable select and delete org
		// for dynamic data we can't use pom concept
		chromeDriver.findElement(By.xpath("//a[text()='"+orgName+"']/../..//td[8]/a[text()='del']")).click();
		
		chromeDriver.switchTo().alert().accept();
		
		homePage.logOut();

		chromeDriver.quit();
	}

}


