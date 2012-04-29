package se.chalmers.tda367.std.core;

import java.util.*;
import java.util.logging.Logger;

import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.core.maps.Map;
import se.chalmers.tda367.std.core.maps.MapItem;
import se.chalmers.tda367.std.core.tiles.*;
import se.chalmers.tda367.std.core.tiles.towers.ITower;
import se.chalmers.tda367.std.utilities.*;

/**
 * Represents the whole game board in a grid system.
 * @author Johan Gustafsson
 * @modified Emil Johansson, Emil Edholm (Apr 28, 2012)
 * @date Mar 22, 2012
 */
public class GameBoard {
	private IBoardTile[][] board;
	private List<EnemyItem> enemies;
	
	private BoardPosition enemyStartPos;
	private BoardPosition playerBasePos;
	
	private final int width;
	private final int height;
	private List<Position> waypoints;
	
	public GameBoard(Map map) {
		this.width = map.getWidth();
		this.height = map.getHeight();
		this.board =  new IBoardTile[this.width][this.height];
		this.enemyStartPos = map.getEnemyStartPos();
		this.playerBasePos = map.getPlayerBasePos();
		this.waypoints = new ArrayList<Position>();
		this.enemies = new ArrayList<EnemyItem>();
		
		// Populate the game board with data from the Map.
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				MapItem item = map.getMapItem(x, y);
				board[x][y] = item.getTile();
				if(item.isWaypoint()) {
					waypoints.add(item.getWaypointPosition());
				}
			}
		}
	}
	
	// TODO: Handle event that enemies has died.
	
	/**
	 * Returns a list of enemies that is inside the radius of the supplied position
	 * @param center the center position of the "circle" to check
	 * @param radius the radius to check
	 * @return A list of the enemies inside the "circle". Note that the order of the list is unsorted.
	 */
	public List<EnemyItem> getEnemiesInRadius(Position center, int radius){
		List<EnemyItem> inRadius = new ArrayList<EnemyItem>();
		InRadiusFilter filter = new InRadiusFilter(center, radius);
		
		for(EnemyItem ei : enemies){
			if(filter.accept(ei.getEnemyPos())) {
				inRadius.add(ei);
			}
		}
		return inRadius; // TODO: Sort by enemies closest to player base.
	}
	
	/**
	 * Retrieves the enemies that are currently on the game board.
	 * The actual logic behind removing and adding enemies are 
	 * @return a list of EnemyItems that are currently ON the game board. 
	 */
	public List<EnemyItem> getEnemies() {
		return enemies;
	}
	
	public IPlayerBase getPlayerBase() {
		return (IPlayerBase) getTileAt(playerBasePos);
	}

	/**
	 * Move the IBoardTile from the old position to the new position
	 * @param oldP
	 * @param newP
	 */
	public void moveTile(BoardPosition oldP, BoardPosition newP){
		IBoardTile newPosTile = getTileAt(newP);
		
		placeTile(getTileAt(oldP), newP);
		placeTile(newPosTile, oldP);
	}
	
	/**
	 * Place given tile on given position.
	 * @param tile
	 * @param p
	 */
	public void placeTile(IBoardTile tile, BoardPosition p){
		if(posOnBoard(p)) {
			board[p.getX()][p.getY()] = tile;
		} else {
			Logger.getLogger("se.chalmers.tda367.std.core").info(p + " is a bad coordinate");
		}
	}
	
	/**
	 * Returns the tile from given x and y values.
	 * @param x
	 * @param y
	 * @return IBoardTile from given x and y values.
	 */
	public IBoardTile getTileAt(int x, int y) {
		return board[x][y];
	}
	
	/**
	 * Returns the tile from given position.
	 * @param p
	 * @return IBoardTile from given position.
	 */
	public IBoardTile getTileAt(BoardPosition p){
		return getTileAt(p.getX(), p.getY());
	}
	
	/**
	 * Check if a given x and y values is inside the boundaries of the board.
	 * @param p
	 * @return true if given x and y values are on the game board.
	 */
	public boolean posOnBoard(int x, int y){
		if(x < 0 || y < 0) {
			return false;
		}
		if(x >= width || y >= height) {
			return false;
		}
		return true;
	}
	
	/**
	 * Check if a given position is inside the boundaries of the board.
	 * @param p
	 * @return true if position is on the game board.
	 */
	public boolean posOnBoard(BoardPosition p){
		return posOnBoard(p.getX(), p.getY());
	}
	
	
	/**
	 * Overrides toString
	 * @return a string representation of the game board.
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				str.append('[');
				str.append(board[x][y].toString());
				str.append(']');
			}
			str.append("\n");
		}
		return str.toString();
		
	}
	
	/**
	 * @return the width of the game board.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * @return the height of the game board.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Method to get the enemy's starting position on the game board.
	 * @return a position containing the coordinates of the enemy starting position.
	 */
	public BoardPosition getStartPos() {
		return enemyStartPos;
	}
	
	/**
	 * Method to get the end/goal position on the game board.
	 * @return a position containing the coordinates of the end/goal position.
	 */
	public BoardPosition getEndPos() {
		return playerBasePos;
	}
	
	/**
	 * TODO: Fix javadoc
	 */
	public List<Position> getWaypoints() { 
		return new ArrayList<Position>(waypoints);
	}
	
	/**
	 * Method for checking if a given position on the game board is buildable.
	 * @param p position to check if buildable.
	 * @return true if given position is buildable. Returns false if the position isn't buildable or on the game board.
	 */
	public boolean canBuildAt(BoardPosition p) {
		if(!posOnBoard(p)) {
			return false;
		}
		return getTileAt(p) instanceof IBuildableTile;
	}
	
	/**
	 * Retrieves all the towers currently on the game board.
	 * @return a list of towers currently placed on the game board.
	 */
	public List<ITower> getTowersOnBoard(){
		List<ITower> towers = new ArrayList<ITower>();
		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				if(getTileAt(x, y) instanceof ITower){
					towers.add((ITower) getTileAt(x, y));
				}
			}
		}
		
		return towers;
	}
	
	/**
	 * Method for checking if there is an enemy on a given position.
	 * @param p position to check for an enemy.
	 * @return true if an enemy is on the given position. Returns false if given position is outside the board or no enemy is at the position.
	 */
	public boolean isEnemyAt(BoardPosition p) {
		if(!posOnBoard(p)) {
			return false;
		}
		return getTileAt(p) instanceof IEnemy;
	}
	
	/**
	 * Class used for filtering enemies that are not in a specified radius from a given center position
	 * @author Emil Edholm
	 * @date   Apr 24, 2012
	 */
	private class InRadiusFilter implements Filter<Position> {
		private final Position centerPosition;
		private final int radius;
		
		public InRadiusFilter(Position centerPosition, int radius) {
			this.centerPosition = new Position(centerPosition);
			this.radius = radius;
		}
		
		
		/**
		 * Accepts the position if it is within the {@code radius} of {@code centerPosition}
		 */
		@Override
		public boolean accept(Position p) {
			Double distance = Position.calculateDistance(centerPosition, p);
			
			if(Double.compare(distance, radius) <= 0) {
				return true;
			}
			
			return false;
		}
		
	}
	
	/**
	 * Class used for representing positions on the game board.
	 * @author Emil Edholm
	 * @date   Apr 28, 2012
	 */
	public static class BoardPosition {
		private int x, y;
		private BoardPosition(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public static BoardPosition valueOf(int x, int y) {
			return new BoardPosition(x, y);
		}
		
		public static BoardPosition valueOf(BoardPosition bp) {
			return valueOf(bp.getX(), bp.getY());
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		/**
		 * Convert to a floating point based Position.
		 * @return a {@code Position} with the same coordinates as {@code this}
		 */
		public Position toPosition() {
			return Position.valueOf(x, y);
		}
	}
	
}
