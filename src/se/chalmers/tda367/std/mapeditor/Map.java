package se.chalmers.tda367.std.mapeditor;

import java.util.List;

import se.chalmers.tda367.std.core.GameBoard.BoardPosition;
import se.chalmers.tda367.std.utilities.Position;

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
	public BoardPosition getEnemyStartPos();
	
	/**
	 * The position of the player base. This is the position all enemies walk towards.
	 */
	public BoardPosition getPlayerBasePos();

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
	 * The Map field that contains the actual map content.
	 * @return a {@code PlaceableTile} array with length {@code getWidth()} and height {@code getHeight()}.
	 */
	public PlaceableTile[][] getMapField();
	
	/**
	 * Get the stored {@code MapItem} at the supplied position.
	 * @param x - the x coordinate of the requested {@code MapItem}
	 * @param y - the y coordinate of the requested {@code MapItem}
	 * @throws IndexOutOfBoundsException - if x and/or y are outside the board field.
	 * @return the {@code PlaceableTile} at the x, y position.
	 */
	public PlaceableTile getMapItem(int x, int y);
	
	/**
	 * Returns the list of waypoints associated with the {@code Map}
	 * @return a list of waypoint positions.
	 */
	public List<Position> getWaypointList();
}
