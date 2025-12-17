package com.companyname.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.companyname.utils.WaitUtility;

public class LoginPage {
    private WebDriver driver;

  
    private By usernamefield = By.xpath("//input[@name='username']");

    private By passwordfield= By.xpath("//input[@name='password']");

    private By buttonSubmit = By.cssSelector("button.oxd-button.oxd-button--medium[type=\"submit\"]");

    private By forgotPwdLink =By.xpath("//p[contains(text(),\'Forgot your password?\')]");
    
    private By timeAtWorkText =By.xpath("//p[contains(text(),\'Time at Work\')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String user, String pass) {
    	//WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(20));
    	//wait.until(ExpectedConditions.visibilityOfElementLocated(usernamefield));
        //driver.findElement(usernamefield).sendKeys(user);
        //driver.findElement(passwordfield).sendKeys(pass);
        //driver.findElement(buttonSubmit).click();
        ChainTestListener.log("Enter Username: "+user);
    	WaitUtility.waitForVisibility(usernamefield).sendKeys(user);
    	ChainTestListener.log("Enter Password: "+pass);
    	WaitUtility.waitForVisibility(passwordfield).sendKeys(pass);
    	ChainTestListener.log("click on submit button");
    	WaitUtility.waitForClickable(buttonSubmit).click();
        
    }
    
    public boolean isPageHeaderDisplayed() {
    	return driver.findElement(timeAtWorkText).isDisplayed();
    }

}

