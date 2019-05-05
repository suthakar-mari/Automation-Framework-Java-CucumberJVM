package com.simonkay.javaframework.utility.localisation;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.simonkay.javaframework.utility.enums.Language;

public class LocaleHelper implements LanguageImplementor {
	private static final Logger LOG = LogManager.getLogger(LocaleHelper.class);
	
	private Language language;
	private ResourceBundle resource;
	
	public LocaleHelper(String lang) {
		language = getLanguage(lang);
		LOG.debug("Retrieved application language as: " + this.language.toString());
	}
	
	@Override
	public String getResource(String value) {
		return resource.getString(value);		
	}

	@Override
	public Language getLanguage(String language) {
			
		switch(language.toLowerCase()) {
		case "english":
			LOG.debug("Retrieving language and setting resources for: English");
			resource = ResourceBundle.getBundle("localisation_resources.english", new Locale("en"));
			return Language.ENGLISH;
		case "italian":
			LOG.debug("Retrieving language and setting resources for: Italian");
			resource = ResourceBundle.getBundle("localisation_resources.italian", new Locale("it"));
			return Language.ITALIAN;
		case "spanish":
			LOG.debug("Retrieving language and setting resources for: Spanish");
			resource = ResourceBundle.getBundle("localisation_resources.spanish", new Locale("es"));
			return Language.SPANISH;
		case "french":
			LOG.debug("Retrieving language and setting resources for: French");
			resource = ResourceBundle.getBundle("localisation_resources.french", new Locale("fr"));
			return Language.FRENCH;
		default: LOG.debug("Bad Language set in properties or by maven, defaulting to ENGLISH");
			resource = ResourceBundle.getBundle("localisation_resources.english", new Locale("en"));
			return Language.ENGLISH;		
		}				
	}
	
	
}
