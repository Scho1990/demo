package com.companyname.base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.companyname.config.ConfigReader;

//@Listeners(ChainTestListener.class)
public class BaseTest {
   
	@BeforeMethod
	public void setUp() {
		DriverManager.setDriver(DriverFactory.createDriver());
		DriverManager.getDriver().manage().window().maximize();
		ChainTestListener.log("Enter Url: "+ConfigReader.get("base.url"));
		DriverManager.getDriver().get(ConfigReader.get("base.url"));
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {
		try {
			if(DriverManager.getDriver()!=null) {
				if(!result.isSuccess()) {
				ChainTestListener.embed(attachScreenshot(), "image/png");	
				}
				ChainTestListener.log("Close the browser ");
				DriverManager.getDriver().quit();
			}
		} catch (Exception e) {
			System.err.println("Driver quit failed: " + e.getMessage());
		}
		finally {
			DriverManager.unload();
		}
	}
	
	public byte[] attachScreenshot() {
		return ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
	}
}
