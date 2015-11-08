package net.pwojcik.audio.gracenote.queryprocessor;

import net.pwojcik.audio.gracenote.xml.MajorQuery;

public interface QueryProcessor {
	MajorQuery processMajorQuery();
	void processAdditionalOperation(MajorQuery majorQuery);
	void setData(Object obj);
	void setUserID(String userID);
}
