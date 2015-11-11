package net.pwojcik.audio.flowdata;

/**
 * Abstract representation of FlowData.
 * @author Pawel Wojcik
 * @version 1.0
 */
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
