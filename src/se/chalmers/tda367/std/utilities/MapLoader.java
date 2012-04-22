package se.chalmers.tda367.std.utilities;

import java.io.*;
import java.util.*;

/**
 * A class for loading maps
 * @author Emil Johansson
 * @date Apr 22, 2012
 */
public class MapLoader {
	private int[][] map;
	private File file;
	
	public MapLoader() throws FileNotFoundException{
		file = new File("/maps/level1.txt");
	    Scanner scanner = new Scanner(new FileReader(file));
	    try {
	      while ( scanner.hasNextLine() ){
	        processLine( scanner.nextLine() );
	      }
	    }
	    finally {
	      scanner.close();
	    }
	  }
	
	
	private void processLine(String line){ 
	    Scanner scanner = new Scanner(line);
	    scanner.useDelimiter(":");
	    if ( scanner.hasNext() ){
	    	String tileData[] = scanner.next().split(",");
	    	String tileType = tileData[0];
	    	int xCord = Integer.parseInt(tileData[1]);
	    	int yCord = Integer.parseInt(tileData[2]); 
	    	if(tileType.equals("S")){
	    		map = new int[xCord][yCord];
	    	} else if(tileType.equals("P")){
	    		map[xCord][yCord] = 1;
	    	} else if(tileType.equals("W")){
	    		map[xCord][yCord] = 2;
	    	}
	    } else {
	      System.out.println("Empty or invalid line. Unable to process.");
	    }
	}
	
	public int[][] getMap(){
		return map;
	}
	
}
