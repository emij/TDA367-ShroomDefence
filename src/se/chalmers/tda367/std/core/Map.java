package se.chalmers.tda367.std.core;

import se.chalmers.tda367.std.utilities.Position;

/**
 * Map
 * @author Emil Johansson
 * @date Apr 16, 2012
 */
public class Map {
	// TODO Need to rewrite, and possible read in textfiles that
	// represents maps, maybe create abstract/interface map instead
	private int[][] mapArray;
	private Position startPos;
	private int testValue;
	private int width = Properties.INSTANCE.getDefaultBoardWidth();
	private int height = Properties.INSTANCE.getDefaultBoardHeight();
	/**
	 * Default constructor for creating a Map
	 */
	public Map(){
		startPos = new Position(0,12);
		mapArray = new int[width][height];
		for(int i = 0; i <= 13; i++){
			testValue = i;
			mapArray[i][12] = 1 + testValue;
			mapArray[i][13] = 1 + testValue;
		}
		testValue++;
		for(int i = 2; i <= 13; i++){
			testValue = testValue + i;
			mapArray[i][6] = 1 + testValue;
			mapArray[i][7] = 1 + testValue;
		}
		testValue++;
		for(int i = 2; i <= 19; i++){
			testValue = testValue + i;
			mapArray[i][17] = 1 + testValue;
			mapArray[i][18] = 1 + testValue;
		}
		testValue++;
		for(int i = 6; i <= 18; i++){
			testValue = testValue + i;
			mapArray[2][i] = 1 + testValue;
			mapArray[3][i] = 1 + testValue;
		}
		testValue++;
		for(int i = 6; i <= 13; i++){
			testValue = testValue + i;
			mapArray[12][i] = 1 + testValue;
			mapArray[13][i] = 1 + testValue;
		}
	}
	
	public int[][] getMap(){
		System.out.println(mapArray);
		return mapArray;
	}
	public int getValueAtPos(Position pos){
		int x = pos.getX();
		int y = pos.getY();
		return mapArray[x][y];
	}
	// Properties.INSTANCE.getDefaultBoardWidth()
	// Properties.INSTANCE.getDefaultBoardHeight()

}
