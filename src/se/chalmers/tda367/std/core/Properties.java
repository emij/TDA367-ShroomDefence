package se.chalmers.tda367.std.core;

import java.nio.file.Path;

/**
 * Supplies some constants/properties.
 * @author Unchanged
 * @date Mar 22, 2012
 */
public enum Properties {
	INSTANCE; // Prevents instantiation
	
	public Path getDataFolder(){
		return null;
	}
	// Some needed constants goes here
	
	public int getDefaultBoardWidth(){
		return 20;
	}
	
	public int getDefaultBoardHeight(){
		return 20;
	}

}