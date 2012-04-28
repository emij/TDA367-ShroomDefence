package se.chalmers.tda367.std.utilities;

import java.nio.file.Path;

import com.google.inject.ImplementedBy;

import se.chalmers.tda367.std.gui.NativeSlickSprite;

/**
 * A interface that describes common methods for a sprite.
 * Each GUI needs to have a class that implements this interface to convert to
 * its native Sprite/image system. The implementing class should also be immutable.
 * @author Emil Edholm
 * @date   Apr 28, 2012
 */
@ImplementedBy(NativeSlickSprite.class)
public interface NativeSprite {
	
	/**
	 * Create and initialize the sprite from a path to a image/sprite. <br />
	 * This method <b>must</b> be called before any other since it they may throw {@code NullPointerException} otherwise
	 * @param pathToImage the path to the image.
	 */
	public void create(Path pathToImage);
	
	/**
	 * Draw the sprite at the specified position.
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @param width - draw with this width
	 * @param heigth - draw with this height
	 */
	public void draw(float x, float y, float width, float heigth);
	
	/**
	 * Draw the image at the specified position and actual size.
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 */
	public void draw(float x, float y);
	
	/**
	 * Draw the image with a given scale
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @param scale - the scale to apply.
	 */
	public void draw(float x, float y, float scale);
	
	/**
	 * Retrieves the height of the sprite.
	 * @return the height as an integer.
	 */
	public int getHeight();
	
	/**
	 * Retrieves the width of the sprite
	 * @return the width as an integer.
	 */
	public int getWidth();
}
