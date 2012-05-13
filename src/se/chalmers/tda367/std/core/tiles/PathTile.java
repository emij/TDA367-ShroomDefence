package se.chalmers.tda367.std.core.tiles;

import se.chalmers.tda367.std.utilities.SpriteCreator;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Class for representing a walkable tile.
 * @author Emil Johansson
 * @date Mar 26, 2012
 */
public class PathTile extends AbstractTile implements IWalkableTile {
	//TODO: change sprite for the Path tile.
	private final static Sprite sprite = SpriteCreator.create("/images/gameplay/path_tile.jpg");

	/**
	 * Create a new PathTile.
	 */
	public PathTile(){
		super(sprite);
	}
	
	@Override
	public String toString(){
		return "_";
	}
}
