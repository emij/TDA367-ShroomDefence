package se.chalmers.tda367.std.core.tiles;

import se.chalmers.tda367.std.utilities.Sprite;

public class WaypointTile extends AbstractTile implements IWalkableTile {
	
	/**
	 * Create a new waypoint tile.
	 * @param sprite
	 */
	public WaypointTile(Sprite sprite) {
		super(sprite);
	}
	
	@Override
	public String toString() {
		return "w";
	}

}
