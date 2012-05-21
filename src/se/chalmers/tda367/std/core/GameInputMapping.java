package se.chalmers.tda367.std.core;

import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.input.NiftyInputMapping;
import de.lessvoid.nifty.input.keyboard.KeyboardInputEvent;

/**
 * Custom inputmapping class for overriding the default input mapping of 
 * the space key. This solves nifty from activating elements when the player jumps.
 * @author Johan Gustafsson
 * @date   May 14, 2012
 */
public class GameInputMapping implements NiftyInputMapping {

	@Override
	public NiftyInputEvent convert(final KeyboardInputEvent inputEvent) {
		return null;
	}
}
