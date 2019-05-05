package com.simonkay.javaframework.configurations.webdriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.simonkay.javaframework.utility.exceptions.InvalidDriverTypeSelectedException;

public class Driver extends EventFiringWebDriver {
	private static final Logger LOG = LogManager.getLogger(EventFiringWebDriver.class);

	private static WebDriver CURRENT_DRIVER;

	private static final Thread SHUTDOWN_HOOK = new Thread() {	
		@Override
		public void run() {
			LOG.debug("Executing multithreaded job for shutdown hook");
			killDriver();
		}
	};

	@Override
	public void close() {
		LOG.info("Attempting to close the driver");
		if (Thread.currentThread() != SHUTDOWN_HOOK) {
			throw new UnsupportedOperationException(
					"Driver will close when the JVM exits, do not manually call .close() on it");
		}
		try {
			super.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void killDriver() {		
		WebDriver driver = CURRENT_DRIVER;
		CURRENT_DRIVER = null;	
		if (driver != null) {
			LOG.info("Attempting to kill the driver instance");
			driver.quit();
		}
	}
	
	public Driver(String browserType) throws InvalidDriverTypeSelectedException {
		super(getCurrentDriver(browserType));
		LOG.info("Retrieved current browser for: " + browserType + ". Registering shutdown hook on the browser");
		Runtime.getRuntime().addShutdownHook(SHUTDOWN_HOOK);
	}

	private static WebDriver getCurrentDriver(String browserType)
			throws InvalidDriverTypeSelectedException {		
		switch (browserType.toLowerCase()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					"src/test/resources/binaries/chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--start-maximized");
			LOG.info("Chrome successfully set, returning new chromedriver with options: " + chromeOptions);
			return new ChromeDriver(chromeOptions);
		case "firefox":
			System.setProperty("webdriver.firefox.driver",
					"src/test/resources/binaries/geckodriver.exe");
			LOG.info("Firefox successfully set, returning new geckodriver with options: " + null);
			return new FirefoxDriver();
		default:
			throw new InvalidDriverTypeSelectedException(
					"Invalid driver specified, enter: 'chrome' or 'firefox' in the "
							+ "resources/framework.properties file, this can be passed at runtime using mvn -D arguments");
		}

	}

}
