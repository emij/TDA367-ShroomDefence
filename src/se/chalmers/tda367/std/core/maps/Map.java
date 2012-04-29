package se.chalmers.tda367.std.core.maps;

import se.chalmers.tda367.std.core.GameBoard;

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
	
	/**
	 * What level the Map are supposed to represent.
	 * @return a integer represents a map level ranging from 1 to Integer.MAX_VALUE
	 */
	public int getLevel();
	
	/**
	 * The Map field that contains the actual map content, such as tile and waypoint placement.
	 * @return a MapItem array with length {@code getWidth()} and height {@code getHeight()}.
	 */
	public MapItem[][] getMapField();
	
	/**
	 * Get the stored {@code MapItem} at the supplied position.
	 * @param x - the x coordinate of the requested {@code MapItem}
	 * @param y - the y coordinate of the requested {@code MapItem}
	 * @throws IndexOutOfBoundsException - if x and/or y are outside the board field.
	 * @return the {@code MapItem} at the x, y position.
	 */
	public MapItem getMapItem(int x, int y);
}
