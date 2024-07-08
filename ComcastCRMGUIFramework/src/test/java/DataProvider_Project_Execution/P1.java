package DataProvider_Project_Execution;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileUtility.ExcelUtility;

public class P1
{

	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName, String productName)
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.Amazon.in");
		
		//Search Product
		driver.findElement(By.name("field-keywords")).sendKeys(brandName,Keys.ENTER);
		
		//Capture product info
	//	String x="//span[text()='"+productName+"']/../../../../div[4]/div[1]/div[1]/a/span/span[2]/span[2]";
		String x="//span[text()='"+productName+"']/../../../..//span[@class='a-price-whole']";
		
		String price = driver.findElement(By.xpath(x)).getText();
		System.out.println(productName+" price of the product is:-> "+price);
		driver.quit();
	}
	
	@DataProvider
	public Object[][] getData() throws Throwable
	{
		ExcelUtility excelUtility = new ExcelUtility();
		int rowcount = excelUtility.getRowCount("product");
		
		Object obj[][]= new Object[rowcount][2];
		
		for( int i = 0 ; i < rowcount ; i++)
		{
		  obj[i][0]=excelUtility.getDataFromExcel("product", i+1 , 0);	
		  obj[i][1]=excelUtility.getDataFromExcel("product", i+1 , 1);	
		}
		
		return obj;
		
	}
	
	
}
