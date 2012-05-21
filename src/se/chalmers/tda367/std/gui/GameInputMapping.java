package se.chalmers.tda367.std.gui;

import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.input.NiftyInputMapping;
import de.lessvoid.nifty.input.keyboard.KeyboardInputEvent;

/**
 * Custom input mapping class for overriding the default nifty input mapping. 
 * This solves nifty from activating elements when the player jumps(presses space).
 * The class is referenced and used in the gui XML-files.
 * @author Johan Gustafsson
 * @date   May 14, 2012
 */
public class GameInputMapping implements NiftyInputMapping {

	@Override
	public NiftyInputEvent convert(final KeyboardInputEvent inputEvent) {
		return null;
	}
}
