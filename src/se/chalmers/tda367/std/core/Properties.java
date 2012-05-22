package se.chalmers.tda367.std.core;

import java.io.File;
import java.nio.file.Path;

/**
 * Supplies some constants/properties.
 * @author Unchanged
 * @date Mar 22, 2012
 */
public enum Properties {
	INSTANCE; // Prevents instantiation
	
	public Path getDataFolder(){
		return null;
	}
	// Some needed constants goes here
	
	/**
	 * Getter for highscore file.
	 * @return File containing the highscores for the game.
	 */
	public File getHighscoreFile() {
		return new File("data/highscore.bin");
	}
	
	/**
	 * Return integer representing the scale used by tiles on the gameboard.
	 * @return integer representing the scale of the tiles.
	 */
	public int getTileScale() {
		return 32;
	}
}