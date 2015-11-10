package net.pwojcik.audio.exception;

public class AudioException extends RuntimeException {

	private static final long serialVersionUID = -3840150966069985987L;

	public AudioException(String message) {
		super(message);
	}
	
	public AudioException(String message, Throwable cause) {
		super(message, cause);
	}
}
