package com.simonkay.javaframework.utility.localisation;

import com.simonkay.javaframework.utility.enums.Language;

public interface LanguageImplementor {
	String getResource(String value);
	Language getLanguage(String value);
}
