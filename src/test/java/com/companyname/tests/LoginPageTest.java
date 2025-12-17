package com.companyname.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.companyname.base.BaseTest;
import com.companyname.base.DriverManager;
import com.companyname.pages.LoginPage;

public class LoginPageTest extends BaseTest {
	
	@Test
	public void loginTest() {
		LoginPage login = new LoginPage(DriverManager.getDriver());
		login.login("Admin", "admin123");
		Assert.assertTrue(login.isPageHeaderDisplayed());
	}
	
	@Test
	public void invalidLoginTest() {
		LoginPage login = new LoginPage(DriverManager.getDriver());
		login.login("Admin22", "admin123");
	}

}
