package se.chalmers.tda367.std.utilities;

import java.io.*;
import java.util.*;

/**
 * A class for loading maps
 * @author Emil Johansson
 * @date Apr 22, 2012
 */
public class MapLoader {
	private static int[][] map;
	private static File file;
	
	public static int[][] loadMap(int level){
		file = new File("maps/level" + level + ".txt");
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
		return map;
	  }
	
	
	private static void processLine(String line){ 
	    Scanner scanner = new Scanner(line);
	    scanner.useDelimiter(":");
	    while ( scanner.hasNext() ){
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
	    } 
	}
	
}
