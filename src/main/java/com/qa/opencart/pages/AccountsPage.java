package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logoHeader=By.xpath("//img[@title='naveenopencart']");
	private By accountSectionHeader=By.cssSelector("div#content h2");
	private By logoutLink=By.linkText("Logout");
	private By search =By.name("search");
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(this.driver);
		
	}
	public String getAccPageTitle() {
		return eleUtil.waitForTitleIs(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	public String getAccPageUrl() {
		return eleUtil.waitForUrlContains(Constants.ACCOUNTS_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	public String getAccPageHeader() {
		return eleUtil.waitForElementVisible(logoHeader, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT) .getAttribute("title");	
		
	}
	public List<String> getAccSectionHeader() {
		List<WebElement> AccSectionHeader= eleUtil.waitForElementsVisible(accountSectionHeader, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT);
		List<String> accSecValueList = new ArrayList<String>();
		for(WebElement e:AccSectionHeader) {
			String text=e.getText();
			accSecValueList.add(text);
		}
		return accSecValueList;
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
	}
	
	public LoginPage clickOnLogout() {
	if(isLogoutLinkExist()) {
		eleUtil.doClick(logoutLink);
		return new LoginPage(driver);
	}
	return null;
	}
	
	public boolean isSearchExist() {
		return eleUtil.waitForElementVisible(search, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
	}

}
