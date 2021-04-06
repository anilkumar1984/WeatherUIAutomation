package generics;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import managers.BrowserManager;
import utility.Utility;

public class BaseTestForUI extends BaseTest {
	BrowserManager browserManager;
	public static WebDriver driver;
		
	@Parameters("browser")
	@BeforeMethod(alwaysRun=true)
	public void setUp(String browser) {
		
		extent.setSystemInfo("Browser", browser);
		browserManager = new BrowserManager(browser);
		driver = browserManager.getDriver();
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown(ITestResult result) {
		
		String screenShotPath = Utility.captureScreenShot(driver, result.getName());
		if (result.getStatus() == ITestResult.FAILURE) {
			
			try {
				
				logger.fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//logger.fail(e.getMessage());
			}
		}

		else if (result.getStatus() == ITestResult.SUCCESS) {

			try {
				logger.pass(result.getName() + "-Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (result.getStatus() == ITestResult.SKIP) {

			logger.info(result.getName() + "-Test Skipped");
		}

		
		driver.quit();
	}
}
