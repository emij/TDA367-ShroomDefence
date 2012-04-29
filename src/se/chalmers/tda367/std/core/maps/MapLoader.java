package se.chalmers.tda367.std.core.maps;

import java.io.*;
import java.util.*;

import se.chalmers.tda367.std.core.GameBoard.BoardPosition;
import se.chalmers.tda367.std.core.Properties;
import se.chalmers.tda367.std.core.tiles.*;
import se.chalmers.tda367.std.utilities.Position;

/**
 * A class for loading maps
 * @author Emil Johansson
 * @modified Emil Edholm (Apr 29, 2012)
 * @date Apr 22, 2012
 */
public class MapLoader {
	private final int level;
	private LevelMap map;
	public MapLoader(int level) {
		this.level = level;
		loadMap(new File("maps/level" + level + ".txt"));
	}

	private void loadMap(File file){
		Scanner scanner;
		try {
			scanner = new Scanner(new FileReader(file));
			while ( scanner.hasNextLine() ){
		        processLine( scanner.nextLine() );
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	  }
	
	
	private void processLine(String line){ 
	    Scanner scanner = new Scanner(line);
	    scanner.useDelimiter(":");
	    
	    int xCord, yCord;
	    String[] tileData;
	    String tileType;
	    while ( scanner.hasNext() ){
	    	tileData = scanner.next().split(",");
	    	tileType = tileData[0];
	    	xCord = Integer.parseInt(tileData[1]);
	    	yCord = Integer.parseInt(tileData[2]);
	    	
	    	if(tileType.equals("S")){ // S = Size.
	    		map = new LevelMap(level, xCord, yCord);

	    		// Default to TerrainTile.
	    		for(int y = 0; y < yCord; y++){
	    			for(int x = 0; x < xCord; x++){
	    				map.setMapItem(x, y, MapItem.createTerrainMapItem());
	    			}
	    		}
	    	} else if(tileType.equals("E")) { // E as in Enemy insert position
	    		map.setPlayerEnemyStartPos(BoardPosition.valueOf(xCord, yCord));
	    	} else if(tileType.equals("B")) { // B as in Player base position.
	    		map.setPlayerBasePos(BoardPosition.valueOf(xCord, yCord));
	    		map.setMapItem(xCord, yCord, new MapItem(new PlayerBase(3), calcWaypointPos(xCord, yCord)));
	    	} else if(tileType.equals("P")) { // P as in PathTile
	    		map.setMapItem(xCord, yCord, MapItem.createPathMapItem());
	    	} else if(tileType.equals("W")) { // W as in WayPoint.
	    		map.setMapItem(xCord, yCord, new MapItem(MapItem.PATH_TILE, calcWaypointPos(xCord, yCord)));
	    	} else if(tileType.equals("T")) { // T as in Buildable Tower.
	    		map.setMapItem(xCord, yCord, MapItem.createBuildableMapItem());
	    	}
	    } 
	}
	
	private Position calcWaypointPos(int x, int y) {
		return Position.valueOf(x * Properties.INSTANCE.getTileScale(), 
				   				y * Properties.INSTANCE.getTileScale());
	}
	
	/**
	 * Get the map that was loaded by the {@code MapLoader}
	 * @return the loaded map, or null if for some reason the {@code MapLoader} was unable to load the {@code Map}.
	 */
	public Map getLoadedMap() {
		return map;
	}
}
