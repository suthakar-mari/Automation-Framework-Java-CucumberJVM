package com.simonkay.javaframework.configurations.webdriver;


import io.qameta.allure.Attachment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;

import com.simonkay.javaframework.utility.reporting.ReportEnvironmentHelper;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class WebDriverHooks {
	private static boolean runOnce = true;
	private static final Logger LOG = LogManager.getLogger(WebDriverHooks.class);
	
	@Autowired
	private Driver driver;
	
	@Autowired
	private ReportEnvironmentHelper rh;


	
	@Before
	public void log_scenario_started(Scenario scenario) {
		LOG.debug("Beginning of scenario: " + scenario.getId());
	}
	
	@Before
	public void delete_browser_cookies(Scenario scenario) {
		LOG.info("Deleting browser cookies after: " + scenario.getName());
		driver.manage().deleteAllCookies();
	}
	
	
	@After
	public void add_failure_attachments_if_needed(Scenario scenario) {
		if (scenario.isFailed()) {
			check_if_pagesource(scenario);
			check_if_screenshot(scenario);
		}
	}
	
	
	@Attachment(value = "failed_pagesource", type = "text/plain")
	public String  check_if_pagesource(Scenario scenario) {
		return driver.getPageSource().toString();
	}
	
	@Attachment(value = "failed_screenshot", type = "image/png")
	public byte[] check_if_screenshot(Scenario scenario) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
	
	


}
