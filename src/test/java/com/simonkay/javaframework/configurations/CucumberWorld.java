package com.simonkay.javaframework.configurations;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.simonkay.javaframework.datamodels.Order;

public class CucumberWorld {
	private static final Logger LOG = LogManager.getLogger(CucumberWorld.class);

	private HashMap<String, Object> state = new HashMap<String, Object>();
	
	
	public HashMap<String, Object> getOrdersMap() {
		return state;
	}
	
	public void addNewOrder(String orderAlias, Order order) {
		LOG.debug("Adding new order to cucumber world with alias: " + orderAlias + ". Order: " + order.toString());
		state.put(orderAlias, order);
	}
	
	public Order getOrderByAlias(String orderAlias) {
		LOG.debug("Retrieving order from cucumber world with alias: " + orderAlias);
		if (state.get(orderAlias) instanceof Order) {
			LOG.debug("Alias is infact an Order, casting from Obj to Order");
			return (Order) state.get(orderAlias);
		}
		LOG.fatal("ATTEMPTING TO RETRIEVE AN INCORRECT OBJECT TYPE FROM THE WORLD, IT WAS NOT AN INSTANCE OF ORDER, RETURNING NULL");
		return null;		
	}
	
}
