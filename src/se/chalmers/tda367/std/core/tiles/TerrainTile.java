package se.chalmers.tda367.std.core.tiles;

import se.chalmers.tda367.std.utilities.SpriteCreator;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * A simple class for representing a standard
 * terrain-tile, that is unwalkable and unbuildable.
 * @author Emil Johansson
 * @date Mar 26, 2012
 */
public class TerrainTile extends AbstractTile{
	//TODO: change sprite for the TerrainTile tile.
	private final static Sprite sprite = SpriteCreator.create("/images/gameplay/default_tile.jpg");
	
	/**
	 * Create a new TerrainTile.
	 */
	public TerrainTile(){
		super(sprite);
	}
	
	@Override
	public String toString(){
		return "$";
	}

}
