package com.simonkay.javaframework.stepdefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.simonkay.javaframework.configurations.CucumberWorld;
import com.simonkay.javaframework.configurations.springconfig.SpringConfig;
import com.simonkay.javaframework.utility.localisation.LocaleHelper;

import cucumber.api.Scenario;

@ContextConfiguration(classes = SpringConfig.class)
public abstract class AbstractBaseStepDefinition {
	private static final Logger LOG = LogManager.getLogger(AbstractBaseStepDefinition.class);

	protected Scenario scenario;
	
	@Autowired
	protected CucumberWorld cucumberWorld;

	public void before(Scenario scenario) {
		this.scenario = scenario;
	}

	protected void embedTextInReport(String text) {
		LOG.info("Embedding text into the report");
		scenario.write(text);
	}

}
