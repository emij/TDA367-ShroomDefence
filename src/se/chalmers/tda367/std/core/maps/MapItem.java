package se.chalmers.tda367.std.core.maps;

import se.chalmers.tda367.std.core.tiles.BuildableTile;
import se.chalmers.tda367.std.core.tiles.IBoardTile;
import se.chalmers.tda367.std.core.tiles.PathTile;
import se.chalmers.tda367.std.core.tiles.TerrainTile;
import se.chalmers.tda367.std.utilities.Position;

/**
 * Represents the the info on each tile on a Map/Level.
 * @author Emil Edholm
 * @date   Apr 29, 2012
 */
public final class MapItem {
	/** A constant {@code PathTile}. No need for this to have separate instances. */
	public static final IBoardTile PATH_TILE = new PathTile();
	/** A constant {@code TerrainTile}. No need for this to have separate instances. */
	public static final IBoardTile TERRAIN_TILE = new TerrainTile();
	/** A constant {@code BuildableTile}. No need for this to have separate instances. */
	public static final IBoardTile BUILDABLE_TILE = new BuildableTile();
	
	// For lazy init and acts as a sort of cache.
	private static MapItem terrainMapItem = null;
	private static MapItem pathMapItem = null;
	private static MapItem buildableMapItem = null;
	
	private final IBoardTile tile;
	private final boolean isWaypoint, isStartPosition;
	private final Position waypointPosition;
	
	public MapItem(IBoardTile tile, Position waypointPosition, boolean isStartPosition) {
		this.tile = tile;
		this.isWaypoint = waypointPosition != null;
		this.isStartPosition = isStartPosition;
		this.waypointPosition = waypointPosition;
	}
	
	public MapItem(IBoardTile tile, Position waypointPosition) {
		this(tile, waypointPosition, false);
	}
	
	public MapItem(IBoardTile tile) {
		this(tile, null, false);
	}
	
	/**
	 * Create a new {@code MapItem} that consists of a {@code PathTile}
	 */
	public static MapItem createPathMapItem() {
		if(pathMapItem == null) {
			pathMapItem = new MapItem(PATH_TILE);
		}
		return pathMapItem;
	}
	
	/**
	 * Create a new {@code MapItem} that consists of a {@code TerrainTile}
	 */
	public static MapItem createTerrainMapItem() {
		if(terrainMapItem == null) {
			terrainMapItem = new MapItem(TERRAIN_TILE);
		}
		return terrainMapItem;
	}
	
	/**
	 * Create a new {@code MapItem} that consists of a {@code BuildableTile}
	 */
	public static MapItem createBuildableMapItem() {
		if(buildableMapItem == null) {
			buildableMapItem = new MapItem(BUILDABLE_TILE);
		}
		return buildableMapItem;
	}
	
	/**
	 * Returns the tile represented on the MapItem.
	 */
	public IBoardTile getTile() {
		return tile;
	}
	
	/**
	 * Whether or not the MapItem represents a waypoint tile.
	 */
	public boolean isWaypoint() {
		return isWaypoint;
	}
	
	/**
	 * Returns the waypoint position if this {@code MapItem} is a waypoint or {@code null} otherwise.
	 */
	public Position getWaypointPosition() {
		return this.waypointPosition;
	}
	
	/**
	 * Whether or not the {@code MapItem} represents the tile that enemies are inserted on the map
	 * @return true if it id the enemy start position.
	 */
	public boolean isStartPosition() {
		return this.isStartPosition;
	}
}
