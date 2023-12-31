package utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileReader {
private Properties properties;
	
	//Class Constructor open read the data from the input stream of the properties file
	public PropertiesFileReader(InputStream fis) throws Exception
	{
		properties = new Properties();
		properties.load(fis);
		fis.close();
	}
	
	//Method to get value of the given key from the property file
	public String getValue(String key)
	{
		return properties.getProperty(key);
	}
}
