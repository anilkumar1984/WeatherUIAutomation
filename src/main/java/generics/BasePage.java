package generics;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataProviders.ConfigFileReader;
import managers.FileReaderManager;
import utility.Utility;

public abstract class BasePage {

	private WebDriver driver;
	private WebDriverWait wait;
	private Actions actions;
	private JavascriptExecutor jse;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, FileReaderManager.getInstance().getConfigReader().getWebdriverWaitTime());

	}

	
	public String getAttribute(WebElement element,String attributeName) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return element.getAttribute(attributeName);
		
		 

	}

	public boolean verifyElementPresent(List<WebElement> element) {
		boolean found = false;

		if (element.size() > 0) {
			found = true;
		}
		return found;

	}

	public void moveToElement(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}

	public void click(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (TimeoutException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		// moveToElement(element);
		element.click();
	}

	public void clickUsingJavaScriptExceutor(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e) {
			throw new RuntimeException(e.getMessage());
		}
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", element);
	}

	public void enterText(WebElement element, String data) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		element.clear();
		element.click();
		element.sendKeys(data);
	}

	public String getDefaultValueFromInputBox(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		return element.getAttribute("name");

	}

	public boolean waitForAnElement(WebElement element) {
		boolean boolValue = false;
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			boolValue = true;
		} catch (TimeoutException e) {
			// throw new RuntimeException(e.getMessage());
		}

		return boolValue;
	}

	public void sendKeysDown(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		element.sendKeys(Keys.DOWN);

	}

	public void sendKeysEnter(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		element.sendKeys(Keys.ENTER);
	}

	public boolean verifyPageTitle(String expectedTitle) {
		boolean booleanValue = false;
		try {
			wait.until(ExpectedConditions.titleContains(expectedTitle));
			System.out.println(driver.getTitle());
			System.out.println(expectedTitle);
			booleanValue = true;

		} catch (TimeoutException e) {
			// throw new RuntimeException(e.getMessage());
		}

		return booleanValue;

	}

	public boolean verifyCurrentUrl(String expectedCurrentURL) {
		boolean booleanValue = false;
		try {
			wait.until(ExpectedConditions.urlContains(expectedCurrentURL));
			booleanValue = true;

		} catch (TimeoutException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		return booleanValue;

	}

	public boolean selectValueFromDropDown(WebElement element, String value) {

		boolean booleanValue = false;
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e) {
			throw new RuntimeException(e.getMessage());
		}
		Select se = new Select(element);
		List<WebElement> options = se.getOptions();

		for (WebElement option : options) {
			String text = option.getText();
			// System.out.println("Value from drop down is:"+text);
			if (text.contains(value)) {
				se.selectByVisibleText(value);
				booleanValue = true;
			} else {

			}
		}
		if (booleanValue == false) {
			// System.out.println("Drop down value: " + value + " is not present");
		}
		return booleanValue;

	}

	public boolean verifyCheckBoxStatus(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return element.isSelected();
	}

	public String getText(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return element.getText();
	}

}
