package se.chalmers.tda367.std.core.tiles;

import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Class for representing a buildable tile. 
 * @author Emil Johansson
 * @
 * @date Mar 26, 2012
 */
public class BuildableTile extends AbstractTile implements IBuildableTile {

	/**
	 * Create a buildable tile.
	 */
	public BuildableTile() {
		super(new Sprite("/images/gameplay/buildable_tile.png"));
	}
	
	@Override
	public String toString(){
		return "O";
	}

}
