package se.chalmers.tda367.std.core.tiles;

import se.chalmers.tda367.std.utilities.Sprite;

/**
 * A simple class for representing a standard
 * terrain-tile, that is unwalkable and unbuildable.
 * @author Emil Johansson
 * @date Mar 26, 2012
 */
public class TerrainTile extends AbstractTile{
	
	public TerrainTile(){
		super(new Sprite("/images/gameplay/background.png"));
	}
	
	@Override
	public String toString(){
		return "$";
	}

}
