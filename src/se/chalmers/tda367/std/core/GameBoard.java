package se.chalmers.tda367.std.core;

import java.util.*;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.core.events.EnemyEnteredBaseEvent;
import se.chalmers.tda367.std.core.tiles.*;
import se.chalmers.tda367.std.utilities.*;

/**
 * Represents the whole game board in a grid system.
 * @author Johan Gustafsson
 * @modified Emil Johansson, Emil Edholm (Apr 28, 2012)
 * @date Mar 22, 2012
 */
public class GameBoard implements IGameBoard {
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
	
	@Override
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
	
	@Override
	public EnemyList getEnemies() {
		return enemies;
	}
	
	@Override
	public int getPlayerBaseHealth() {
		return getPlayerBase().getHealth();
	}
	
	/** Returns the IPlayerBase located on the board  */
	private IPlayerBase getPlayerBase() {
		return (IPlayerBase) getTileAt(playerBasePos);
	}
	
	@Subscribe
	public void enemyEnteredBase(EnemyEnteredBaseEvent e) {
		getPlayerBase().decreaseHealth();
	}
	
	@Override
	public void placeTile(IBoardTile tile, BoardPosition p){
		if(posOnBoard(p)) {
			board[p.getX()][p.getY()] = tile;
		} else {
			Logger.getLogger("se.chalmers.tda367.std.core").info(p + " is a bad coordinate");
		}
	}
	
	@Override
	public IBoardTile getTileAt(int x, int y) {
		if(!posOnBoard(x, y))
			throw new IllegalArgumentException("Invalid x/y. x = " + x + ", y = " + y);
		return board[x][y];
	}
	
	@Override
	public IBoardTile getTileAt(BoardPosition p){
		return getTileAt(p.getX(), p.getY());
	}
	
	@Override
	public boolean posOnBoard(int x, int y){
		if(x < 0 || y < 0) {
			return false;
		}
		if(x >= width || y >= height) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean posOnBoard(BoardPosition p){
		return posOnBoard(p.getX(), p.getY());
	}
	
	
	@Override
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

	@Override
	public int getWidth() {
		return width;
	}
	
	@Override
	public int getHeight() {
		return height;
	}
	
	@Override
	public BoardPosition getEndPos() {
		return playerBasePos;
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
