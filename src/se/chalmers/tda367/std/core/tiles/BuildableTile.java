package se.chalmers.tda367.std.core.tiles;

import se.chalmers.tda367.std.utilities.SpriteCreator;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Class for representing a buildable tile. 
 * @author Emil Johansson
 * @
 * @date Mar 26, 2012
 */
public class BuildableTile extends AbstractTile implements IBuildableTile {

	//TODO: change sprite for the buildable tile.
	private final static Sprite sprite = SpriteCreator.create("/images/gameplay/buildable_tile.png");
	
	/**
	 * Create a buildable tile.
	 */
	public BuildableTile() {
		super(sprite);
	}
	
	@Override
	public String toString(){
		return "O";
	}

}
