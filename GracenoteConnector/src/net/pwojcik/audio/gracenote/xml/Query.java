package net.pwojcik.audio.gracenote.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "QUERY")
@XmlAccessorType(XmlAccessType.FIELD)
public class Query {

	public enum QueryCommand {
		ALBUM_SEARCH, ALBUM_FETCH, REGISTER, COVER_SEARCH
	}

	public enum QueryMode {
		SINGLE_BEST, SINGLE_BEST_COVER
	}

	@XmlAttribute(name = "CMD")
	private QueryCommand queryCommand;
	@XmlElement(name = "CLIENT")
	private String clientIdentifier;
	@XmlElement(name = "MODE")
	private QueryMode queryMode;
	@XmlElement(name="GN_ID")
	private String albumGracenoteID;
	@XmlElement(name = "RANGE")
	private Range range;
	@XmlElement(name = "TEXT")
	private List<TextQueryCondition> textQC;
	@XmlElement(name="OPTION")
	private List<Option> options;

	public String getQueryCommand() {
		return queryCommand.toString();
	}

	public void setQueryCommand(QueryCommand newValue) {
		queryCommand = newValue;
	}

	public String getClientIdentifier() {
		return clientIdentifier;
	}

	public void setClientIdentifier(String clientIDFull) {
		clientIdentifier = clientIDFull;
	}

	public String getQueryMode() {
		return queryMode.toString();
	}

	public void setQueryMode(QueryMode newValue) {
		queryMode = newValue;
	}

	public String getAlbumGracenoteID() {
		return albumGracenoteID;
	}
	
	public void setAlbumGracenoteID(String newValue) {
		albumGracenoteID = newValue;
	}

	public Range getRange() {
		return range;
	}

	public void setRange(Range newValue) {
		range = newValue;
	}

	public List<TextQueryCondition> getTextQC() {
		return textQC;
	}
	
	public void addTextQueryCondition(TextQueryCondition newValue) {
		if(textQC == null) {
			textQC = new ArrayList<>();
		}
		textQC.add(newValue);
	}
	
	public List<Option> getOptions() {
		return options;
	}
	
	public void addOption(Option opt) {
		if(options == null) {
			options = new ArrayList<>();
		}
		options.add(opt);
	}
}
