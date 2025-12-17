package com.companyname.base;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.companyname.config.ConfigReader;
import com.companyname.exceptions.DriverInitializationException;

public final class DriverFactory {
	private DriverFactory() {}
	
	public static WebDriver createDriver() {
		
		try {
			String executionMode = ConfigReader.get("execution.mode");
			if("remote".equalsIgnoreCase(executionMode)) {
				return new RemoteWebDriver(new URL(ConfigReader.get("grid.url")),
						BrowserFactory.getOptions(ConfigReader.get("browser")));
			}
			return BrowserFactory.getLocalDriver(ConfigReader.get("browser")); 
		}
		
		catch (Exception e) {
			throw new DriverInitializationException("Failed to initialize WebDriver ", e);
		}
		
	}
}
