package se.chalmers.tda367.std.core;

import se.chalmers.tda367.std.core.tiles.IBoardTile;
import se.chalmers.tda367.std.utilities.Position;

/**
 * Map
 * @author Emil Johansson
 * @date Apr 16, 2012
 */
public class Level1Map implements Map {
	private int[][] mapArray;
	private GameBoard.BoardPosition enemyStartPos;
	private GameBoard.BoardPosition playerBasePos; // End position.
	private int boardValue;
	private int width = Properties.INSTANCE.getDefaultBoardWidth();
	private int height = Properties.INSTANCE.getDefaultBoardHeight();
	/**
	 * Default constructor for creating a Map
	 */
	public Level1Map(){
		enemyStartPos = GameBoard.BoardPosition.valueOf(1,12);
		playerBasePos = GameBoard.BoardPosition.valueOf(24,16);
		mapArray = new int[width][height];
		for(int i = 0; i <= 13; i++){
			boardValue = i;
			mapArray[i][12] = 1 + boardValue;
			mapArray[i][13] = 1 + boardValue;
		} 
		for(int i = 12; i >= 6; i--){
			boardValue++;
			mapArray[12][i] = 1 + boardValue;
			mapArray[13][i] = 1 + boardValue;
		}
		for(int i = 12; i >= 2; i--){
			boardValue++;
			mapArray[i][6] = 1 + boardValue;
			mapArray[i][7] = 1 + boardValue;
		}
		for(int i = 7; i <= 18; i++){
			boardValue++;
			mapArray[2][i] = 1 + boardValue;
			mapArray[3][i] = 1 + boardValue;
			mapArray[2][12] = 3;
			mapArray[2][13] = 3;
			mapArray[3][12] = 4;
			mapArray[3][13] = 4;
		}
		for(int i = 3; i <= 19; i++){
			boardValue++;
			mapArray[i][17] = 1 + boardValue;
			mapArray[i][18] = 1 + boardValue;
		}
		mapArray[2][12] = 38;
		mapArray[3][13] = 39;
		mapArray[3][7] = 0;
		mapArray[12][7] = 0;
		mapArray[12][12] = 0;
		mapArray[3][17] = 0;
		
	}
	
	public int[][] getMap(){
		return mapArray;
	}
	
	public int getBoardValueAtPos(GameBoard.BoardPosition pos){
		int x = pos.getX();
		int y = pos.getY();
		return mapArray[x][y];
	}
	
	@Override
	public GameBoard.BoardPosition getEnemyStartPos() {
		return this.enemyStartPos;
	}
	
	@Override
	public GameBoard.BoardPosition getPlayerBasePos() {
		return this.playerBasePos;
	}
	
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
	
	private class MapItem {
		private final IBoardTile tile;
		
		public MapItem(IBoardTile tile) {
			this.tile = tile;
		}
	}

	
}
