package generics;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.MediaEntityBuilder;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import managers.APIManager;
import utility.Utility;

//import io.restassured.RestAssured;

public class BaseTestForAPI extends BaseTest {
	
	@BeforeMethod
	public void init() {
		//System.out.println(APIManager.getAPIEndPoint());
		RestAssured.baseURI=APIManager.getAPIEndPoint();		
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown(ITestResult result) throws IOException {
		
		if (result.getStatus() == ITestResult.FAILURE) {
			
			logger.fail(result.getThrowable());
		}

		else if (result.getStatus() == ITestResult.SUCCESS) {

			logger.pass(result.getName() + "-Test Passed");
		}

		if (result.getStatus() == ITestResult.SKIP) {

			logger.info(result.getName() + "-Test Skipped");
		}
	
		
	}
}
