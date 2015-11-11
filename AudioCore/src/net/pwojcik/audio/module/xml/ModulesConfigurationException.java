package net.pwojcik.audio.module.xml;

import net.pwojcik.audio.exception.AudioException;

/**
 * Exception used in unexpected situations during parsing XML modules configuration file.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class ModulesConfigurationException extends AudioException {

	private static final long serialVersionUID = 6646819775325714073L;

	public ModulesConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

}
