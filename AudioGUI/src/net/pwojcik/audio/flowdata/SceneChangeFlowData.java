package net.pwojcik.audio.flowdata;

public final class SceneChangeFlowData extends AbstractFlowData {
	
	private final String moduleType;
	
	public SceneChangeFlowData(String moduleToRun) {
		moduleType = moduleToRun;
	}
	
	public String getModuleType() {
		return moduleType;
	}
	
}
