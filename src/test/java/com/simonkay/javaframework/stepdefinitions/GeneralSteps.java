package com.simonkay.javaframework.stepdefinitions;

import com.simonkay.javaframework.datamodels.Order;
import com.simonkay.javaframework.dataproviders.DataProviderInjector;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class GeneralSteps extends AbstractBaseStepDefinition {
	
	@Before
	public void before(Scenario scenario) {
		super.before(scenario);
	}
	
	/*
	 * Creates a new order and adds it to the cucumberWorld object for shared state among stepdefinitions
	 */
	@Given("^The order \"([^\"]*)\" exists$")
	public void the_order_exists(String orderAlias) throws Throwable {
		Order order = new Order("Credit card", new DataProviderInjector());
		cucumberWorld.addNewOrder(orderAlias, order);    
	}


}
