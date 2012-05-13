package se.chalmers.tda367.std.mapeditor;

import se.chalmers.tda367.std.core.PlaceableTile;

/**
 * Represents the choices of default tile placement in the Map Editor new map wizard.
 * @author Emil Edholm
 * @date   May 9, 2012
 */
public enum DefaultTile {
	
	TERRAIN_TILE("Terrain tile") {
		@Override
		public PlaceableTile convertToPlaceableTile() { return PlaceableTile.TERRAIN_TILE; }
	},
	BUILDABLE_TILE("Buildable tile") {
		@Override
		public PlaceableTile convertToPlaceableTile() { return PlaceableTile.BUILDABLE_TILE; }
	};
	
	private final String fancyName;
	private DefaultTile(String fancyName){
		this.fancyName = fancyName;
	}
	
	/** Convert the {@code DefaultTile} to a {@code PlaceableTile}. */
	public abstract PlaceableTile convertToPlaceableTile();
	
	@Override
	public String toString() {
		return this.fancyName;
	}
}
