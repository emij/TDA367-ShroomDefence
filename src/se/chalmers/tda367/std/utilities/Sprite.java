package se.chalmers.tda367.std.utilities;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 * Represents a Sprite. i.e. a moving (or static) image of sorts.
 * Used mostly as a in-between class and the GUI will have to convert this to a usable image.
 * @author Emil Edholm
 * @date Apr 24, 2012
 */
public class Sprite {
	public final Path imagePath;
	
	/**
	 * Create a sprite with the image loaded from a resource string.
	 * @param resourceString the resource string from which to load the image.
	 */
	public Sprite(String resourceString){
		String s = getClass().getResource(resourceString).getPath();
		if(s != null) {
			imagePath = Paths.get(s.substring(1)); // Must for some reason remove a '/' at the beginning.
		}
		else {
			Logger.getLogger("se.chalmers.tda367.std.utilities").severe("Unable to find the resource: " + resourceString);
			imagePath = null;
		}
	}
	
	/**
	 * Retrieves the path associated with this sprite.
	 * @return a path to the image contained within this sprite.
	 */
	public Path getImagePath() {
		return imagePath;
	}
}
