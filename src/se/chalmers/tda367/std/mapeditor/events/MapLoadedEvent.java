package se.chalmers.tda367.std.mapeditor.events;

/**
 * Represents a event fired when a map has been successfully loaded.
 * @author Emil Edholm
 * @date   May 12, 2012
 */
public final class MapLoadedEvent {
	private final boolean loadedSuccessfully;
	private final String errorMessage;
	
	public MapLoadedEvent(String errorMessage) {
		this.errorMessage = errorMessage;
		this.loadedSuccessfully = errorMessage == null || errorMessage.length() == 0;
	}
	
	/** Everything when smoothly */
	public MapLoadedEvent() {
		this(null);
	}

	/**
	 * @return true if the map loaded successfully, else false.
	 */
	public boolean didLoadSuccessfully() {
		return loadedSuccessfully;
	}

	/**
	 * @return the error message if one exists, else null.
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
}
