package com.companyname.base;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class BrowserFactory {

	private BrowserFactory() {}
	
	public static WebDriver getLocalDriver(String browser) {
		switch (browser.toLowerCase()) {
		case "firefox":
			return new FirefoxDriver();
			
		case "edge":
			return new EdgeDriver();

		default:
			return new ChromeDriver();
		}
	}
	
	public static MutableCapabilities getOptions(String browser) {

        switch (browser.toLowerCase()) {
            case "firefox":
                return new FirefoxOptions();
            case "edge":
                return new EdgeOptions();
            default:
                return new ChromeOptions();
        }
    }
}
