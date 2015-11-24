package net.pwojcik.audio.gui.table;

import java.util.Comparator;

/**
 * Comparator for columns containing numeric values.
 * 
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class NumericTableColumnComparator implements Comparator<String> {

	@Override
	public int compare(String first, String sec) {
		int result;
		if (first == null || first.isEmpty()) {
			result = -1;
		} else if (sec == null || sec.isEmpty()) {
			result = 1;
		} else {
			int firstInt = Integer.parseInt(first);
			int secInt = Integer.parseInt(sec);
			if (firstInt < secInt) {
				result = -1;
			} else if (firstInt == secInt) {
				result = 0;
			} else {
				result = 1;
			}
		}

		return result;
	}

}
