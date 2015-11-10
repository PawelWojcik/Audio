package net.pwojcik.audio.gracenote;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import net.pwojcik.audio.gracenote.xml.MajorQuery;
import net.pwojcik.audio.gracenote.xml.MajorResponse;

final class GracenoteXMLManager {

	public String marshallQueryToString(MajorQuery majorQuery) {
		try {
			StringWriter stringWriter = new StringWriter();
			JAXBContext jaxbContext = JAXBContext.newInstance(MajorQuery.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			marshaller.marshal(majorQuery, stringWriter);
			return stringWriter.toString();
		} catch (JAXBException e) {
			throw new GracenoteException(e.getMessage());
		}
	}

	public MajorResponse unmarshallQueryString(String queryString) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(MajorResponse.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(queryString);
			MajorResponse result = (MajorResponse) unmarshaller.unmarshal(reader);
			return result;
		} catch (JAXBException e) {
			throw new GracenoteException(e.getMessage());
		}
	}

}
