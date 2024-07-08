package DataProvider_Project_Execution;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileUtility.ExcelUtility;

public class GetProductInfoTest {

	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName, String productName)
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.Amazon.in");
		
		//Search Product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
		
		//Capture product info
	//	String x="//span[text()='"+productName+"']/../../../../div[4]/div[1]/div[1]/a/span/span[2]/span[2]";
		String x="//span[text()='"+productName+"']/../../../..//span[@class='a-price-whole']";
		String price = driver.findElement(By.xpath(x)).getText();
		System.out.println("price of the product is:-> "+price);
		driver.quit();
	}
	
	@DataProvider
	public Object[][] getData() throws Throwable
	{
		ExcelUtility excelUtility = new ExcelUtility();
		int rowCount = excelUtility.getRowCount("product");
		
		Object[][] obj = new Object[rowCount][2];
		
		
		for(int i = 0 ; i<rowCount ; i++)
		{
			obj[0][0]=excelUtility.getDataFromExcel("product", i+1, 0);
			obj[0][1]=excelUtility.getDataFromExcel("product", i+1, 1);
		}
		
//		obj[0][0]="iphone";
//		obj[0][1]="Apple iPhone 15 (128 GB) - Black";
//		
//		obj[1][0]="iphone";
//		obj[1][1]="Apple iPhone 15 (128 GB) - Red";
//		
		
		return obj;
	}
}
