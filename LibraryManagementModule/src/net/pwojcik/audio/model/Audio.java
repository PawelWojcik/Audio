package net.pwojcik.audio.model;

public interface Audio {

	String getPath();
	
	int getDirectoryIdentifier();
	
	void setDirectoryIdentifier(int identifier);
}
