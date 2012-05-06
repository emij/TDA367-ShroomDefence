package se.chalmers.tda367.std.gui;

import java.nio.file.Path;
import java.util.logging.Logger;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.std.utilities.NativeSprite;

/**
 * Represents a Slick {@code Image} represented as a {@code NativeSprite}.
 * @see org.newdawn.slick.Image
 * @author Emil Edholm
 * @date   Apr 28, 2012
 */
public final class NativeSlickSprite implements NativeSprite {
	private Image slickImage = null;
	
	@Override
	public void create(Path pathToImage) {
		if(slickImage == null){
			try {
				slickImage = new Image(pathToImage.toString());
			} catch (SlickException e) {
				e.printStackTrace();
				Logger.getLogger("se.chalmers.tda367.std.gui").severe("Unable to create Slick Image: " + pathToImage);
			}
		}
	}

	@Override
	public void draw(float x, float y, float width, float height) {
		slickImage.draw(x, y, width, height);
	}

	@Override
	public void draw(float x, float y) {
		slickImage.draw(x, y);
	}

	@Override
	public void draw(float x, float y, float scale) {
		slickImage.draw(x, y, scale);
	}

	@Override
	public int getHeight() {
		return slickImage.getHeight();
	}

	@Override
	public int getWidth() {
		return slickImage.getWidth();
	}

}
