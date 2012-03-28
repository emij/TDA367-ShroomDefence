package se.chalmers.tda367.std.utilities;

import java.nio.file.Path;

/**
 * A utilities class with some helper methods.
 * @author Unchanged
 * @date Mar 22, 2012
 */
public enum Utilities {
	; // Prevents instantiation.
	/**
	 * Used to save a supplied class to disk. I.e. serialize it.
	 * Saves it to the specified path.
	 */
	public static <T> void saveObject(T data, Path p){
		
	}
	
	/**
	 * Tries to deserialize the object from the specified path.
	 */
	public static <T> T loadObject(Path p){
		return null;
	}
	
	
	/**
	 * Takes a path and returns the sprite saved on that location.
	 */
	public static Sprite loadSprite(Path p){
		return null;
	}
	
}
