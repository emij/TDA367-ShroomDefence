package se.chalmers.tda367.std.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import se.chalmers.tda367.std.utilities.BoardPosition;
import se.chalmers.tda367.std.utilities.Position;

/**
 * Represents a Map that can be set and changed.
 * Useful for building maps with the MapEditor etc.
 * @author Emil Johansson
 * @modified Emil Edholm (May 12, 2012)
 * @date Apr 16, 2012
 */
public class LevelMap implements Map, Serializable {
	private static final long serialVersionUID = -7348783894640743904L;
	
	private PlaceableTile[][] mapArray;
	private transient BoardPosition enemyStartPos;
	private transient BoardPosition playerBasePos; // End position.
	private final List<Position> waypointsList;
	private final int level;
	private final int width;
	private final int height;
	
	/**
	 * Create a level map with specified level and size.
	 * @param level - what level the map represents.
	 * @param width - the width of the map.
	 * @param height - the height of the map.
	 */
	public LevelMap(int level, int width, int height){
		this.width = width;
		this.height = height;
		this.level = level;
		mapArray = new PlaceableTile[width][height];
		waypointsList = new ArrayList<Position>();
	}
	
	@Override
	public BoardPosition getEnemyStartPos() {
		return this.enemyStartPos;
	}
	
	private void setPlayerEnemyStartPos(int x, int y) {
		this.enemyStartPos = BoardPosition.valueOf(x, y);
	}
	
	@Override
	public BoardPosition getPlayerBasePos() {
		return this.playerBasePos;
	}
	
	private void setPlayerBasePos(int x, int y) {
		this.playerBasePos = BoardPosition.valueOf(x, y);
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
	public int getLevel() {
		return level;
	}

	@Override
	public PlaceableTile[][] getMapField() {
		return mapArray;
	}
	
	public void setMapItem(int x, int y, PlaceableTile pTile) {
		checkBounds(x, y);
		mapArray[x][y] = pTile;
		
		// Handle the fringe cases.
		if(pTile == PlaceableTile.PLAYER_BASE_TILE) {
			setPlayerBasePos(x, y);
			waypointsList.add(calculateWaypoint(x, y));
			
		} else if(pTile == PlaceableTile.ENEMY_START_TILE) {
			setPlayerEnemyStartPos(x, y);
			
		} else if(pTile == PlaceableTile.WAYPOINT) {
			waypointsList.add(calculateWaypoint(x, y));
		}
	}
	
	@Override
	public PlaceableTile getMapItem(int x, int y) {
		checkBounds(x, y);
		return mapArray[x][y];
	}
	
	private void checkBounds(int x, int y) {
		if(x >= width || y >= height || x < 0 || y < 0) {
			throw new IndexOutOfBoundsException("x and/or y value illegal.\nX: " + x + ", Y: " + y);
		}
	}

	@Override
	public List<Position> getWaypointList() {
		return new ArrayList<Position>(waypointsList); // Note: defensive copy but Position is mutable.
	}
	
	/** Used to clear waypoints after loading an existing map. This is to avoid problems with the waypoint ordering */
	public void clearWaypoints() {
		waypointsList.clear();
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				if(mapArray[x][y] == PlaceableTile.WAYPOINT || 
						mapArray[x][y] == PlaceableTile.ENEMY_START_TILE ||
						mapArray[x][y] == PlaceableTile.PLAYER_BASE_TILE) {
					mapArray[x][y] = PlaceableTile.PATH_TILE;
				}
			}
		}
	}
	
	/** The x,y coordinate should be in a {@code BoardPosition} format. */
	private Position calculateWaypoint(int x, int y) {
		int scale = Properties.INSTANCE.getTileScale();
		float nX = x * scale;
		float nY = y * scale;
		
		return Position.valueOf(nX, nY);
	}
	
	/**
	 * Serialize this {@code LevelMap}.
	 * @serialData the x,y values for the player base and enemy start positions are read
	 * 			   separately. The player base position is written first, followed by enemy start position.
	 */
	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();
		s.writeInt(playerBasePos.getX());
		s.writeInt(playerBasePos.getY());
		
		s.writeInt(enemyStartPos.getX());
		s.writeInt(enemyStartPos.getY());
	}
	
	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		s.defaultReadObject();
		int x = s.readInt();
		int y = s.readInt();
		playerBasePos = BoardPosition.valueOf(x, y);
		
		x = s.readInt();
		y = s.readInt();
		enemyStartPos = BoardPosition.valueOf(x, y);
	}
}
