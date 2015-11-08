package net.pwojcik.audio.gracenote.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "QUERIES")
@XmlAccessorType(XmlAccessType.FIELD)
public class MajorQuery {
	
	@XmlElement(name="AUTH")
	private Authorization authorization;
	@XmlElement(name="QUERY")
	private List<Query> queriesList; 
	
	public Authorization getAuthorization() {
		return authorization;
	}
	
	public void setAuthorization(Authorization newValue) {
		authorization = newValue;
	}

	public List<Query> getQueriesList() {
		return queriesList;
	}

	public void addQuery(Query query) {
		if(queriesList == null) {
			queriesList = new ArrayList<>();
		}
		queriesList.add(query);
	}
}
