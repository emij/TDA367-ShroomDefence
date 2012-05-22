package se.chalmers.tda367.std.gui;

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
