package se.chalmers.tda367.std.core;

import java.util.*;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.core.events.EnemyEnteredBaseEvent;
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
	private final EnemyList enemies;
	
	private final BoardPosition playerBasePos;
	
	private final int width;
	private final int height;
	
	public GameBoard(Map map) {
		EventBus.INSTANCE.register(this);
		
		this.width = map.getWidth();
		this.height = map.getHeight();
		this.board =  new IBoardTile[this.width][this.height];
		this.playerBasePos = map.getPlayerBasePos();
		
		// Create the EnemyList but convert the start position to the Enemy-coordinate system first.
		BoardPosition startPos = map.getEnemyStartPos();
		int scale = Properties.INSTANCE.getTileScale();
		float nX = startPos.getX() * scale;
		float nY = startPos.getY() * scale;
		this.enemies = new EnemyList(Position.valueOf(nX, nY), map.getWaypointList());
		
		// Populate the game board with data from the Map.
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				PlaceableTile item = map.getMapItem(x, y);
				board[x][y] = item.getInstance();
			}
		}

	}
	
	/**
	 * Returns a list of {@code Attackable} that is inside the radius of the supplied position
	 * @param center - the center position of the "circle" to check
	 * @param radius - the radius to check
	 * @return A list of the {@code Attackable} inside the "circle".
	 */
	public List<Attackable> getAttackables(Position center, int radius){
		List<IEnemy> inRadius = new ArrayList<IEnemy>();
		InRadiusFilter filter = new InRadiusFilter(center, radius);
		
		for(IEnemy enemy : enemies){
			if(filter.accept(enemy.getPosition())) {
				inRadius.add(enemy);
			}
		}
		Collections.sort(inRadius);
		
		// Convert to Attackable.
		List<Attackable> attackables = new ArrayList<Attackable>(inRadius.size());
		for(IEnemy enemy : inRadius) {
			attackables.add(enemy);
		}
		return attackables;
	}
	
	/**
	 * Retrieves the enemies that are currently on the game board.
	 * The actual logic behind removing and adding enemies are 
	 * @return a list of EnemyItems that are currently ON the game board.
	 */
	public EnemyList getEnemies() {
		return enemies;
	}
	
	public int getPlayerBaseHealth() {
		return getPlayerBase().getHealth();
	}
	
	private IPlayerBase getPlayerBase() {
		return (IPlayerBase) getTileAt(playerBasePos);
	}
	
	@Subscribe
	public void enemyEnteredBase(EnemyEnteredBaseEvent e) {
		getPlayerBase().decreaseHealth();
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
	 * Method to get the end/goal position on the game board.
	 * @return a position containing the coordinates of the end/goal position.
	 */
	public BoardPosition getEndPos() {
		return playerBasePos;
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
}
