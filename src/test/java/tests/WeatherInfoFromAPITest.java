package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dataProviders.ExcelFileReader;
import generics.BaseTestForAPI;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import managers.APIManager;
import utility.Utility;

import static io.restassured.RestAssured.given;

import java.io.File;

public class WeatherInfoFromAPITest extends BaseTestForAPI {
	private static String expectedCity;
	
	@Parameters("city")
	@Test
	public void getWeatherInfoFromAPITest(String city) {
		expectedCity=city;
		File dataJsonFile=new File("./TestData/Data.json");
		JsonPath jsKey=JsonPath.from(dataJsonFile);
		
		logger = extent.createTest("getWeatherInfoFromAPI_TC", "Get the Weather Information for a city from the API");
		Response response=RestAssured.given().queryParam("q",expectedCity).queryParam("appid", jsKey.get("appid"))
		.log().all()
		.when().get(APIManager.getAPIResource());
		
		logger.info("API Response for the city: "+expectedCity+":");
		String responseBody=response.getBody().asPrettyString();
		
		logger.info(responseBody);
		JsonPath js=new JsonPath(responseBody);
		float actualKelvinTemparatureFromAPI=js.get("main.temp");
		logger.info("Temparature in Kelvin from API is: "+actualKelvinTemparatureFromAPI);
		double celsius=actualKelvinTemparatureFromAPI-273.15;
		celsius=Math.round(celsius*100.0)/100.0;
		String strCelsiusAPI=Double.toString(celsius);
		logger.info("Temparature in Celsius from API is: "+celsius);
		float windAPI=js.get("wind.speed");
		String strWindAPI=Float.toString(windAPI);
		logger.info("Wind from API is: "+windAPI);
		
		
		excelFileReader.enterCellData(4, Utility.getCurrentDateAndTime());
		excelFileReader.enterCellData(5, expectedCity);
		excelFileReader.enterCellData(6, strWindAPI);
		excelFileReader.enterCellData(7, strCelsiusAPI);
		
		excelFileReader.writeWorkBook();
		
		
	}

}
