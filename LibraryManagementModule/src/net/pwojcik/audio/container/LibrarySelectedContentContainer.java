package net.pwojcik.audio.container;

import java.util.ArrayList;
import java.util.Collection;

import net.pwojcik.audio.dataprovider.SourceDirectory;

public final class LibrarySelectedContentContainer {

	private Collection<SourceDirectory> selectedItems;
	
	public void setSelectedItems(Collection<SourceDirectory> selection) {
		selectedItems = new ArrayList<>(selection);
	}
	
	public Collection<SourceDirectory> getSelectedItems() {
		return selectedItems;
	}
	
}
