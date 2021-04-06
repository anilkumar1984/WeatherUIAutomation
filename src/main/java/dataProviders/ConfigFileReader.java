package dataProviders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import enums.DriverType;
import enums.EnvironmentType;

public class ConfigFileReader {

	private Properties properties;;
	private final String propertyFilePath = "configs//config.properties";
	FileInputStream fis;

	public ConfigFileReader() {

		File f = new File(propertyFilePath);
		try {
			fis = new FileInputStream(f.getAbsolutePath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		properties = new Properties();
		try {
			properties.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getApplicationURL() {
		String url = properties.getProperty("url");
		if (url != null) {
			return url;
		} else {
			throw new RuntimeException("URL not specified in the Configuration.properties file");
		}

	}

	public int getImpicitlyWait() {
		String implicitwait = properties.getProperty("implicitwait");
		if (implicitwait != null) {
			return Integer.parseInt(implicitwait);

		} else {
			throw new RuntimeException("implicitwait not specified in the Configuration.properties file");
		}

	}

	public int getPageLoadTimeOut() {
		String pageloadtimeout = properties.getProperty("pageloadtimeout");
		if (pageloadtimeout != null) {
			return Integer.parseInt(pageloadtimeout);

		} else {
			throw new RuntimeException("pageloadtimeout not specified in the Configuration.properties file");
		}

	}
	
	public String getAPIEndPoint() {
		String apiendpoint = properties.getProperty("apiendpoint");
		if (apiendpoint != null) {
			return apiendpoint;

		} else {
			throw new RuntimeException("apiendpoint not specified in the Configuration.properties file");
		}

	}
	
	public String getAPIResource() {
		String apiresource = properties.getProperty("apiresource");
		if (apiresource != null) {
			return apiresource;

		} else {
			throw new RuntimeException("apiresource not specified in the Configuration.properties file");
		}
	}

	public int getWebdriverWaitTime() {
		String webdriverwaittime = properties.getProperty("webdriverwaittime");
		if (webdriverwaittime != null) {
			return Integer.parseInt(webdriverwaittime);

		} else {
			throw new RuntimeException("webdriverwaittime not specified in the Configuration.properties file");
		}

	}

	public DriverType getBrowser(String browserName) {
		//String browserName = properties.getProperty("browser");
		if (browserName == null || browserName.equalsIgnoreCase("chrome"))
			return DriverType.CHROME;
		else if (browserName.equalsIgnoreCase("firefox"))
			return DriverType.FIREFOX;
		else if (browserName.equalsIgnoreCase("edge"))
			return DriverType.EDGE;
		else {
			throw new RuntimeException("Browser key name not specified in the Configuration.properties file");
		}

	}
	
	public EnvironmentType getEnvironment() {
		String environmentName = properties.getProperty("environment");
		if (environmentName == null || environmentName.equalsIgnoreCase("local"))
			return EnvironmentType.LOCAL;
		else if (environmentName.equalsIgnoreCase("remote"))
			return EnvironmentType.REMOTE;
		
		else {
			throw new RuntimeException("environment key name not specified in the Configuration.properties file");
		}

	}
}
