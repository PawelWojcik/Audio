package net.pwojcik.audio.exception;

/**
 * Abstract Exception class grouping all exceptions that may occur in application.
 * @author Pawel Wojcik
 * @version 1.0
 */
public abstract class AudioException extends RuntimeException {

	private static final long serialVersionUID = -3840150966069985987L;

	public AudioException(String message) {
		super(message);
	}
	
	public AudioException(String message, Throwable cause) {
		super(message, cause);
	}
}
