package practice.Listener_1;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.generic.baseUtility.BaseClass;

//@Listeners(com.comcast.crm.listenerutility.ListImpClass.class)
public class InvoiceTest extends BaseClass
{

	@Test(retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImp.class)
	public void activateSim()
	{
		System.out.println("execute activateSim");
		//String actTitle = driver.getTitle();
		//Assert.assertEquals(actTitle, "Login");
		System.out.println(1);
		System.out.println(2);
		System.out.println(3);

	}

	@Test
	public void createInvoiceTest()
	{
		System.out.println("execute createInvoiceTest");
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		System.out.println(1);
		System.out.println(2);
		System.out.println(3);

	}
	@Test
	public void createInvoiceWithContactTest()
	{
		System.out.println("execute createInvoiceWithContactTest");
		System.out.println(1);
		System.out.println(2);
		System.out.println(3);
	}
}
