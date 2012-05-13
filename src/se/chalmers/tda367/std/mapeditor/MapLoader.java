package se.chalmers.tda367.std.mapeditor;

import java.io.*;
import se.chalmers.tda367.std.utilities.IO;

/**
 * A class for loading maps
 * @author Emil Johansson
 * @modified Emil Edholm (May 12, 2012)
 * @date Apr 22, 2012
 */
public class MapLoader {
	private LevelMap map;
	
	public MapLoader(int level) {
		this(new File("maps/level" + level + ".map"));
	}
	
	public MapLoader(File mapFile) {
		map = loadMap(mapFile);
	}
	
	private LevelMap loadMap(File f) {
		LevelMap map = null;
		try {
			map = IO.loadObject(LevelMap.class, f);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		return map;
	}

	/*private void loadMap(File file){
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
	    				map.setMapItem(x, y, PlaceableTile.TERRAIN_TILE);
	    			}
	    		}
	    	} else if(tileType.equals("E")) { // E as in Enemy insert position
	    		map.setMapItem(xCord, yCord, PlaceableTile.ENEMY_START_TILE);
	    		
	    	} else if(tileType.equals("B")) { // B as in Player base position.
	    		map.setMapItem(xCord, yCord, PlaceableTile.PLAYER_BASE_TILE);
	    		
	    	} else if(tileType.equals("P")) { // P as in PathTile
	    		map.setMapItem(xCord, yCord, PlaceableTile.PATH_TILE);
	    		
	    	} else if(tileType.equals("W")) { // W as in WayPoint.
	    		map.setMapItem(xCord, yCord, PlaceableTile.WAYPOINT);
	    		
	    	} else if(tileType.equals("T")) { // T as in Buildable Tower.
	    		map.setMapItem(xCord, yCord, PlaceableTile.BUILDABLE_TILE);
	    	}
	    } 
	}*/
	
	/**
	 * Get the map that was loaded by the {@code MapLoader}
	 * @return the loaded map, or null if for some reason the {@code MapLoader} was unable to load the {@code Map}.
	 */
	public Map getLoadedMap() {
		return map;
	}
}
