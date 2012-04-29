package se.chalmers.tda367.std.core.maps;

import se.chalmers.tda367.std.core.GameBoard;

/**
 * Represents a Map that can be set and changed.
 * Useful for building maps with the MapEditor etc.
 * @author Emil Johansson
 * @modified Emil Edholm (Apr 29, 2012)
 * @date Apr 16, 2012
 */
public class LevelMap implements Map {
	private MapItem[][] mapArray;
	private GameBoard.BoardPosition enemyStartPos;
	private GameBoard.BoardPosition playerBasePos; // End position.
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
		mapArray = new MapItem[width][height];
	}
	
	@Override
	public GameBoard.BoardPosition getEnemyStartPos() {
		return this.enemyStartPos;
	}
	
	public void setPlayerEnemyStartPos(GameBoard.BoardPosition pos) {
		this.enemyStartPos = pos;
	}
	
	@Override
	public GameBoard.BoardPosition getPlayerBasePos() {
		return this.playerBasePos;
	}
	
	public void setPlayerBasePos(GameBoard.BoardPosition pos) {
		this.playerBasePos = pos;
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
	public MapItem[][] getMapField() {
		return mapArray;
	}
	
	public void setMapItem(int x, int y, MapItem mapItem) {
		checkBounds(x, y);
		mapArray[x][y] = mapItem;
	}
	
	@Override
	public MapItem getMapItem(int x, int y) {
		checkBounds(x, y);
		return mapArray[x][y];
	}
	
	private void checkBounds(int x, int y) {
		if(x >= width || y >= height || x < 0 || y < 0) {
			throw new IndexOutOfBoundsException("x and/or y value illegal.\nX: " + x + ", Y: " + y);
		}
	}
}
