package net.pwojcik.audio.model.tag;

import net.pwojcik.audio.locale.I18N;

/**
 * Type of tag related to audio track.
 * @author Pawel Wojcik
 * @version 1.0
 */
public enum AudioTagType {
	TRACK_NUMBER("AudioTagType_TableLabel_TrackNumber"), 
	TITLE("AudioTagType_TableLabel_Title"), 
	ALBUM("AudioTagType_TableLabel_Album"), 
	ARTIST("AudioTagType_TableLabel_Artist"), 
	LENGTH("AudioTagType_TableLabel_Length"), 
	GENRE("AudioTagType_TableLabel_Genre"), 
	RELEASE_YEAR("AudioTagType_TableLabel_ReleaseYear"), 
	DISC_NUMBER("AudioTagType_TableLabel_DiscNumber"), 
	TOTAL_DISCS("AudioTagType_TableLabel_TotalDiscs"), 
	COMMENT("AudioTagType_TableLabel_Comment");

	private final String tableLabel;
	
	private AudioTagType(String label) {
		tableLabel = I18N.label(label);
	}
	
	/**
	 * Returns label shown in table headers for given tag type.
	 * @return label
	 */
	public String getTableLabel() {
		return tableLabel;
	}
}
