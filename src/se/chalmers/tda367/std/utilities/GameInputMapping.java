package se.chalmers.tda367.std.utilities;

import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.input.NiftyInputMapping;
import de.lessvoid.nifty.input.keyboard.KeyboardInputEvent;

public class GameInputMapping implements NiftyInputMapping {

	@Override
	public NiftyInputEvent convert(KeyboardInputEvent inputEvent) {
			if(inputEvent.getKey() == KeyboardInputEvent.KEY_UP) {
				return NiftyInputEvent.MoveCursorUp;
			}
			else if(inputEvent.getKey() == KeyboardInputEvent.KEY_DOWN) {
				return NiftyInputEvent.MoveCursorDown;
			}
			else if(inputEvent.getKey() == KeyboardInputEvent.KEY_RIGHT) {
				return NiftyInputEvent.MoveCursorRight;
			}
			else if(inputEvent.getKey() == KeyboardInputEvent.KEY_LEFT) {
				return NiftyInputEvent.MoveCursorLeft;
			}
			else if(inputEvent.getKey() == KeyboardInputEvent.KEY_LEFT) {
				return NiftyInputEvent.MoveCursorLeft;
			}
		return null;
	}

}
