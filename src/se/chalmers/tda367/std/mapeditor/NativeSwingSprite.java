package se.chalmers.tda367.std.mapeditor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import se.chalmers.tda367.std.utilities.NativeSprite;

/**
 * The native implementation of a Sprite for use with Swing.
 * @author Emil Edholm
 * @date   May 9, 2012
 */
public final class NativeSwingSprite implements NativeSprite {

	private BufferedImage image;
	private Graphics2D graphics;
	
	@Override
	public void create(Path pathToImage) {
		try {
			image = ImageIO.read(pathToImage.toFile());
			graphics = image.createGraphics();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(float x, float y, float width, float heigth) {
		int iX = Math.round(x);
		int iY = Math.round(y);
		int iW = Math.round(width);
		int iH = Math.round(heigth);
		
		graphics.drawImage(image, iX, iY, iW, iH, null);
	}

	@Override
	public void draw(float x, float y) {
		int iX = Math.round(x);
		int iY = Math.round(y);
		
		graphics.drawImage(image, iX, iY, null);
	}

	@Override
	public void draw(float x, float y, float scale) {
		int iX = Math.round(x);
		int iY = Math.round(y);
		int iW = Math.round(getWidth() * scale);
		int iH = Math.round(getHeight() * scale);
		
		graphics.drawImage(image, iX, iY, iW, iH, null);
	}

	@Override
	public int getHeight() {
		return image.getHeight();
	}

	@Override
	public int getWidth() {
		return image.getWidth();
	}

}
