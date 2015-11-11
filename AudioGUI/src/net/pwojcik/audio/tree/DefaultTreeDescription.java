package net.pwojcik.audio.tree;

import java.util.Optional;

/**
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class DefaultTreeDescription implements TreeDescription {

	public enum TreeDescriptionMode {
		WITH_ICON, WITH_PARENT
	}

	private String treeLabel;
	private Optional<String> treeIconPath;
	private Optional<String> parentModule;

	public DefaultTreeDescription(String label) {
		treeLabel = label;
		treeIconPath = Optional.empty();
		parentModule = Optional.empty();
	}

	public DefaultTreeDescription(String label, String treePathOrParentType, TreeDescriptionMode mode) {
		this(label);
		if (mode == TreeDescriptionMode.WITH_ICON) {
			treeIconPath = Optional.of(treePathOrParentType);
		} else if (mode == TreeDescriptionMode.WITH_PARENT) {
			parentModule = Optional.of(treePathOrParentType);
		}
	}

	public DefaultTreeDescription(String label, String treeIcon, String parent) {
		treeLabel = label;
		treeIconPath = Optional.of(treeIcon);
		parentModule = Optional.of(parent);
	}

	@Override
	public Optional<String> getTreeIconPath() {
		return treeIconPath;
	}

	@Override
	public String getTreeLabel() {
		return treeLabel;
	}

	@Override
	public Optional<String> getParentModule() {
		return parentModule;
	}
}
