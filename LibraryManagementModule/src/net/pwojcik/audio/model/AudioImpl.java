package net.pwojcik.audio.model;

public final class AudioImpl implements Audio {

	private final String path;
	private int directoryIdentifier;

	public AudioImpl(String filepath) {
		path = filepath;
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public int getDirectoryIdentifier() {
		return directoryIdentifier;
	}

	@Override
	public void setDirectoryIdentifier(int identifier) {
		directoryIdentifier = identifier;
	}

}
