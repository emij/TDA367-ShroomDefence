package se.chalmers.tda367.std.gui;

/**
 * This class will serve as a helper for loading resources to the GUI.
 * It will be used by {@code GameplayRenderer}, {@code GameplayState},
 * {@code HighscoreState} and {@code MainMenuState}.
 * @author Johan Gustafsson
 * @date 2012-05-12
 */

public enum GUIResourceManager {
	INSTANCE;
	
	/**
	 * Helper method to get path to resources.
	 * @param path to search for.
	 * @return
	 */
	public String getResourcePath(String path){
		return getClass().getResource(path).getPath();
	}
}
