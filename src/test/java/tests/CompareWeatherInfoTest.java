package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import generics.BaseTest;

public class CompareWeatherInfoTest extends BaseTest {
	
	@Parameters("city")
	@Test
	public void compareWeatherInfoBetweeenAPIAndUI(String city) {
		SoftAssert softAssert = new SoftAssert();
		logger = extent.createTest("compareWeatherInfoBetweeenAPIAndUI",
				"Compare the  Weather Information for a city between the UI and API");
		logger.info("Compare the Weather Temerature details from UI and API for the below City: "+city);
		int rowCount = excelFileReader.getRowCount();
		String cityUI = excelFileReader.getCellData("CityUI", rowCount - 1);
		String cityAPI = excelFileReader.getCellData("CityAPI", rowCount - 1);

		softAssert.assertEquals(cityUI, cityAPI,
				"Actual City from UI: " + cityUI + " should match Expected City API: " + cityAPI);

		

		String strTemparature_UI = excelFileReader.getCellData("Temparature_UI", rowCount - 1);
		String strTemparature_API = excelFileReader.getCellData("Temparature_API", rowCount - 1);
		double temparature_UI = Double.parseDouble(strTemparature_UI);
		double temparature_API = Double.parseDouble(strTemparature_API);
		double afterAdditionTemparature_API = temparature_API + 2;
		double afterSubtractionTemparature_API = temparature_API - 2;

		boolean found = false;

		if (temparature_UI <= afterAdditionTemparature_API) {
			found = true;
		} else if (temparature_UI <= afterSubtractionTemparature_API) {
			found = true;		
		}

		if (found) {

			softAssert.assertTrue(found, "Actual Temparature from UI: " + temparature_UI
					+ " should match Expected Temperature from API: " + temparature_API);
			logger.pass("Actual Temparature from UI: " + temparature_UI
					+ " celcius should match Expected Temperature from API: "+ temparature_API+" celcius with a variation of '+ or -2' degree celcius " );
		} else {
			logger.fail("Actual Temparature from UI for City: "+city+" :is " + temparature_UI+" celcius"
					+ " and is not matching with the Expected temparature from  API: "+ temparature_API+ " celcius  with a variation of '+ or -2' degree celcius " );
			softAssert.fail();
		}

		softAssert.assertAll();

	}

	@AfterMethod(alwaysRun = true)
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
