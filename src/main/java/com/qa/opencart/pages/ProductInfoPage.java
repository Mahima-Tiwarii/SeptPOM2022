package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By mainProductName=By.cssSelector("div#content h1");
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(this.driver);
	}
	public String getMainProductName() {
		return eleUtil.doGetElementText(mainProductName);
	}

}
