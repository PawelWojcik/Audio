package net.pwojcik.audio.exception;

public final class CanvasDataContainerException extends AudioException {

	private static final long serialVersionUID = -157676078796817951L;
	private static final String INVALID_CANVAS_DATA_CONTAINER_INSTANCE = "Given module's CanvasDataContainer is not instance of DesktopCanvasDataContainer.";

	public CanvasDataContainerException() {
		this(INVALID_CANVAS_DATA_CONTAINER_INSTANCE);
	}

	public CanvasDataContainerException(String message) {
		super(message);
	}

}
