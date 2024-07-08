package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.*;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;
import com.comcast.crm.generic.webdriverUtility.UtilityClassObject;
import com.comcast.crm.listenerutility.ListImpClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrgPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.crm.generic.baseUtility.BaseClass;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

// this is help us to run the class directly, dont need testNg.xml file to run the class

@Listeners(com.comcast.crm.listenerutility.ListImpClass.class)
public class CreateOraganizationTest extends BaseClass
{
	@Test(groups = "smokeTest")
	public void createOrgTest() throws Throwable
	{
		UtilityClassObject.getTest().log(Status.INFO, "read data from Excel");
		
		// Reading OrgName from Excel file
		String orgName = excelUtility.getDataFromExcel("Sheet1", 4, 2)+javaUtility.getRandonNumber();

		// Navigating to Organization Page 
		ListImpClass.test.log(Status.INFO, "navigate to Org Page");
		HomePage hm = new HomePage(driver);
		hm.getOrganization().click();

		// Clicking on Organization button
		ListImpClass.test.log(Status.INFO, "navigate to create Org Page");
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrg().click();

		// Pass the details of Organization and clicking on SAVE
		ListImpClass.test.log(Status.INFO, "create a new org");
		CreatingNewOrgPage cnop = new CreatingNewOrgPage(driver);
		cnop.createOrg(orgName);

		// Verifying the Header Msg
		OrganizationInfoPage infoPage = new OrganizationInfoPage(driver);
		String header=infoPage.getHeaderMsg().getText();

		Assert.assertEquals(true, header.contains(orgName));

	}


	@Test//(groups = "regressionTest") 
	public void createOrgWithIndustryTest() throws Throwable
	{ 
		String orgName =excelUtility.getDataFromExcel("Sheet1", 4, 2)+javaUtility.getRandonNumber();
		String industry  = excelUtility.getDataFromExcel("Sheet1", 4, 3); 
		//	Navigating to Organization Page 
		HomePage hm = new HomePage(driver);
		hm.getOrganization().click();

		// Clicking on Organization button 
		OrganizationsPage op = new OrganizationsPage(driver); op.getCreateNewOrg().click();

		// Pass the details of Organization along with Industry 
		CreatingNewOrgPage cnop = new CreatingNewOrgPage(driver);
		cnop.createOrg(orgName, industry);

		//Verifying is pending.... for OrganizatioName and IndustryName 
	}

	@Test(groups = "regressionTest")
	public void createOrgTestWithPhoneNo() throws Throwable
	{
		// Reading OrgName from Excel file
		String orgName = excelUtility.getDataFromExcel("Sheet1", 4, 2)+javaUtility.getRandonNumber();
		String phoneNo = excelUtility.getDataFromExcel("Sheet1", 4, 5);


		// Navigating to Organization Page 
		HomePage hm = new HomePage(driver);
		hm.getOrganization().click();

		// Clicking on Organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrg().click();

		// Pass the details of Organization along with Industry
		CreatingNewOrgPage cnop = new CreatingNewOrgPage(driver);
		cnop.createOrgWithPhoneNo(orgName, phoneNo);

		//hm.logOut();

		//Verification is pending....  for OrganizatioName and PhoneNo 

	}
}


