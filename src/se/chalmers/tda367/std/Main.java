package se.chalmers.tda367.std;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.std.gui.NativeSlickSprite;
import se.chalmers.tda367.std.gui.STDGame;
import se.chalmers.tda367.std.utilities.SpriteCreator;

/**
 * Contains the main method. The entrance to the game.
 * @author Unchanged
 * @date Mar 22, 2012
 */
public final class Main {

	/**
	 * The main method. Used to start the game.
	 * @param args the command line arguments.
	 */
	public static void main(String[] args) {
		SpriteCreator.setNativeSpriteClass(NativeSlickSprite.class);
		try {
            AppGameContainer app = new AppGameContainer(new STDGame(), 1024, 720, false);
            app.start();
	    } catch (SlickException e) {
	        e.printStackTrace();
	    }
	}

}
