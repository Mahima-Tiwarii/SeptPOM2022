package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterTest extends BaseTest{
	
	@BeforeClass
	public void regSetUp() {
		regPage=loginPage.navigateToRegisterPage();
		System.out.println("Register Page*************");
		
	}
	
	public String getRandomEmailId() {
		Random random=new Random();
		String email="TestAutomation"+random.nextInt(1000)+"@gmail.com";
		return email;
	}
	
	@DataProvider
	public Object[][] getRegisterData() {
		Object regData[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	@Test(dataProvider="getRegisterData")
	public void userRegisterTest(String firstName, String  lastName, String email, String telephone, String password, String subscribe) {
		
		Assert.assertTrue(regPage.userRegister(firstName,lastName,getRandomEmailId() ,telephone,password,subscribe));

		//Assert.assertTrue(regPage.userRegister("Mahima",  "Tiwari",  "mahima@gmail.com", "1234543212", "dfdffffref", "yes"));
	}

}
