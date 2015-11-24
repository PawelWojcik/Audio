package net.pwojcik.audio.container;

import java.util.ArrayList;
import java.util.Collection;

import net.pwojcik.audio.dataprovider.SourceDirectory;

/**
 * Container for selected rows in Library Module main table.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class LibrarySelectedContentContainer {

	private Collection<SourceDirectory> selectedItems;
	
	/**
	 * Sets new collection of selected items.
	 * @param selection selected items
	 */
	public void setSelectedItems(Collection<SourceDirectory> selection) {
		selectedItems = new ArrayList<>(selection);
	}
	
	/**
	 * Returns selected items.
	 * @return selected items
	 */
	public Collection<SourceDirectory> getSelectedItems() {
		return selectedItems;
	}
	
}
