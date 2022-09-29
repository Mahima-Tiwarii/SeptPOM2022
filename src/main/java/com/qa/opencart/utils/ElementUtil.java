package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public void doClick(By locator) {
		getElement(locator).click();

	}

	public String doGetElementText(By locator) {
		return getElement(locator).getText();
	}

	public static By getBy(String locatorType, String locatorValue) {

		By locator = null;

		switch (locatorType.toLowerCase()) {
		case "id":
			locator = By.id(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "classname":
			locator = By.className(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		case "cssSelector":
			locator = By.cssSelector(locatorValue);
			break;

		case "linkText":
			locator = By.linkText(locatorValue);
			break;
		case "partialLinkText":
			locator = By.partialLinkText(locatorValue);
			break;

		case "tagName":
			locator = By.tagName(locatorValue);
			break;
		default:
			System.out.println("please pass the right locator....");
			break;
		}

		return locator;

	}

	public String doGetAttributeValue(By locator, String attributeValue) {
		return getElement(locator).getAttribute(attributeValue);
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public int getElementsCount(By locator) {
		return getElements(locator).size();

	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void clickOnElementFromSection(By locator, String value) {
		List<WebElement> eleList = getElements(locator);

		System.out.println(eleList.size());
		for (WebElement e : eleList) {
			String text = e.getText();
			System.out.println(text);

			if (text.contains(value)) {
				e.click();
				System.out.println("clicked");
				break;
			}
		}
	}

	public int getElementsListCount(By locator) {
		return getElements(locator).size();

	}

	public List<String> getAllElementsTextList(By locator) {
		List<String> eleTextList = new ArrayList<String>();
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String text = e.getText();
			eleTextList.add(text);

		}
		return eleTextList;

	}

	public void getAllElementsText(By locator) {

		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String text = e.getText();
			System.out.println(text);
		}

	}

	// ******************************Dropdown Utils*********************
	public void doSelectDropdownByIndex(By locator, int index) {

		Select select = new Select(getElement(locator));

		select.selectByIndex(index);

	}

	public void doSelectDropdownByVIsibleText(By locator, String visibleText) {

		Select select = new Select(getElement(locator));

		select.selectByVisibleText(visibleText);

	}

	public void doSelectDropdownByValue(By locator, String value) {

		Select select = new Select(getElement(locator));

		select.selectByValue(value);

	}
	public int getSelectDropDownValueCount(By locator) {
		return getElements(locator).size();
	}
	public List<String> getSelectValueDropdownList(By locator) {
		List<String> valueList=new ArrayList<String>();
		Select select=new Select(getElement(locator));
		select.getOptions();
		List<WebElement> dropDownOptions=select.getOptions();
		
		System.out.println(dropDownOptions.size());
		
		for(WebElement e: dropDownOptions) {
			String text=e.getText();
			
			valueList.add(text);
		}
		return valueList;
	}
	
	
	public void selectValueFromSelectDropDown(By locator, String value) {
		Select select=new Select(getElement(locator));
		select.getOptions();
		List<WebElement> dropDownOptions=select.getOptions();
		
		System.out.println(dropDownOptions.size());
		
		for(WebElement e: dropDownOptions) {
			String text=e.getText();
			if(text.contains(value)) {
				e.click();
				break;
			}

			
		}
		
	}
	
	
	public void printSelectDropdownValues(By locator) {
		Select select=new Select(getElement(locator));
		select.getOptions();
		List<WebElement> dropDownOptions=select.getOptions();
		
		System.out.println(dropDownOptions.size());
		
		for(WebElement e: dropDownOptions) {
			String text=e.getText();
			System.out.println(text);
			
		}
		
	}
	
	public void selectValueFromDropdown(By locator,String value) {
		List<WebElement> countryList=driver.findElements(locator);
		System.out.println(countryList.size());
		
		for(WebElement e:countryList) {
			String text=e.getText();
			if(text.contains(value)) {
				e.click();
			
			}
			
		}
		
	}
	
	//=====================================Waits=======================================================
	
	public WebElement waitForElementPresent(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public String waitForTitleContains(String titleFraction, int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.titleContains(titleFraction))) {
			return driver.getTitle();
		}
		return null;
	}
	
	public String waitForTitleIs(String titleValue, int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.titleIs(titleValue))) {
			return driver.getTitle();
		}
		return null;
	}
	
	public String waitForUrlContains(String urlFraction, int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.urlContains(urlFraction))) {
			return driver.getCurrentUrl();
		}
		return null;
	}
	
	public String waitForUrlToBe(String urlValue, int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.urlToBe(urlValue))) {
			return driver.getCurrentUrl();
		}
		return null;
	}

}
