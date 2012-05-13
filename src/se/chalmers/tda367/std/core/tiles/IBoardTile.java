package se.chalmers.tda367.std.core.tiles;

import com.google.inject.ImplementedBy;

import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Represents a general game board tile. 
 * Essentially anything that is placeable on the game board.
 * @author Emil Edholm
 * @date Mar 22, 2012
 * @modified Emil Edholm (12 mar, 2012)
 */
@ImplementedBy(TerrainTile.class) // Should default to the the TerrainTile.
public interface IBoardTile {
	/**
	 * Return the graphical representation of the IBoardTile
	 * @return a sprite representing the board tile.
	 */
	public Sprite getSprite();
}
