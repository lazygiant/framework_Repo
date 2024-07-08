
package practice.homeTest.Assert_Implementation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

public class SampleTestForScreenShot {

	@Test
	public void flipkartTest(Method mtd) throws IOException 
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.flipkart.com");
		
		//Createing an object to EventFiring Webdriver
		EventFiringWebDriver edDriver = new EventFiringWebDriver(driver);
		File srcFile = edDriver.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(srcFile, new File("./screenshot/"+mtd.getName()+".png"));
	}
	
}
