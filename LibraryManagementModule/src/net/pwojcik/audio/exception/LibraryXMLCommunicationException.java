package net.pwojcik.audio.exception;

import net.pwojcik.audio.exception.AudioException;

/**
 * Exception representing unexpected situations during 
 * communication with XML library descriptor file.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class LibraryXMLCommunicationException extends AudioException {

	private static final long serialVersionUID = -1767868527369741301L;

	public LibraryXMLCommunicationException(Throwable cause) {
		super(cause);
	}
}
