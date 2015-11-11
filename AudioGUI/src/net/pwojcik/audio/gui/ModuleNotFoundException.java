package net.pwojcik.audio.gui;

import net.pwojcik.audio.exception.AudioException;

/**
 * Exception representing unexpected situations during module loading.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class ModuleNotFoundException extends AudioException {

	private static final long serialVersionUID = -672147698792893895L;
	private static final String REPLACEMENT_NEEDLE = "*";
	private static final String MESSAGE = "Module with name " + REPLACEMENT_NEEDLE + " has not been found.";

	public ModuleNotFoundException(String moduleName) {
		super(MESSAGE.replace(REPLACEMENT_NEEDLE, moduleName));
	}
}
