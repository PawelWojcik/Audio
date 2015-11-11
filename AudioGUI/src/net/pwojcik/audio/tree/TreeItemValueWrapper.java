package net.pwojcik.audio.tree;

/**
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class TreeItemValueWrapper {
	private final String label;
	private final String correspondingModule;
	
	public TreeItemValueWrapper(String newLabel, String module) {
		label = newLabel;
		correspondingModule = module;
	}
	
	public String getCorrespondingModule() {
		return correspondingModule;
	}
	
	@Override
	public String toString() {
		return label;
	}
}
