package managers;

import org.openqa.selenium.WebDriver;

import pageObjects.HomePage;
import pageObjects.WeatherPage;


public class PageObjectManager {

	private WeatherPage weatherPage;
	private HomePage homePage;
	
	private WebDriver driver;
	

	public PageObjectManager(WebDriver driver) {
		this.driver=driver;
		
	}

	public WeatherPage getWeatherPage() {
		return (weatherPage == null) ? new WeatherPage(driver) : weatherPage;
	}
	
	public HomePage getHomePage() {
		return (homePage == null) ? new HomePage(driver) : homePage;
	}

	
}
