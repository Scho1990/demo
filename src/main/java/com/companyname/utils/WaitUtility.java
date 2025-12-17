package com.companyname.utils;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.companyname.base.DriverManager;
import com.companyname.constants.FrameworkConstants;
import com.companyname.exceptions.FrameworkException;

public final class WaitUtility {
	
	private WaitUtility() {}
	
	 /**
     * Returns WebDriverWait instance
     */
	private static WebDriverWait getWait() {
		return new WebDriverWait(DriverManager.getDriver(),
				Duration.ofSeconds(FrameworkConstants.EXPLICIT_WAIT));
	}
	
	 /**
     * Wait until element is visible
     */
	public static WebElement waitForVisibility(By locator) {

		try {
			return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (TimeoutException e) {

			throw new FrameworkException("Element not visible: " + locator, e);
		}

	}
	
	 /**
     * Wait until element is clickable
	 * @return 
     */
	
	public static WebElement waitForClickable(By locator) {
		
		try {
			return getWait().until(ExpectedConditions.elementToBeClickable(locator));
		} catch (TimeoutException e) {

			throw new FrameworkException("Element not clickable: " + locator, e);
		}
	}
	
	/**
     * Wait until element is present in DOM
     */
    public static WebElement waitForPresence(By locator) {
        try {
            return getWait()
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new FrameworkException(
                    "Element not present: " + locator, e);
        }
    }
    
    /**
     * Wait until element disappears (loader/spinner)
     */
    public static boolean waitForInvisibility(By locator) {
        try {
            return getWait()
                    .until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new FrameworkException(
                    "Element still visible: " + locator, e);
        }
    }
	
	/**
     * Wait until title contains text 
	 * @return 
     */
	
	public static Boolean waitForTitleContains(String title) {
		
		try {
			return getWait().until(ExpectedConditions.titleContains(title));
		} catch (TimeoutException e) {

			throw new FrameworkException("Title is not present: " + title, e);
		}
	}
	
	/**
     * Fluent wait (dynamic polling)
	 * @return 
     */
	
	public static WebElement FluentWait(By locator, int timeout, int polling) {
		
		try {
			Wait<WebDriver> wait = new org.openqa.selenium.support.ui.FluentWait<>(DriverManager.getDriver())
					.withTimeout(Duration.ofSeconds(timeout)).pollingEvery(Duration.ofSeconds(polling))
					.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);

			return wait.until(driver -> driver.findElement(locator));
		} catch (Exception e) {
			throw new FrameworkException("Fluentwait failed for: " + locator, e);
		}
	}
	
	/**
     * Wait for JavaScript page load
     */
	
	public static void waitForPageLoad() {
        try {
            getWait().until(driver ->
                    ((JavascriptExecutor) driver)
                            .executeScript("return document.readyState")
                            .equals("complete"));
        } catch (Exception e) {
            throw new FrameworkException("Page load timeout", e);
        }
    }

	
}
