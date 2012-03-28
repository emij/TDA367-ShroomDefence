package se.chalmers.tda367.std.core.tiles;

import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Class for representing a buildable tile. 
 * @author Emil Johansson
 * @date Mar 26, 2012
 */
public class BuildableTile extends AbstractTile implements IBuildableTile {

	/**
	 * @param sprite
	 */
	public BuildableTile(Sprite sprite) {
		super(sprite);
	}
	
	@Override
	public String toString(){
		return "O";
	}

}
