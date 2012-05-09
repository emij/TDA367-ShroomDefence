package se.chalmers.tda367.std.mapeditor;

import se.chalmers.tda367.std.core.maps.MapItem;
import se.chalmers.tda367.std.core.tiles.IBoardTile;

/**
 * Represents the choices of default tile placement in the Map Editor new map wizard.
 * @author Emil Edholm
 * @date   May 9, 2012
 */
public enum DefaultTile {
	
	TERRAIN_TILE("Terrain tile", MapItem.TERRAIN_TILE),
	BUILDABLE_TILE("Buildable tile", MapItem.BUILDABLE_TILE);
	
	private final String fancyName;
	private final IBoardTile tile;
	private DefaultTile(String fancyName, IBoardTile tile){
		this.fancyName = fancyName;
		this.tile = tile;
	}
	
	@Override
	public String toString() {
		return this.fancyName;
	}
	
	public IBoardTile getTile() {
		return this.tile;
	}
}
