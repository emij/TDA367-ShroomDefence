package se.chalmers.tda367.std.utilities;

import java.nio.file.Path;

/**
 * A IO class for use when (de)serializing objects.
 * @author Emil Edholm
 * @date May 12, 2012
 */
public enum IO {
	; // Prevents instantiation.
	/**
	 * Used to save a supplied class to disk. I.e. serialize it.
	 * Saves it to the specified path.
	 */
	public static <T> void saveObject(T data, Path p){
		// TODO: Implement the saveObject.
	}
	
	/**
	 * Tries to deserialize the object from the specified path.
	 */
	public static <T> T loadObject(Class<T> expectedType, Path p){
		// TODO: Implement the loadObject.
		return null;
	}
}
