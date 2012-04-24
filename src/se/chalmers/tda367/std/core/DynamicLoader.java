package se.chalmers.tda367.std.core;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import se.chalmers.tda367.std.core.anno.Enemy;
import se.chalmers.tda367.std.core.anno.Tower;
import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.core.tiles.towers.ITower;
import se.chalmers.tda367.std.utilities.ExtendedClassLoader;
import se.chalmers.tda367.std.utilities.FileScanner;

/**
 * A class used to load exported classes from a previously
 * specified location dynamically.
 * @author Emil Edholm
 * @date   Apr 22, 2012
 */
public final class DynamicLoader {
	private static final Path exportedFolderPath = Paths.get("data", "tiles");
	
	// No need to be able to create an instance.
	private DynamicLoader() {}
	
	/**
	 * Get a list of tiles with the specified annotation.
	 * @param annotation search for classes with this annotation.
	 * @return a list of classes with specified annotation.
	 */
	@SuppressWarnings("unchecked")
	public static List<Class<?>> getExportedClasses(@SuppressWarnings("rawtypes") Class annotation) {
		if(!checkInvariants()){
			return Collections.emptyList();
		}
		ExtendedClassLoader classLoader = new ExtendedClassLoader("se.chalmers.tda367.std.core.exported", exportedFolderPath);
		
		List<Class<?>> classList = new ArrayList<Class<?>>();

		// Iterate through each files and try to add to class list if it has correct annotation.
		List<File> files = FileScanner.getFiles(exportedFolderPath);
		for(File f : files){
			String name = f.getName();
			name = name.substring(0, name.lastIndexOf(".")); // Remove the extension from the file
			try {
				Class<?> export = classLoader.loadClass(name);
				
				if(export.getAnnotation(annotation) != null){
					classList.add(export);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		// TODO: Sort before returning?
		// TODO: Test if it works.
		return classList;
	}
	
	private static <T> List<T> getInstanceList(Class<T> type, List<Class<?>> classList) {
		if(type == null) {
			throw new NullPointerException("type is null");
		}
		
		List<T> instanceList = new ArrayList<T>(classList.size());
		Object tmpInstance = null;
		for(Class<?> c : classList) {
			try {
				tmpInstance = c.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			
			if(type.isInstance(tmpInstance)){
				instanceList.add(type.cast(tmpInstance));
			}
			
		}
		
		return instanceList;
	}
	
	
	/**
	 * @return Retrieves all dynamically read {@code Enemies} with a correct annotation sorted by enemy strength.
	 */
	public static List<Class<?>> getEnemies(){
		List<Class<?>> enemies = getExportedClasses(Enemy.class);
		Collections.sort(enemies, new EnemyComparator());
		return enemies;
	}
	
	/**
	 * 
	 * @return Retrieves all dynamically read {@code Towers} with a correct annotation sorted by tower strength.
	 */
	public static List<Class<?>> getTowers() {
		List<Class<?>> towers = getExportedClasses(Tower.class);
		Collections.sort(towers, new TowerComparator());
		return towers;
	}
	
	/**
	 * Check if some invariants are true.
	 * @return true if everything is as it should be, else false.
	 */
	private static boolean checkInvariants() {
		File tileFolder = new File(exportedFolderPath.toUri());
		if(!tileFolder.isDirectory()){
			Logger log = Logger.getLogger("se.chalmers.tda367.std.core");
			log.severe("The tile folder could not be found at " + tileFolder.toString());
			return false;
		}
		return true;
	}
	
	/**
	 * Used to compare enemy strength. Allows for sorting of list of enemies.
	 * @author Emil Edholm
	 * @date   Apr 23, 2012
	 */
	private static class EnemyComparator implements Comparator<Class<?>> {

		@Override
		public int compare(Class<?> o1, Class<?> o2) {
			Enemy e1 = o1.getAnnotation(Enemy.class);
			Enemy e2 = o2.getAnnotation(Enemy.class);
			if(e1 != null && e2 != null){
				int strengthDiff = Double.compare(e1.enemyStrength(), e2.enemyStrength());
				if(strengthDiff != 0){
					return strengthDiff;
				}
				
				return e1.name().compareTo(e2.name());
			}
			return 0;
		}
		
	}
	
	/**
	 * Used to compare Tower-strength and tower name.
	 * Allows for sorting of lists of towers.
	 * @author Emil Edholm
	 * @date   Apr 23, 2012
	 */
	private static class TowerComparator implements Comparator<Class<?>> {

		@Override
		public int compare(Class<?> o1, Class<?> o2) {
			Tower t1 = o1.getAnnotation(Tower.class);
			Tower t2 = o2.getAnnotation(Tower.class);
			if(t1 != null && t2 != null){
				int strengthDiff = Double.compare(t1.towerStrength(), t2.towerStrength());
				if(strengthDiff != 0){
					return strengthDiff;
				}
				
				return t1.name().compareTo(t2.name());
			}
			return 0;
		}
	}
}
