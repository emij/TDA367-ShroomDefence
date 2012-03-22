package se.chalmers.tda367.std.core;

import java.util.List;

import se.chalmers.tda367.std.utilities.Position;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Represents the whole game board in a grid system.
 * @author Unchanged
 * @date Mar 22, 2012
 */
public class GameBoard {
	
	private IBoardTile[][] board;
	
	public GameBoard(){
		
	}
	public GameBoard(int height, int width){
		
	}
	/**
	 * Returns a list of enemies that is inside the radius based on supplied position
	 */
	public List<IEnemy> getEnemiesInRadius(Position pos, int radius){
		throw new NotImplementedException();
	}
	/**
	 * Move the IBoardTile from the old position to the new position
	 */
	public void moveTile(Position oldP, Position newP){
		
	}
	/**
	 * Place a supplied IBoardTile on given position
	 */
	public void placeTile(IBoardTile tile, Position pos){
		
	}
	/**
	 * Retrieve the tile at the supplied position.
	 */
	public IBoardTile getTileAt(Position p){
		return null;
	}
}
