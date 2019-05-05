package com.simonkay.javaframework.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.simonkay.javaframework.datamodels.Order;

public class PuppyOrderPage extends AbstractBasePageObject {
	private static final Logger LOG = LogManager.getLogger(PuppyOrderPage.class);
	private static final String relativePath = "/orders/new?";
	
	@FindBy(xpath = "//*[@id='new_order']/div[6]/input")
	private WebElement placeOrderButton;
	
	@FindBy(id = "order_name")
	private WebElement orderNameTextbox;
	
	@FindBy(id = "order_address")
	private WebElement orderAddressTextbox;
	
	@FindBy(id = "order_email")
	private WebElement orderEmailTextbox;
	
	@FindBy(id = "order_pay_type")
	private WebElement orderPaymentTypeDropdown;

	public PuppyOrderPage(WebDriver driver, int implicitWait, String url) {
		super(driver, implicitWait, url + relativePath);
		PageFactory.initElements(driver, this);
		LOG.info("Creating new PuppyCartPage, PageElements initialized!");
	}

	public void order_puppy(Order order) {
		set_text(orderNameTextbox, order.getOrderName());
		set_text(orderAddressTextbox, order.getOrderAddress());
		set_text(orderEmailTextbox, order.getOrderEmail());
		selectDropDownByValue(orderPaymentTypeDropdown, order.getOrderPaymentType());
		submit(placeOrderButton);
	}

	@Override
	protected void load() {

	}

	@Override
	protected void isLoaded() throws Error {

	}

}
