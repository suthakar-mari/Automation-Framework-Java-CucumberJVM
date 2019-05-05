package com.simonkay.javaframework.pageobjects;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.simonkay.javaframework.configurations.webdriver.WaitConditions;

public abstract class AbstractBasePageObject {
	private static final Logger LOG = LogManager.getLogger(AbstractBasePageObject.class);
	private final WebDriver driver;
	private final int timeToWait;
	private final WebDriverWait wait;
	private final String url;
	

	public AbstractBasePageObject(WebDriver driver, int implicitWait, String url) {
		this.driver = driver;
		timeToWait = implicitWait;
		wait = new WebDriverWait(getDriver(), timeToWait);
		this.url = url;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public boolean does_element_exist(By loc) {
		LOG.info("Checking if element exists using locator: " + loc);
		return driver.findElements(loc).size() != 0 ? true : false;
	}

	public void navigate_and_wait() {
		LOG.info("Navigating to page " + url);
		driver.get(url);
		wait.until(ExpectedConditions.urlToBe(url));
	}

	public void set_text(WebElement ele, String value) {
		LOG.info("Setting text on element: " + ele + " with the value: " + value);
		ele.clear();
		ele.sendKeys(value);
	}

	public void submit(WebElement ele) {
		LOG.info("Attempting to submit on element: " + ele);
		ele.submit();
	}

	public void selectDropDownByValue(WebElement ele, String valueToChoose) {
		LOG.info("Selecting dropdown value on: " + ele + " with the value " + valueToChoose);
		Select s = new Select(ele);
		wait_for_dropdown(ele);
		s.selectByVisibleText(valueToChoose);
	}

	public boolean is_text_present(String text) {
		LOG.info("Attempting to find text on the page: " + text);
		try {
			wait_until_true_or_timeout(WaitConditions.pageContainsText(text));
		} catch (TimeoutException te) {
			LOG.fatal(te.getMessage() + "\n\nPageSource:\n\n" + getDriver().getPageSource());
			return false;
		}
		return true;
	}

	private void wait_for_dropdown(WebElement ele) {
		LOG.info("Waiting for dropdown to be clickable: " + ele);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	protected WebElement find(By loc) {
		try {
			return getDriver().findElement(loc);
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException(ex.getMessage()
					+ "\n\nPageSource:\n\n" + getDriver().getPageSource());
		}
	}
	
    /**
     * wait until condition is true or timeout is reached
     * @throws TimeoutException 
     */
    protected <V> V wait_until_true_or_timeout(ExpectedCondition<V> isTrue) throws TimeoutException {
        Wait<WebDriver> wait = new WebDriverWait(this.driver, timeToWait)
                .ignoring(StaleElementReferenceException.class);
            return wait.until(isTrue);
    }

}
