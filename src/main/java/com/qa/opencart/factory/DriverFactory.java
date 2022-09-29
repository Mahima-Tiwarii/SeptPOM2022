package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.github.dockerjava.core.dockerfile.DockerfileStatement.Env;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author mahimatiwari
 *
 */

public class DriverFactory {
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This method is use to initialize the driver
	 * @param browserName
	 * @return this method returns the WebDriver
	 */
	public WebDriver init_driver(Properties prop) {
		
		String browserName=prop.getProperty("browser");
		System.out.println("browser name is:"+browserName);
		
		optionsManager=new OptionsManager(prop);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver =new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver =new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		else if(browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			//driver =new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		else {
			System.out.println("Please pass the right browser: "+browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
		
	}
	
	public synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * this method is used to initialize the properties from the respective config file
	 * @return this returns properties class object with all the config properties
	 */
	public Properties init_prop() {
		FileInputStream ip=null;
		prop = new Properties();
		
		String envName = System.getProperty("env");
		System.out.println("Runnind test on environment: "+envName);
		
		if(envName==null) {
			System.out.println("No env is given...hence running test on QA: "+envName);
			try {
				ip=new FileInputStream("./src/test/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				
			switch (envName.toLowerCase()) {
			case "qa":
				System.out.println("++++++++++++++++++++++++=======running test on QA: "+envName);
				ip=new FileInputStream("./src/test/resources/config/qa.config.properties");
				
				break;
			case "dev":
				System.out.println("++++++++++++++++++++++++=======running test on: "+envName);
				ip=new FileInputStream("./src/test/resources/config/dev.config.properties");
				
				break;
			case "stage":
				System.out.println("++++++++++++++++++++++++=======running test on: "+envName);
				ip=new FileInputStream("./src/test/resources/config/stage.config.properties");
				
				break;
			case "uat":
				System.out.println("++++++++++++++++++++++++=======running test on: "+envName);
				ip=new FileInputStream("./src/test/resources/config/uat.config.properties");
				
				break;
			case "prod":
				System.out.println("++++++++++++++++++++++++=======running test on: "+envName);
				ip=new FileInputStream("./src/test/resources/config/config.properties");
				
				break;

			default:
				System.out.println("please pass the right env value..."+envName);
				break;
			}
		}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		try {
		prop.load(ip);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return prop;
		
//		try {
//			ip=new FileInputStream("./src/test/resources/config/config.properties");
//			
//			prop.load(ip);
//			
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}
	
	public String getScreenshot() {
		
		File srcFile= ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path=  "./screenshot/"+System.currentTimeMillis()+"png";
		File destination=new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path;
		
	}

}
