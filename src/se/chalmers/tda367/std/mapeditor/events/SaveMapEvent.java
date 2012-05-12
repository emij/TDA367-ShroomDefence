package se.chalmers.tda367.std.mapeditor.events;

import java.io.File;

/**
 * Represents the event that is fired when the user wants to save his newly created map.
 * @author Emil Edholm
 * @date   May 12, 2012
 */
public final class SaveMapEvent {
	private final File newMapFile;
	
	public SaveMapEvent(File newMapFile) {
		this.newMapFile = newMapFile;
	}

	/**
	 * @return the new map {@code File} that should be saved to.
	 */
	public File getNewMapFile() {
		return newMapFile;
	}
}
