package se.chalmers.tda367.std.tests;

import java.nio.file.Path;

import se.chalmers.tda367.std.utilities.NativeSprite;

/**
 * A dummy implementation of the NativeSprite for use in unit tests.
 * @author Emil Edholm
 * @date   May 15, 2012
 */
public final class NativeDummySprite implements NativeSprite {

	@Override
	public void create(Path pathToImage) { }

	@Override
	public void draw(float x, float y, float width, float heigth) { }

	@Override
	public void draw(float x, float y) { }

	@Override
	public void draw(float x, float y, float scale) { }

	@Override
	public int getHeight() { return 0; }

	@Override
	public int getWidth() { return 0; }
}
