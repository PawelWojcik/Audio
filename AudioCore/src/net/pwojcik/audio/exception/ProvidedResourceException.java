package net.pwojcik.audio.exception;

public final class ProvidedResourceException extends AudioException {
	
	private static final long serialVersionUID = 6543471105103065977L;
	public static final String RESOURCE_NOT_PROVIDED = "Given unit does not provide declared type of resource.";
	public static final String RESOURCE_NOT_SUPPORTED = "No unit provide given type of resource.";
	
	public ProvidedResourceException(String msg) {
		super(msg);
	}
}
