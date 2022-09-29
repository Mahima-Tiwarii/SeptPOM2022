package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By emailId=By.id("input-email");
	private By password=By.id("input-password");
	private By loginBtn=By.xpath("//input[@value='Login']");
	private By forgotPaswdLink=By.xpath("(//a[contains(text(),'Forgotten Password')])[2]");
	private By registerLink=By.linkText("Register");
	private By accLogoutMsg=By.cssSelector("div#content h1");
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(this.driver);
	}
	
	public String getLoginPageTitle() {
		String title=eleUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
		System.out.println("login page title is: "+title);
		return title;
	}
	public String getLoginPageUrl() {
		String url=eleUtil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
		System.out.println("login page url is: "+url);
		return url;
	}
	
	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForElementVisible(forgotPaswdLink, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
	}
	public boolean isRegisterLinkExist() {
		return eleUtil.waitForElementVisible(registerLink, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
	
	}
	public AccountsPage doLogin(String userName, String pwd) {
		eleUtil.waitForElementVisible(emailId, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).sendKeys(userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		return new AccountsPage(driver);
	}
	
	public String getAccLogoutMsg() {
		String logoutMsg=eleUtil.waitForElementVisible(accLogoutMsg, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).getText();
		System.out.println("Logout msg is: "+logoutMsg);
		return logoutMsg;
	}
	
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForElementVisible(registerLink,Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).click();;
		System.out.println(driver.getTitle());
		return new RegisterPage(this.driver);
	}
}
