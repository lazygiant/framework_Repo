package com.comcast.crm.generic.fileUtility;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtility 
{

	public String getDataFromPropertiesFile(String Key) throws Throwable
	{
		FileInputStream fileInputStream = new FileInputStream("./configAppData/commondata.properties");
		Properties properties = new Properties();
		properties.load(fileInputStream);
		
		String data = properties.getProperty(Key);
		return data;
	}
}
