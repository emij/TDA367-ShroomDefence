package se.chalmers.tda367.std.core.tiles;

import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Represents a general game board tile. 
 * Essentially anything that is placeable on the game board.
 * @author Unchanged
 * @date Mar 22, 2012
 */
public interface IBoardTile {
	/**
	 * Return the graphical representation of the IBoardTile
	 * @return
	 */
	public Sprite getSprite();
	

}
