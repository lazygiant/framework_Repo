package com.comcast.crm.contacttest;

import com.crm.generic.baseUtility.BaseClass;

public class CreateContactTestWithFull_CODE {

	public static void main(String[] args) {
		/*
		 * // [READING] the [DATA] from ====[PROPERTIES FILE]====
		 * 
		 * [INHERITING FROM BASE CLASS]
		 * 
		 * FileUtility fileUtility = new FileUtility(); ExcelUtility excelUtility = new
		 * ExcelUtility(); JavaUtility javaUtility = new JavaUtility();
		 * 
		 * String Browser = fileUtility.getDataFromPropertiesFile("browser"); String url
		 * = fileUtility.getDataFromPropertiesFile("url"); String username =
		 * fileUtility.getDataFromPropertiesFile("username"); String password =
		 * fileUtility.getDataFromPropertiesFile("password");
		 * 
		 * System.out.println(Browser + " " + url + " " + username + " " + password);
		 * 
		 * 
		 * // [READING] the [TEST SCRIPT DATA] from xlss file String lastName =
		 * excelUtility.getDataFromExcel("Sheet1", 4, 2) +
		 * javaUtility.getRandonNumber(); String phoneNo =
		 * excelUtility.getDataFromExcel("Sheet1", 4, 5);
		 * 
		 * 
		 * // [SELENIUM] Program [EXECUTION]
		 * 
		 * ChromeOptions option = new ChromeOptions();
		 * option.addArguments("--disable-notifications"); WebDriver chromeDriver =
		 * null;
		 * 
		 * if (Browser.equals("Chrome")) { chromeDriver = new ChromeDriver(); } else if
		 * (Browser.equals("Firefox")) { chromeDriver = new FirefoxDriver(); } else if
		 * (Browser.equals("edge")) { chromeDriver = new EdgeDriver(); }
		 * 
		 * chromeDriver.manage().window().maximize();
		 * chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 * chromeDriver.get(url);
		 * chromeDriver.findElement(By.name("user_name")).sendKeys(username);
		 * chromeDriver.findElement(By.name("user_password")).sendKeys(password);
		 * chromeDriver.findElement(By.id("submitButton")).click();
		 * 
		 * 
		 * HomePage homePage = new HomePage(driver); homePage.ge
		 * 
		 * LoginPage lp = new LoginPage(driver); lp.
		 * 
		 * 
		 * 
		 * // ONE MORE TIME LEARNING
		 * 
		 * 
		 * // navigated to Oraganization done
		 * chromeDriver.findElement(By.linkText("Contacts")).click();
		 * 
		 * // clicking on organization button
		 * chromeDriver.findElement(By.xpath("//img[@title='Create Contact...']")).click
		 * ();
		 * 
		 * // Filling the details
		 * chromeDriver.findElement(By.name("lastname")).sendKeys(lastName);
		 * 
		 * // Using Phone no chromeDriver.findElement(By.id("phone")).sendKeys(phoneNo);
		 * 
		 * // Saving the details
		 * chromeDriver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		 * 
		 * // ====[VALIDATION]==== for lastname after clicking on [SAVE]
		 * 
		 * String actualLastName =
		 * chromeDriver.findElement(By.id("dtlview_Last Name")).getText();
		 * 
		 * if(actualLastName.equals(lastName)) { System.out.println(lastName +
		 * " lastname is verifed"); }else { System.out.println(lastName +
		 * " lastname is not verifed"); }
		 */

	}

}
