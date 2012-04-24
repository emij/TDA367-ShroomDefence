package se.chalmers.tda367.std.utilities;

import java.awt.Image;

/**
 * Represents a Sprite. i.e. a moving (or static) image of sorts.
 * @author Emil Edholm
 * @date Apr 24, 2012
 */
public class Sprite {
	public final Image image;
	
	/**
	 * Create an empty sprite with no associated image.
	 */
	public Sprite(){
		image = null;
	}
	
	/**
	 * Create a sprite with the image loaded from a resource string.
	 * @param resourceString the resource string from which to load the image.
	 */
	public Sprite(String resourceString){
		this();
		// TODO: Load image from resources. Possible use of a resource loader.
	}
	
	/**
	 * Create a sprite from the supplied image.
	 * @param image the image to base the sprite on.
	 */
	public Sprite(Image image){
		this.image = image;
	}
	
	/**
	 * Retrieves the image associated with this sprite.
	 * @return an image representing the sprite.
	 */
	public Image getImage() {
		return image;
	}
}
