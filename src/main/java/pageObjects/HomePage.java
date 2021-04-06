package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generics.BasePage;
import managers.PageObjectManager;

public class HomePage extends BasePage {

	@FindBy(id = "h_sub_menu")
	private WebElement subMenuLink;

	// notnow
	@FindBy(xpath = "//a[@class='notnow']")
	private List<WebElement> alertNoThanks;

	@FindBy(xpath = "//a[text()='WEATHER']")
	private WebElement weatherLink;

	@FindBy(xpath = "//img[contains(@src,'ndtvlogo')]")
	private WebElement ndtvLogoLink;

	private PageObjectManager pageObjectManager;

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		pageObjectManager = new PageObjectManager(driver);
	}

	public boolean verifyHomePageIsDisplayed() {
		return waitForAnElement(ndtvLogoLink);
	}

	public boolean verifyBreakingNewsAlertPopUp() {
		return verifyElementPresent(alertNoThanks);
			
	}
	
	public void clickOnNoThanksAlertPopUp() {
		click(alertNoThanks.get(0));
	}

	public void clickOnSubMenu() {
		click(subMenuLink);

	}

	public WeatherPage clickOnWeatherLink() {
		click(weatherLink);
		return pageObjectManager.getWeatherPage();
	}

}
