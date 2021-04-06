package managers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import enums.DriverType;
import enums.EnvironmentType;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserManager {
	private WebDriver driver;
	private DriverType driverType;
	private EnvironmentType environmentType;
	
	public BrowserManager(String browser) {
		 driverType=FileReaderManager.getInstance().getConfigReader().getBrowser(browser);
		 environmentType=FileReaderManager.getInstance().getConfigReader().getEnvironment();
	}
	
	public WebDriver getDriver() {
		if(driver==null) {
			createDriver();
			
		}
		return driver;
	}
	
	private  WebDriver createDriver() {
		switch(environmentType) {
		case LOCAL:createLocalDriver();
			break;
		case REMOTE:createRemoteDriver();
			break;
		}
		return driver;
	}
	
	public void createRemoteDriver() {
		
	}
	
	
	public WebDriver createLocalDriver() {
		switch (driverType) {
		case CHROME: {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		}
		case FIREFOX: {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		}

		case EDGE: {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		}
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImpicitlyWait(), TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(FileReaderManager.getInstance().getConfigReader().getPageLoadTimeOut(), TimeUnit.SECONDS);
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationURL());
		return driver;
		
	}

}
