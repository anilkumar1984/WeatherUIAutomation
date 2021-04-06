package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generics.BasePage;
import managers.PageObjectManager;

public class WeatherPage extends BasePage {

	private PageObjectManager pageObjectManager;

	@FindBy(xpath = "//div[@class='outerContainer']")
	private List<WebElement> listOfCitiesOnTheMap;

	@FindBy(xpath = "//div[@class='leaflet-popup-content-wrapper']//span")
	private List<WebElement> popUpCityContent;

	@FindBy(xpath = "(//div[@class='leaflet-popup-content-wrapper']//span[2])[1]")
	private WebElement popUpCityContentName;

	@FindBy(xpath = "//input[@id='searchBox']")
	private WebElement searchBox;

	@FindBy(xpath = "//div[@class='message']//input")
	private List<WebElement> searchedCityNameCheckbox;

	private ArrayList<String> listTemperatureDetailsOfTheCity;
	private int index;

	public WeatherPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		pageObjectManager = new PageObjectManager(driver);
	}

	public boolean verifyWeatherPageIsDisplayed() {
		return waitForAnElement(searchBox);
	}

	public void searchForCity(String cityName) {
		enterText(searchBox, cityName);

	}

	public boolean verifyActualCityDisplayed(String attributeName, String expectedCity) {
		boolean found = false;
		int count = searchedCityNameCheckbox.size();
		for (int i = 0; i < count; i++) {
			String actualCity = searchedCityNameCheckbox.get(i).getAttribute(attributeName);
			if (actualCity.equalsIgnoreCase(expectedCity)) {
				found = true;
				index = i;
				break;
			}
		}

		return found;

	}

	public boolean verifyCityDetailsPopUpIsDisplayed() {
		return verifyElementPresent(popUpCityContent);
	}

	public ArrayList<String> getTemparatureDetails() {

		listTemperatureDetailsOfTheCity = new ArrayList<String>();
		int count = popUpCityContent.size();
		for (int i = 1; i < count; i++) {
			String text = popUpCityContent.get(i).getText();
			//System.out.println("Text 1: " + text);
			listTemperatureDetailsOfTheCity.add(text);
		}
		return listTemperatureDetailsOfTheCity;
	}

	public void clickCheckboxOfSearchedCity() {
		System.out.println("Index found: " + index);
		if (!searchedCityNameCheckbox.get(index).isSelected()) {
			click(searchedCityNameCheckbox.get(index));
		}

	}
	
	

	public boolean clickOnTheCitySelectedInsideTheMap(String expectedTitle) {
		boolean found=false;
		int count = listOfCitiesOnTheMap.size();
		for (int i = 0; i < count; i++) {
			if (waitForAnElement(listOfCitiesOnTheMap.get(i))) {
				String actualTitle = listOfCitiesOnTheMap.get(i).getAttribute("title");
				if (actualTitle.equalsIgnoreCase(expectedTitle)) {
					click(listOfCitiesOnTheMap.get(i));
					found=true;
					break;
				}
			}
		}
		return found;

	}

}
