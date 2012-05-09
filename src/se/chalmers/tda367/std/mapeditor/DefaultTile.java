package se.chalmers.tda367.std.mapeditor;

/**
 * Represents the choices of default tile placement in the Map Editor new map wizard.
 * @author Emil Edholm
 * @date   May 9, 2012
 */
public enum DefaultTile {
	
	TERRAIN_TILE("Terrain tile"),
	BUILDABLE_TILE("Buildable tile");
	
	private final String fancyName;
	private DefaultTile(String fancyName){
		this.fancyName = fancyName;
	}
	
	@Override
	public String toString() {
		return fancyName;
	}
}
