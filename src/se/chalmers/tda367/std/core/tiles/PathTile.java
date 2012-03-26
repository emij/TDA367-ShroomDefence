package se.chalmers.tda367.std.core.tiles;

import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Class for representing a walkable tile.
 * @author Emil Johansson
 * @date Mar 26, 2012
 */
public class PathTile extends AbstractTile implements IWalkableTile {

	/**
	 * @param sprite
	 */
	public PathTile(Sprite sprite) {
		super(sprite);
	}
	
	@Override
	public String toString(){
		return "_";
	}

}
