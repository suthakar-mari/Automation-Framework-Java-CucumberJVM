package com.simonkay.javaframework.dataproviders;

import com.github.javafaker.Faker;

public class DataProviderInjector implements DataProvider {
	
	private Faker f = new Faker();
	
	public DataProviderInjector() {
		
	}

	@Override
	public String fullname() {
		return f.name().fullName();
	}

	@Override
	public String address() {
		return f.address().fullAddress();
	}

	@Override
	public String email() {
		return f.internet().emailAddress();
	}
	

}
