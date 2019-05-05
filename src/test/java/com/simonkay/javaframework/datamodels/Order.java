package com.simonkay.javaframework.datamodels;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.simonkay.javaframework.dataproviders.DataProviderInjector;

public class Order {
	private static final Logger LOG = LogManager.getLogger(Order.class);

	private String orderName;
	private String orderAddress;
	private String orderEmail;
	private String orderPaymentType;

	public Order(String order_paymentType, DataProviderInjector data) {
		this.orderName = data.fullname();
		this.orderAddress = data.address();
		this.orderEmail = data.email();
		this.orderPaymentType = order_paymentType;
		LOG.debug("Creating order using data provider " + this.toString());
	}
	
	public Order(String orderName, String orderAddress, String orderEmail, String order_paymentType) {
		this.orderName = orderName;
		this.orderAddress = orderAddress;
		this.orderEmail = orderEmail;
		this.orderPaymentType = order_paymentType;
		LOG.debug("Creating order using manual data: " + this.toString());
	}

	public String getOrderName() {
		return this.orderName;
	}

	public String getOrderAddress() {
		return this.orderAddress;
	}

	public String getOrderEmail() {
		return this.orderEmail;
	}

	public String getOrderPaymentType() {
		return this.orderPaymentType;
	}

	@Override
	public String toString() {
		return "Order [orderName=" + orderName + ", orderAddress="
				+ orderAddress + ", orderEmail=" + orderEmail
				+ ", orderPaymentType=" + orderPaymentType + "]";
	}


}
