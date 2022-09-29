package com.qa.opencart.tests;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accSetUp() {
		accPage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
	}
	
	@Test
	public void accPageTitleTest() {
		String title=accPage.getAccPageTitle();
		System.out.println(accPage.getAccPageTitle());
		Assert.assertEquals(title, Constants.ACCOUNTS_PAGE_TITLE);
		
	}
	
	@Test
	public void accPageHeaderTest() {
		String accPageHeader=accPage.getAccPageHeader();
		System.out.println("Accounts Page Header is: "+accPageHeader);
		Assert.assertEquals(accPageHeader, Constants.ACCOUNTS_PAGE_HEADER);
	}
	
	@Test
	public void accPageSecHeaderTest() {
		List<String> actAccSecList=accPage.getAccSectionHeader();
		System.out.println("Account Page section header is: "+actAccSecList);
		Collections.sort(actAccSecList);
		Collections.sort(Constants.ACCOUNTS_PAGE_SECTIONS_LIST);
		Assert.assertEquals(actAccSecList,Constants.ACCOUNTS_PAGE_SECTIONS_LIST);
		
	}
	
	@Test
	public void isUserLoggedOutTest() {
		loginPage=accPage.clickOnLogout();
		Assert.assertEquals(loginPage.getAccLogoutMsg(),Constants.USER_LOGOUT_MSG);
		
	}
	@DataProvider
	public Object[][] getProductName() {
		return new Object[][] {
			{"Macbook"},
				{"iMac"},
				{"Samsung"},
				{"Apple"}
				};
		}
		
	
	@Test(dataProvider="getProductName")
	public void searchTest(String productName) {
		commPage=new CommonsPage(driver);
		searchResultsPage=commPage.doSearch(productName);
		String actResultsPageHeader=searchResultsPage.getResultsPageHeader();
		System.out.println(actResultsPageHeader);
		Assert.assertTrue(actResultsPageHeader.contains(productName));
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro"},
				{"iMac", "iMac"},
				{"Samsung","Samsung SyncMaster 941BW"},
				{"Apple","Apple Cinema 30\""}
				};
		}
	
	@Test(dataProvider="getProductData")
	public void productInfoTest(String productName, String mainProductName) {
		commPage=new CommonsPage(driver);
		searchResultsPage=commPage.doSearch(productName);
		String actResultsPageHeader=searchResultsPage.getResultsPageHeader();
		System.out.println(actResultsPageHeader);
		productInfoPage=searchResultsPage.selectProductName(mainProductName);
		String mainProductNameValue=productInfoPage.getMainProductName();
		System.out.println(mainProductNameValue);
		Assert.assertEquals(mainProductNameValue, mainProductName);
	}

}
