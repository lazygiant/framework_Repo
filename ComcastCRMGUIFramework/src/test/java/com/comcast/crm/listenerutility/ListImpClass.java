package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.generic.baseUtility.BaseClass;
import java.util.*;
public class ListImpClass implements ITestListener, ISuiteListener
{
	ExtentReports report;
	public static ExtentTest test;
	
	//onStart() is exactly similar to onSuite().
	//onStart() will execute before onSuite().
	//onStart() is coming from ISuiteListener.
	@Override
	public void onStart(ISuite suite)
	{
		System.out.println("Report configuration");
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		// Title of the report
		spark.config().setDocumentTitle("CRM Test Suite Results");
		// Name of the report
		spark.config().setReportName("CRM Report");
		// Theme of the report
		spark.config().setTheme(Theme.DARK);

		// Step 2 : add Env Information and create test //
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "CHROME-10");
	}

	@Override
	public void onFinish(ISuite suite)
	{
		System.out.println("Report backup");
		
		// to save the report will use 
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) 
	{
		System.out.println("====>> "+result.getMethod().getMethodName()+" ====START====>");
		test=report.createTest(result.getMethod().getMethodName());
		
		String testName = result.getMethod().getMethodName();
		test.log(Status.INFO, testName+" ====> [STARTED] <====");
	}

	@Override
	public void onTestSuccess(ITestResult result)
	{
		System.out.println("====>> "+result.getMethod().getMethodName()+" ====END====>");
		test.log(Status.PASS, result.getMethod().getMethodName()+"==[ SUCCESS ] ==" );
	}

	@Override
	public void onTestFailure(ITestResult result)
	{
		// will write screenshot program on this failure method
		String testName = result.getMethod().getMethodName();
		TakesScreenshot edDriver =(TakesScreenshot) BaseClass.sDriver;
		String filePAth = edDriver.getScreenshotAs(OutputType.BASE64);
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		
		test.addScreenCaptureFromBase64String(filePAth, testName+"_"+time);
		test.log(Status.FAIL,result.getMethod().getMethodName()+"==[ FAILED ] ==" );

	}
	
	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}

}
