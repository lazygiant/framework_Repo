package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrgPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.crm.generic.baseUtility.BaseClass;

public class CreateContactTest extends BaseClass 
{

	@Test(groups = "smokeTest")
	public void createContactTest() throws Throwable
	{

		// [READING] the [TEST SCRIPT DATA] from xlss file
		String lastName = excelUtility.getDataFromExcel("Sheet1", 4, 2) + javaUtility.getRandonNumber();

		// Step 2: navigate to Contact Module	
		HomePage hp = new HomePage(driver);
		hp.getContacts().click();

		// Step 3: Click on " create Contact " Button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContact().click();

		//Pass the details in Contact and clicking on save
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.createContact(lastName);

		//dtlview_Last Name -> we should add in repository
		//Verification of Header
		//getHeaderMessage() is present in ContactPage
		
		String actHeader = cp.getHeaderMessage().getText();
		//Hence above statement is mandatory will use HardAssert
		//actHeader if it contains lastName
		boolean status = actHeader.contains(lastName);
		Assert.assertEquals(status, true);
		
		/*
		 * if(actHeader.equals(lastName)) { System.out.println(actHeader +
		 * " Header is verifed"); }else { System.out.println(actHeader +
		 * " Header is not verifed"); }
		 */
		//According to automation data should not be hard-coded
		
		//Verification 
		String actualLastName = cncp.getLastEdit().getText();
		//Hence above statement is not mandatory will use SoftAssert
		SoftAssert assert1 = new SoftAssert();
		assert1.assertEquals(actualLastName, lastName);
		assert1.assertAll();
		
		/*
		 * if(actualLastName.equals(lastName)) { System.out.println(lastName +
		 * " lastname is verifed"); }else { System.out.println(lastName +
		 * " lastname is not verifed"); }
		 */
	}

	// 
	@Test(groups = "regressionTest")
	public void createContactWithSupportDateTest() throws Throwable 
	{
		String lastName = excelUtility.getDataFromExcel("Sheet1",4,2)+javaUtility.getRandonNumber();

		// Step 2: navigate to Contact Module HomePage hp = new HomePage(driver);
		HomePage hp = new HomePage(driver);
		hp.getContacts().click();

		// Step 3: Click on " create Contact " Button ContactPage cp = new
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContact().click();

		// Step 4: Enter all the details String startDate =
		String startDate= javaUtility.getSystemDataYYYYMMDD();
		String endDate =javaUtility.getRequiredDataYYYYMMDD(30);

		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.createContactWithSupportDate(lastName, startDate, endDate);

		//Validation for Contact, StartDate and EndDate String actualLastName=
		wbLib.waitForPageToLoad(driver);
		
		//According to automation data should not be hard-coded
		//dtlview_Last Name -> we should add in repository
		
		String actualLastName=driver.findElement(By.id("dtlview_Last Name")).getText();

		if(actualLastName.equals(lastName)) { System.out.println(lastName+
				" lastname is verifed"); } else { System.out.println(lastName+
						" lastname is not verifed"); }

		String startDate_ =
				driver.findElement(By.id("dtlview_Support Start Date")).getText();

		if(startDate.equals(startDate_)) { System.out.println(startDate_+
				" is verifed"); } else { System.out.println(startDate_+ " is not verifed"); }

		String endDate_ =
				driver.findElement(By.id("dtlview_Support End Date")).getText();

		if(endDate.equals(endDate_)) { System.out.println(endDate_+ " is verifed"); }
		else { System.out.println(endDate_+ " is not verifed"); }
	}

	@Test(groups = "regressionTest")
	public void createContactWithOrgTest() throws Throwable
	{
		//[READING] the [TEST SCRIPT DATA] from xless file
		//String lastName = excelUtility.getDataFromExcel("Sheet1", 7, 2);
		String orgName = excelUtility.getDataFromExcel("Sheet1", 7, 3)+javaUtility.getRandonNumber();

		// Step 2: navigate to Organization Module	
		HomePage hp = new HomePage(driver);
		hp.getOrganization().click();

		// Step 3 : Click on Organization + Button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrg().click();

		// Step 4 : Fill all the details
		CreatingNewOrgPage cnop = new CreatingNewOrgPage(driver);
		cnop.createOrg(orgName);
		wbLib.waitForPageToLoad(driver);

		// Step 5 : Navigate to Contact


		hp.getContacts().click();

		String lastName = excelUtility.getDataFromExcel("Sheet1", 4, 2) + javaUtility.getRandonNumber();

		// Step 3: Click on " create Contact " Button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContact().click();

		//Pass the details in Contact and clicking on save
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.createContact(lastName);

	}
}
