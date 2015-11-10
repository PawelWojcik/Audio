package net.pwojcik.audio.module;

import java.util.ArrayList;
import java.util.Collection;

import net.pwojcik.audio.broadcast.AbstractBroadcastParticipant;

public abstract class AbstractModule extends AbstractBroadcastParticipant implements Module {
	
	private Collection<String> observedFlowTypes = new ArrayList<>();
	private String moduleType;
	
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
	
}
