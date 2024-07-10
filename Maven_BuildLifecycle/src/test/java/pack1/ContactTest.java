package pack1;

import org.testng.annotations.Test;

public class ContactTest 
{

	@Test(groups = "ST")
	public void createContactTest()
	{
		String url = System.getProperty("url");
		String browser = System.getProperty("browser","chrome");
		String username = System.getProperty("username");
		String password = System.getProperty("pwd");
		System.out.println("execute createContactTest");
		System.out.println(url);
		System.out.println(browser);
		System.out.println(username);
		System.out.println(password);
	}
	
	@Test(groups = "RT")
	public void modifyContactTest()
	{
		System.out.println("execute modifyContactTest");
	}
	
}
