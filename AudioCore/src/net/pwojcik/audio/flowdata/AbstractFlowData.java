package net.pwojcik.audio.flowdata;

public abstract class AbstractFlowData implements FlowData {

	@Override
	public final String getType() {
		return getClass().getName().toString();
	}
	
	@Override
	public boolean isInstanceOf(Class<?> classRepresentation) {
		return getType().equals(classRepresentation.getName().toString());
	}
	
}
