package net.pwojcik.audio.flowdata;

/**
 * FlowData implementation holding type of module which has to be shown at the moment.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class SceneChangeFlowData extends AbstractFlowData {
	
	private final String moduleType;
	
	public SceneChangeFlowData(String moduleToRun) {
		moduleType = moduleToRun;
	}
	
	/**
	 * Type of module that has to be shown.
	 * @return module type
	 */
	public String getModuleType() {
		return moduleType;
	}
	
}
