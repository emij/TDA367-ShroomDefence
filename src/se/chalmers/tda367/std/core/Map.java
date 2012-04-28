package se.chalmers.tda367.std.core;

/**
 * Represents a map that is used to crate the game board.
 * Used to create the game board and is used by the map editor.
 * @author Emil Edholm
 * @date   Apr 28, 2012
 */
public interface Map {
	
	/**
	 * The enemy start position. The position where the enemies are to be inserted into the game board/map.
	 */
	public GameBoard.BoardPosition getEnemyStartPos();
	
	/**
	 * The position of the player base. This is the position all enemies walk towards.
	 */
	public GameBoard.BoardPosition getPlayerBasePos();

	/** The width of the map. */
	public int getWidth();
	
	/** The height of the map. */
	public int getHeight();
}
