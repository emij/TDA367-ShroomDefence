package se.chalmers.tda367.std.mapeditor;

import se.chalmers.tda367.std.core.Properties;
import se.chalmers.tda367.std.core.maps.MapItem;
import se.chalmers.tda367.std.core.tiles.PlayerBase;
import se.chalmers.tda367.std.utilities.Position;

/**
 * Represents the choices of placeable tiles in the MapEditor.
 * @author Emil Edholm
 * @date   May 10, 2012
 */
public enum PlaceableTile {
	TERRAIN_TILE("Terrain tile") {
		@Override
		public MapItem getMapItem(int x, int y) {
			return MapItem.createTerrainMapItem();
		}
	},
	BUILDABLE_TILE("Buildable tile") {
		@Override
		public MapItem getMapItem(int x, int y) {
			return MapItem.createBuildableMapItem();
		}
	},
	PATH_TILE("Path tile") {
		@Override
		public MapItem getMapItem(int x, int y) {
			return MapItem.createPathMapItem();
		}
	},
	PLAYER_BASE_TILE("PlayerBase tile") {
		@Override
		public MapItem getMapItem(int x, int y) {
			return new MapItem(new PlayerBase(2), getCenterPosition(x, y));
		}
	},
	ENEMY_START_TILE("Enemy start position") {
		@Override
		public MapItem getMapItem(int x, int y) {
			// No need for the first position the enemies are inserted to be a waypoint.
			return new MapItem(MapItem.PATH_TILE, null, true);
		}
	},
	WAYPOINT("Waypoint") {
		@Override
		public MapItem getMapItem(int x, int y) {
			return new MapItem(MapItem.PATH_TILE, getCenterPosition(x, y));
		}
	};
	
	private final String fancyName;
	private PlaceableTile(String fancyName) {
		this.fancyName = fancyName;
	}
	
	/** The x, y coordinate should be in the upper left corner. */
	private static Position getCenterPosition(int x, int y) {
		float cX = x + Properties.INSTANCE.getTileScale() / 2;
		float cY = y + Properties.INSTANCE.getTileScale() / 2;
		return Position.valueOf(cX, cY);
	}
	public abstract MapItem getMapItem(int x, int y);
	
	@Override
	public String toString() {
		return fancyName;
	}
}
