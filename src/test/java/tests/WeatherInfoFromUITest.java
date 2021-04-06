package tests;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dataProviders.ExcelFileReader;
import generics.BaseTestForUI;
import managers.PageObjectManager;
import pageObjects.HomePage;
import pageObjects.WeatherPage;
import utility.Utility;

public class WeatherInfoFromUITest extends BaseTestForUI {

	private PageObjectManager pageObjectManager;
	private HomePage homePage;
	private WeatherPage weatherPage;
	private static String expectedCity;
	private static ArrayList<String> listTemperatureDetailsOfTheCityFromUI;
	
	@Parameters("city")
	@Test
	public void getWeatherInfoFromUI_TC(String city) {
		
		expectedCity=city;
		logger = extent.createTest("getWeatherInfoFromUI_TC", "Get the Weather Information for a city from the UI");
		pageObjectManager = new PageObjectManager(driver);
		homePage = pageObjectManager.getHomePage();
		if (homePage.verifyBreakingNewsAlertPopUp()) {
			homePage.clickOnNoThanksAlertPopUp();
		}

		Assert.assertTrue(homePage.verifyHomePageIsDisplayed());

		homePage.clickOnSubMenu();
		weatherPage = homePage.clickOnWeatherLink();
		Assert.assertTrue(weatherPage.verifyWeatherPageIsDisplayed());
		weatherPage.searchForCity(expectedCity);
		Assert.assertTrue(weatherPage.verifyActualCityDisplayed("id",expectedCity),"Expected City: "+expectedCity+" is not present in the Drop down.Please search with a Valid City");
		weatherPage.clickCheckboxOfSearchedCity();
		Assert.assertTrue(weatherPage.clickOnTheCitySelectedInsideTheMap(expectedCity),"Expected City: "+expectedCity+" not found in the Map");
		Assert.assertTrue(weatherPage.verifyCityDetailsPopUpIsDisplayed(),"Expected City: "+expectedCity+" Pop Up should be dispalyed");
		listTemperatureDetailsOfTheCityFromUI=weatherPage.getTemparatureDetails();
		logger.info("Below is the Temparature Details for City from UI: "+listTemperatureDetailsOfTheCityFromUI.get(0));
		logger.info(listTemperatureDetailsOfTheCityFromUI.get(1));
		logger.info(listTemperatureDetailsOfTheCityFromUI.get(2));
		logger.info(listTemperatureDetailsOfTheCityFromUI.get(3));
		logger.info(listTemperatureDetailsOfTheCityFromUI.get(4));
		logger.info(listTemperatureDetailsOfTheCityFromUI.get(5));
			
		String windDetailsFromUI=listTemperatureDetailsOfTheCityFromUI.get(2).split(":")[1];
		windDetailsFromUI=windDetailsFromUI.trim();
		
		String temperatureDetailsFromUI=listTemperatureDetailsOfTheCityFromUI.get(4).split(":")[1];
		temperatureDetailsFromUI=temperatureDetailsFromUI.trim();
			
		excelFileReader.enterCellData(0, Utility.getCurrentDateAndTime());
		excelFileReader.enterCellData(1, expectedCity);
		excelFileReader.enterCellData(2, windDetailsFromUI);
		excelFileReader.enterCellData(3, temperatureDetailsFromUI);
		excelFileReader.writeWorkBook();
		
		

	}

}
