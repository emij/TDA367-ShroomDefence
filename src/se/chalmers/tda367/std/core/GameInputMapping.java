package se.chalmers.tda367.std.core;

import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.input.NiftyInputMapping;
import de.lessvoid.nifty.input.keyboard.KeyboardInputEvent;

/**
 * Custom inputmapping class for taking care of 
 * @author Johan Gustafsson
 * @date   May 14, 2012
 */
public class GameInputMapping implements NiftyInputMapping {

	@Override
	public NiftyInputEvent convert(final KeyboardInputEvent inputEvent) {
		if (inputEvent.getKey() == KeyboardInputEvent.KEY_SPACE) {
	        return null;
		}
		return null;
	}
}
