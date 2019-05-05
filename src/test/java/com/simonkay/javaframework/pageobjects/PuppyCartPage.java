package com.simonkay.javaframework.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PuppyCartPage extends AbstractBasePageObject {
	private static final Logger LOG = LogManager.getLogger(PuppyCartPage.class);
	private static final String relativePath = "/";

	@FindBy(xpath = "//*[@id='content']/div[2]/form[1]/div/input")
	private WebElement completeAdoptionButton;

	public PuppyCartPage(WebDriver driver, int implicitWait, String url) {
		super(driver, implicitWait, url + relativePath);
		PageFactory.initElements(driver, this);
		LOG.info("Creating new PuppyCartPage, PageElements initialized!");
	}

	public void complete_order() {
		completeAdoptionButton.click();
	}

	@Override
	protected void load() {

	}

	@Override
	protected void isLoaded() throws Error {

	}

}
