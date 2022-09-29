package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.utils.Constants;

public class LoginPageTest extends BaseTest{
	
	@Test
	public void loginPageTitleTest() {
		
		String actualTitle=loginPage.getLoginPageTitle();
		System.out.println(actualTitle);
		Assert.assertEquals(actualTitle, Constants.LOGIN_PAGE_TITLE);
	}

	@Test
	public void loginPageUrlTest() {
		String actUrl=loginPage.getLoginPageUrl();
		Assert.assertTrue(actUrl.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}
	@Test

	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	@Test
	public void RegisterLinkExistTest() {
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}
	
	@Test
	public void loginTest() {
		AccountsPage accPage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
}
