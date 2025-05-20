package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	public static Properties properties;
	public static final String propertyFilePath = "./src/test/resources/config.properties";
	private static ThreadLocal<String> browserType = new ThreadLocal<String>();
	
	public static void loadConfig() throws Throwable {
		FileInputStream fis = new FileInputStream(propertyFilePath);
		properties = new Properties();
		properties.load(fis);
		fis.close();
	}
	
	public static String getPropertyValue(String key) {
		String value = properties.getProperty(key);
		if(value!=null)
			return value;
		else
			throw new RuntimeException("Key "+key+" not specified in config.properties file");
	}
	
	public static void setBrowserType(String browser) {
		browserType.set(browser);
	}

	public static String getBrowserType() {
			return browserType.get();
	}

	

}
