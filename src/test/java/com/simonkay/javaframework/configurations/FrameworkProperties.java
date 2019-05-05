package com.simonkay.javaframework.configurations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class FrameworkProperties {
	private static final Logger LOG = LogManager.getLogger(FrameworkProperties.class);

    @Autowired
    private Environment environment;
   
    
    public String getTestServerBaseAddress() {
    	String result = environment.getProperty("env.baseurl");
    	return result;
    }
    
    public String getGridAddress() {
    	String result = environment.getProperty("grid.endpoint");
    	LOG.info("Test run grid endpoint baseUrl: " + result);
    	return result;
    }
    
    public String getGridOrLocal() {
    	String result = environment.getProperty("run.on.browserstack.grid.local");
    	LOG.info("Test run test architecture: " + result);
    	return result;
    }
    
    public String getBrowserType() {
    	String result = environment.getProperty("browser");
    	LOG.info("Test run selected browser: " + result);
    	return result;
    }
    
    public String getPlatformType() {
    	String result = environment.getProperty("platform");
    	LOG.info("Test run selected platform: " + result);
    	return result;
    }
    
    public int seleniumImplicitWaitTime() {
    	int result = environment.getProperty("driver.implicit.wait", Integer.class);
    	return result;
    }
    
    public String getApplicationLanguage() {
    	String result = environment.getProperty("application.language");
    	LOG.info("Test run selected language: " + result);
    	return result;
    }
    
    public String getProductName() {
    	String result = environment.getProperty("product.name");
    	LOG.info("Test run product: " + result);
    	return result;
    }
    
    public String getDatabaseConn() {
    	String result = environment.getProperty("database.connection");
    	LOG.info("Database Instance: " + result);
    	return result;
    }
    
    public String getAngular() {
    	String result = environment.getProperty("is.frontend.angular");
    	LOG.info("Frontend is angular? " + result);
    	return result;
    }


    
    
}
