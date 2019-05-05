package com.simonkay.javaframework.stepdefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

import com.simonkay.javaframework.pageobjects.PuppyAdoptionHomePage;
import com.simonkay.javaframework.pageobjects.PuppyCartPage;
import com.simonkay.javaframework.pageobjects.PuppyInformationPage;
import com.simonkay.javaframework.pageobjects.PuppyOrderPage;
import com.simonkay.javaframework.utility.localisation.LocaleHelper;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PuppyAdoptionSteps extends AbstractBaseStepDefinition {
	private static final Logger LOG = LogManager.getLogger(PuppyAdoptionSteps.class);

	@Autowired
	private PuppyAdoptionHomePage puppyAdoptionHomePage;
	@Autowired
	private PuppyInformationPage puppyInformationPage;
	@Autowired
	private PuppyCartPage puppyCartPage;
	@Autowired
	private PuppyOrderPage puppyOrderPage;
	@Autowired
	private LocaleHelper localeHelper;
	
	@Before
	public void before(Scenario scenario) {
		super.before(scenario);
	}
	
	/**
	 * Navigates to the application home page
	 * @throws Throwable
	 */
	@Given("^I am on the homepage$")
	public void i_am_on_the_homepage() throws Throwable {
		puppyAdoptionHomePage.navigate_and_wait();
	}

	/**
	 * Places an adoption order for a puppy, reading in an order from an alias from the world object
	 * @param orderAlias
	 * @throws Throwable
	 */
	@When("^I adopt a puppy providing \"([^\"]*)\"$")
	public void i_adopt_a_puppy_providing(String orderAlias) throws Throwable {
		LOG.debug("Attempting to adopt a puppy using order" + orderAlias);
		puppyAdoptionHomePage.select_a_puppy();
		puppyInformationPage.select_adopt_me();
		puppyCartPage.complete_order();
		puppyOrderPage.order_puppy(cucumberWorld.getOrderByAlias(orderAlias));
	}

	/**
	 * Asserts that the puppy successful registration message appears after succesful adoptions
	 * @throws Throwable
	 */
	@Then("^I should see the successful adoption message$")
	public void i_should_see_the_successful_adoption_message() throws Throwable {
		LOG.debug("Asserting successful message in correct language appears");
		assertThat(puppyAdoptionHomePage.is_text_present(localeHelper.getResource("successful.adoption.message"))).isTrue();
	}
	
	/**
	 * Auto failing just to demonstrate failure in the report for 1 scenario
	 * @throws Throwable
	 */
	@Then("^I will fail on purpose$")
	public void i_will_fail_on_purpose() throws Throwable {
	    assertThat(true).isFalse();
	}

}
