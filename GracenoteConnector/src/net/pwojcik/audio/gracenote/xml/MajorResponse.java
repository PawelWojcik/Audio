package net.pwojcik.audio.gracenote.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.pwojcik.audio.gracenote.xml.Response.ResponseType;

@XmlRootElement(name = "RESPONSES")
@XmlAccessorType(XmlAccessType.FIELD)
public class MajorResponse {

	@XmlElement(name = "RESPONSE")
	private List<Response> responses;
	@XmlElement(name = "MESSAGE")
	private String errorMessage;

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> newValue) {
		responses = newValue;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String newValue) {
		errorMessage = newValue;
	}

	public boolean isResponseValid() {
		boolean result = false;
		if (responses != null && responses.size()==1) {
			Response response = responses.get(0);
			ResponseType responseStatus = response.getResponseStatus();
			result = responseStatus.equals(ResponseType.OK) || 
					responseStatus.equals(ResponseType.NO_MATCH);
		}
		return result;
	}
}