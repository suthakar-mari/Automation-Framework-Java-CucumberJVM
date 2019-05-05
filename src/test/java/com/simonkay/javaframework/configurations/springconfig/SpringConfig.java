package com.simonkay.javaframework.configurations.springconfig;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import com.simonkay.javaframework.configurations.CucumberWorld;
import com.simonkay.javaframework.configurations.FrameworkProperties;
import com.simonkay.javaframework.configurations.webdriver.Driver;
import com.simonkay.javaframework.pageobjects.PuppyAdoptionHomePage;
import com.simonkay.javaframework.pageobjects.PuppyCartPage;
import com.simonkay.javaframework.pageobjects.PuppyInformationPage;
import com.simonkay.javaframework.pageobjects.PuppyOrderPage;
import com.simonkay.javaframework.utility.exceptions.InvalidDriverTypeSelectedException;
import com.simonkay.javaframework.utility.localisation.LocaleHelper;
import com.simonkay.javaframework.utility.reporting.ReportEnvironmentHelper;


@Configuration
@PropertySource(value = {"classpath:/framework.properties"})
public class SpringConfig {
	private static final Logger LOG = LogManager.getLogger(SpringConfig.class);


	@Bean
	public CucumberWorld cucumberWorld() {
		return new CucumberWorld();
	}
	
	@Bean(destroyMethod = "quit")
	@Scope("singleton")
	public Driver driver() {
		Driver wd = null;
		
		try {
		wd = new Driver(properties().getBrowserType());
		} catch(InvalidDriverTypeSelectedException ex) {
			LOG.debug("Invalid driver specified" + ex);
			System.exit(1);
		}
		return wd;
	}

	@Bean
	public FrameworkProperties properties() {
		return new FrameworkProperties();
	}

	@Bean
    public PuppyAdoptionHomePage puppyAdoptionHomePage() {
        return new PuppyAdoptionHomePage(
                driver(),               
                properties().seleniumImplicitWaitTime(),
                properties().getTestServerBaseAddress()
        );
    }

	
	@Bean
    public PuppyInformationPage puppyInformationPage() {
        return new PuppyInformationPage(
                driver(),               
                properties().seleniumImplicitWaitTime(),
                properties().getTestServerBaseAddress()
        );
    }
	
	@Bean
    public PuppyCartPage puppyCartPage() {
        return new PuppyCartPage(
                driver(),               
                properties().seleniumImplicitWaitTime(),
                properties().getTestServerBaseAddress()
        );
    }
	
	@Bean
    public PuppyOrderPage puppyOrderPage() {
        return new PuppyOrderPage(
                driver(),               
                properties().seleniumImplicitWaitTime(),
                properties().getTestServerBaseAddress()
        );
	}
	
	@Bean
	public LocaleHelper localeHelper() {
		return new LocaleHelper(properties().getApplicationLanguage());
	}
	
	@Bean
	public ReportEnvironmentHelper envHelper() throws Exception {
		HashMap<String, String> props = new HashMap<String, String>();
		props.put("Language:", properties().getApplicationLanguage());
		props.put("Browser:", properties().getBrowserType());
		props.put("Environment:", properties().getTestServerBaseAddress());
		props.put("Architecture:", properties().getGridOrLocal());
		props.put("Grid Address:", properties().getGridAddress());
		props.put("Selenium Wait:", String.valueOf(properties().seleniumImplicitWaitTime()));
		props.put("Product Name:", String.valueOf(properties().getProductName()));
		props.put("Database Conn:", String.valueOf(properties().getDatabaseConn()));
		props.put("Angular Frontend:", String.valueOf(properties().getAngular()));	
		
		return new ReportEnvironmentHelper(props);

	}

	
	
}
