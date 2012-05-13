package se.chalmers.tda367.std.mapeditor.events;

import se.chalmers.tda367.std.core.PlaceableTile;

/**
 * Represents the event that is fired when a user clicks on the map to place a tile.
 * @author Emil Edholm
 * @date   May 12, 2012
 */
public final class TilePlacementEvent {
	private final PlaceableTile tile;
	private final int x, y;
	
	public TilePlacementEvent(PlaceableTile tile, int x, int y) {
		this.tile = tile;
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the tile that should be placed on the map.
	 */
	public PlaceableTile getTile() {
		return tile;
	}
	
	
}
