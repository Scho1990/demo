package com.companyname.base;

import java.sql.Driver;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

	private DriverManager() {}
	
	private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
	
	public static WebDriver getDriver() {
		return DRIVER.get();
	}
	
	public static void setDriver(WebDriver driver) {
		DRIVER.set(driver);
	}
	
	public static void unload() {
		DRIVER.remove(); // prevents memory leak in parallel execution
	}
}
