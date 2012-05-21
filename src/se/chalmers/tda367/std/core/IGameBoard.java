package se.chalmers.tda367.std.core;

import java.util.List;

import se.chalmers.tda367.std.core.tiles.IBoardTile;
import se.chalmers.tda367.std.utilities.BoardPosition;
import se.chalmers.tda367.std.utilities.Position;

/**
 * Represents the game board. 
 * Contains all info about the each map such as buildability, towers and enemies etc.
 * @author Emil Edholm
 * @date   May 21, 2012
 */
public interface IGameBoard {

	/**
	 * Returns a list of {@code Attackable} that is inside the radius of the supplied position
	 * @param center - the center position of the "circle" to check
	 * @param radius - the radius to check
	 * @return A list of the {@code Attackable} inside the "circle".
	 */
	public abstract List<Attackable> getAttackables(Position center, int radius);

	/**
	 * Retrieves the enemies that are currently on the game board.
	 * The actual logic behind removing and adding enemies are 
	 * @return a list of EnemyItems that are currently ON the game board.
	 */
	public abstract EnemyList getEnemies();

	/** Returns the health of the IPlayerBase */
	public abstract int getPlayerBaseHealth();

	/**
	 * Place given tile on given position.
	 * @param tile - the tile to place
	 * @param p - the position to place the tile at.
	 */
	public abstract void placeTile(IBoardTile tile, BoardPosition p);

	/**
	 * Returns the tile from given x and y values.
	 * @param x - the x coordinate
	 * @param y - the y cooridnate
	 * @return The tile at given position.
	 */
	public abstract IBoardTile getTileAt(int x, int y);

	/**
	 * Returns the tile from given position.
	 * @param p - the position to get.
	 * @return IBoardTile from given position.
	 */
	public abstract IBoardTile getTileAt(BoardPosition p);

	/**
	 * Check if a given x and y values is inside the boundaries of the board.
	 * @param p
	 * @return true if given x and y values are on the game board.
	 */
	public abstract boolean posOnBoard(int x, int y);

	/** Check if a given position is inside the boundaries of the board. */
	public abstract boolean posOnBoard(BoardPosition p);

	/**
	 * @return the width of the game board.
	 */
	public abstract int getWidth();

	/**
	 * @return the height of the game board.
	 */
	public abstract int getHeight();

	/**
	 * Method to get the end/goal position on the game board.
	 * @return a position containing the coordinates of the end/goal position.
	 */
	public abstract BoardPosition getEndPos();

}