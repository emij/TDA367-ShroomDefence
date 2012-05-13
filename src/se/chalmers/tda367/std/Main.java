package se.chalmers.tda367.std;


import java.util.logging.*;

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
		Logger root = Logger.getLogger("");
		Handler[] handlers = root.getHandlers();

		for (int i = 0; i < handlers.length; i++) {
		  if (handlers[i] instanceof ConsoleHandler) {
		    ((ConsoleHandler)handlers[i]).setLevel(Level.WARNING);
		  }
		}
		
		try {
			SpriteCreator.setNativeSpriteClass(NativeSlickSprite.class);
            AppGameContainer app = new AppGameContainer(new STDGame(), 1024, 720, false);
            app.start();
	    } catch (SlickException e) {
	        e.printStackTrace();
	    }
	}

}
