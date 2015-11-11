package net.pwojcik.audio.dataprovider;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Pawel Wojcik
 * @version 1.0
 */
@XmlRootElement(name = "SOURCE")
@XmlAccessorType(XmlAccessType.FIELD)
public final class SourceDirectory {

	@XmlAttribute(name = "PATH")
	private String path;
	@XmlAttribute(name = "ID")
	private int id;
	@XmlElement(name = "SOURCE")
	private List<SourceDirectory> subDirectories;
	
	private int parentID;
	
	public SourceDirectory() {
	}
	
	public SourceDirectory(int newID) {
		id = newID;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int newID) {
		id = newID;
	}
	
	public int getParentID() {
		return parentID;
	}
	
	public void setParentID(int id) {
		parentID = id;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public List<SourceDirectory> getSubDirectories() {
		return subDirectories;
	}

	public void setSubDirectories(List<SourceDirectory> subDirectories) {
		this.subDirectories = subDirectories;
	}	
	
	@Override
	public String toString() {
		return path;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean equal = false;
		if(obj instanceof SourceDirectory) {
			SourceDirectory another = (SourceDirectory) obj;
			if((path==null && another.getPath()==null) || 
				path.equals(another.getPath())) {
				equal = true;
			}
		}
		return equal;
	}

}
