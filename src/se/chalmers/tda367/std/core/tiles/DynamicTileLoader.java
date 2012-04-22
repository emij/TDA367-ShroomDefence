package se.chalmers.tda367.std.core.tiles;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import se.chalmers.tda367.std.core.tiles.enemies.IEnemy;
import se.chalmers.tda367.std.core.tiles.towers.ITower;
import se.chalmers.tda367.std.utilities.ExtendedClassLoader;
import se.chalmers.tda367.std.utilities.FileScanner;

/**
 * A class used to load tiles (mostly enemies and towers) from a previously
 * specified location dynamically.
 * @author Emil Edholm
 * @date   Apr 22, 2012
 */
public final class DynamicTileLoader {
	private static final Path tileFolderPath = Paths.get("data", "tiles");
	// No need to be able to create an instance.
	private DynamicTileLoader() {}
	
	/**
	 * Get a list of tiles with the specified annotation.
	 * @param annotation search for classes with this annotation.
	 * @return a list of classes with specified annotation.
	 */
	@SuppressWarnings("unchecked")
	public static List<Class<?>> getTileClasses(@SuppressWarnings("rawtypes") Class annotation) {
		if(!checkInvariants()){
			return Collections.emptyList();
		}
		
		List<File> files = FileScanner.getFiles(tileFolderPath);
		ExtendedClassLoader tileLoader = new ExtendedClassLoader("se.chalmers.tda367.std.core.tiles", tileFolderPath);
		//TODO: Check if package management can be altered or fixed. This method probably needs some rewrites.
		
		
		List<Class<?>> classList = new ArrayList<Class<?>>();

		// Iterate through each files and try to add to class list if it has correct annotation.
		for(File f : files){
			String name = f.getName();
			name = name.substring(0, name.lastIndexOf("."));
			try {
				Class<?> tile = tileLoader.loadClass(name);
				
				if(tile.getAnnotation(annotation) != null){
					classList.add(tile);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		// TODO: Sort before returning.
		// TODO: Test if it works.
		return classList;
	}
	
	/**
	 * @return Retrieves all dynamically read {@code Enemies} with a correct annotation.
	 */
	public static List<? extends IEnemy> getEnemies(){
		// TODO: Implement correctly.
		return Collections.emptyList();
	}
	
	/**
	 * 
	 * @return Retrieves all dynamically read {@code Towers} with a correct annotation.
	 */
	public static List<? extends ITower> getTowers() {
		// TODO: Implement correctly.
		return Collections.emptyList();
	}
	
	/**
	 * Check if some invariants are true.
	 * @return true if everything is as it should be, else false.
	 */
	private static boolean checkInvariants() {
		File tileFolder = new File(tileFolderPath.toUri());
		if(!tileFolder.isDirectory()){
			Logger log = Logger.getLogger("se.chalmers.tda367.std.core");
			log.severe("The tile folder could not be found at " + tileFolder.toString());
			return false;
		}
		return true;
	}
}
