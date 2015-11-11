package net.pwojcik.audio.module;

import java.util.ArrayList;
import java.util.Collection;

import net.pwojcik.audio.broadcast.AbstractBroadcastParticipant;

/**
 * Abstract Module representation.
 * @author Pawel Wojcik
 * @version 1.0
 * @param <T> any valid subclass of {@linkplain net.pwojcik.audio.CanvasDataContainer}
 */
public abstract class AbstractModule<T extends CanvasDataContainer> extends AbstractBroadcastParticipant
		implements Module {

	private Collection<String> observedFlowTypes = new ArrayList<>();
	private String moduleType;
	private T canvasDataContainer;

	@Override
	public final void setType(String type) {
		moduleType = type;
	}

	@Override
	public final String getType() {
		return moduleType;
	}

	@Override
	public final Collection<String> getObservedFlowTypes() {
		return observedFlowTypes;
	}

	@Override
	public void setObservedFlowTypes(Collection<String> observedTypes) {
		observedFlowTypes = observedTypes;
	}
	
	@Override
	public final T getCanvasDataContainer() {
		if(canvasDataContainer == null) {
			canvasDataContainer = produceCanvasDataContainer();
		}
		return canvasDataContainer;
	}
	
	@Override
	public void postConstruct() {
		// Do nothing by default.
	}

	protected abstract T produceCanvasDataContainer();
	
}
