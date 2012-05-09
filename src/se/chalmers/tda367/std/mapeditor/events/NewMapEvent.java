package se.chalmers.tda367.std.mapeditor.events;

import se.chalmers.tda367.std.mapeditor.DefaultTile;

/**
 * Represents an event that is fired when the MapEditor wizard creates a new map.
 * @author Emil Edholm
 * @date   May 9, 2012
 */
public final class NewMapEvent {
	private final int width, height, level;
	private final DefaultTile defaultTile;
	public NewMapEvent(int width, int height, DefaultTile defaultTile, int level) {
		this.width = width;
		this.height = height;
		this.defaultTile = defaultTile;
		this.level = level;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public DefaultTile getDefaultTile() {
		return defaultTile;
	}
	
	/**
	 * @return the level this map represents.
	 */
	public int getLevel() {
		return this.level;
	}
}

