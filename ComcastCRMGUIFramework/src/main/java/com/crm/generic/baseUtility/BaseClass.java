package com.crm.generic.baseUtility;

import java.sql.SQLException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseUtility.DatabaseUtility;
import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;
import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass 
{
	public DatabaseUtility dbLib = new DatabaseUtility();
	public ExcelUtility excelUtility = new ExcelUtility();
	public FileUtility fLib = new FileUtility();
	public JavaUtility javaUtility = new JavaUtility();
	public WebDriverUtility wbLib = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sDriver = null;
	public static ExtentReports report ;

	@BeforeSuite(groups = {"smokeTest","regressionTest"})
	public void configBS() throws Exception
	{
		System.out.println("====connect to DB ==== and generate the report =====");
		dbLib.getConnection();

		// Report
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		report = new ExtentReports();
		report.attachReporter(spark);

		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "CHROME-10");
	}

	//@Parameters("browser")
	@BeforeClass(groups = {"smokeTest","regressionTest"})
	public void configBC() throws Throwable
	{
		System.out.println("====Launch the Browser=====");

		String Browser = fLib.getDataFromPropertiesFile("browser");

		//fLib.getDataFromPropertiesFile("browser");

		if(Browser.equals("Chrome"))
		{
			driver = new ChromeDriver();
		}
		else if( Browser.equals("Firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if( Browser.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		sDriver=driver ;
	}

	@BeforeMethod(groups = {"smokeTest","regressionTest"})
	public void configBM() throws Throwable
	{
		System.out.println("=====Login======");
		String url=fLib.getDataFromPropertiesFile("url");
		String user=fLib.getDataFromPropertiesFile("username");
		String pwd=fLib.getDataFromPropertiesFile("password");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApp(url, user, pwd);
	}

	@AfterMethod(groups = {"smokeTest","regressionTest"})
	public void configAM()
	{
		System.out.println("=====Logout======");
		
		ChromeOptions ch  =new ChromeOptions();
		ch.addArguments("--disable notification");
		HomePage hm = new HomePage(driver);
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))));
		hm.logOut();
	}
	@AfterClass(groups = {"smokeTest","regressionTest"})
	public void configAC()
	{
		System.out.println("====Close the Browser=====");
		driver.quit();
	}

	@AfterSuite(groups = {"smokeTest","regressionTest"})
	public void configAS() throws SQLException
	{
		System.out.println("====close DB ==== and generate the report =====");
		dbLib.closeDbConnection();

		// Report Backup
		report.flush();
	}
}
