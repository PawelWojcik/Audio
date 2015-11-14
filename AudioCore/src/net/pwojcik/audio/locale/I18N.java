package net.pwojcik.audio.locale;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Static factory of labels used in application
 * 
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class I18N {
	public static final String I18N_FILENAME = "net.pwojcik.audio.locale.messages";
	public static final Locale POLISH = new Locale("pl", "PL");
	private static final String LABEL_NOT_FOUND = "[LABEL NOT FOUND]";

	public static Collection<Locale> getAvailableLocales() {
		List<Locale> locales = new ArrayList<>();
		locales.add(I18N.POLISH);
		locales.add(Locale.ENGLISH);
		return locales;
	}

	public static String label(String name) {
		return label(name, I18N_FILENAME);
	}

	public static String label(String name, String bundleName) {
		return label(name, bundleName, POLISH);
	}

	public static String label(String name, String bundleName, Locale locale) {
		String label;
		try {
			label = ResourceBundle.getBundle(bundleName, locale).getString(name);
		} catch (MissingResourceException exception) {
			label = LABEL_NOT_FOUND;
		}
		return label;
	}

	public static String labelWithParameters(String name, Object... params) {
		String label;
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(I18N_FILENAME, POLISH);
			MessageFormat formatter = new MessageFormat("");
			formatter.setLocale(POLISH);
			formatter.applyPattern(bundle.getString(name));
			label = formatter.format(params);
		} catch (MissingResourceException exception) {
			label = LABEL_NOT_FOUND;
		}
		return label;
	}

	private I18N() {
	}

}
