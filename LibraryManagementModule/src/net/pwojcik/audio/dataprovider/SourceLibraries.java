package net.pwojcik.audio.dataprovider;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Pawel Wojcik
 * @version 1.0
 */
@XmlRootElement(name = "SOURCELIBRARIES")
@XmlAccessorType(XmlAccessType.FIELD)
public final class SourceLibraries {

	@XmlElement(name = "SOURCE")
	private List<SourceDirectory> directories;

	public List<SourceDirectory> getDirectories() {
		return directories;
	}

	public void setDirectories(List<SourceDirectory> directories) {
		this.directories = directories;
	}

}
