package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By firstName=By.id("input-firstname");
	private By lastName=By.id("input-lastname");
	private By email=By.id("input-email");
	private By telephone=By.id("input-telephone");
	private By password=By.id("input-password");
	private By confirmPassword=By.id("input-confirm");
	private By agreeCheckbox=By.xpath("//input[@name='agree']");
	private By continueBtn=By.xpath("//input[@value='Continue']");
	private By successMsg=By.cssSelector("div#content h1");
	private By registerLink=By.linkText("Register");
	private By logoutLink=By.linkText("Logout");
	
	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By successMessg = By.cssSelector("div#content h1");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public boolean userRegister(String firstName, String lastName, 
			String email, String telephone, String password,
			String subscribe) {

		eleUtil.doSendKeys(this.firstName,firstName);;;
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		} else {
			eleUtil.doClick(subscribeNo);
		}

		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);

		String successMesg = eleUtil.waitForElementVisible(successMessg, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT)
											.getText();
		System.out.println(successMesg);

		if (successMesg.contains(Constants.RGISTER_SUCCESS_MSG)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		} else {
			return false;
		}

	}

}
