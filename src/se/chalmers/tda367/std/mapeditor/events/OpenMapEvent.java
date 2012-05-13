package se.chalmers.tda367.std.mapeditor.events;

import java.io.File;

/**
 * Represents the event that is fired when a open file dialog has choosen the map to load.
 * @author Emil Edholm
 * @date   May 12, 2012
 */
public final class OpenMapEvent {
	private final File selectedMap;
	
	public OpenMapEvent(File selectedMap) {
		this.selectedMap = selectedMap;
	}

	/**
	 * @return the selected map
	 */
	public File getSelectedMap() {
		return selectedMap;
	}
	
	
}
