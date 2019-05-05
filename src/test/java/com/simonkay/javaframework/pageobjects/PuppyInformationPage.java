package com.simonkay.javaframework.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PuppyInformationPage extends AbstractBasePageObject {
	private static final Logger LOG = LogManager.getLogger(PuppyInformationPage.class);
	
	private static final String relativePath = "/";
	
	@FindBy(xpath = "//*[@id='content']/div[2]/div/form/div/input[1]")
	private WebElement adoptMeButton;

	public PuppyInformationPage(WebDriver driver, int implicitWait, String url) {
		super(driver, implicitWait, url + relativePath);
		PageFactory.initElements(driver, this);
		LOG.info("Creating new PuppyInformationPage, PageElements initialized!");
	}
	
	public void select_adopt_me() {
		adoptMeButton.click();
	}

	@Override
	protected void load() {
		
	}

	@Override
	protected void isLoaded() throws Error {

		
	}

}
