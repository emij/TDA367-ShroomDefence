package se.chalmers.tda367.std.core;

import java.io.File;
import java.lang.annotation.Annotation;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import se.chalmers.tda367.std.core.anno.*;
import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.core.tiles.towers.ITower;
import se.chalmers.tda367.std.utilities.ExtendedClassLoader;
import se.chalmers.tda367.std.utilities.FileScanner;
import se.chalmers.tda367.std.utilities.Filter;

/**
 * A class used to load exported classes from a previously
 * specified location dynamically.
 * @author Emil Edholm
 * @date   Apr 22, 2012
 */
public final class DynamicLoader {
	// TODO: Change the following path to something more "release" friendly. Debuging purposes only.
	private static final Path exportedFolderPath = Paths.get("bin", "se", "chalmers", "tda367", "std", "core", "exported");
	
	// No need to be able to create an instance.
	private DynamicLoader() {}
	
	/**
	 * Get a list of tiles with the specified annotation.
	 * @param annotation search for classes with this annotation.
	 * @return a list of classes with specified annotation.
	 */
	@SuppressWarnings("unchecked") // Since we first check that the class has the correct annotation, it should be safe to cast.
	public static <T> List<Class<T>> getExportedClasses(Class<? extends Annotation> annotation) {
		if(!checkInvariants()){
			return Collections.emptyList();
		}
		ExtendedClassLoader classLoader = new ExtendedClassLoader("se.chalmers.tda367.std.core.exported", exportedFolderPath);
		
		List<Class<T>> classList = new ArrayList<Class<T>>();

		// Iterate through each files and try to add to class list if it has correct annotation.
		List<File> files = FileScanner.getFiles(exportedFolderPath);
		for(File f : files){
			String name = f.getName();
			name = name.substring(0, name.lastIndexOf(".")); // Remove the extension from the file
			try {
				Class<?> exportedClass = classLoader.loadClass(name);
				
				if(exportedClass.getAnnotation(annotation) != null){
					classList.add((Class<T>)exportedClass);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		return classList;
	}
	
	/**
	 * Creates a instance of the specified class and casts it to {@code type} 
	 * @param class the class to create a instance of.
	 * @return A instanced class of type {@code type} or null if unable to cast.
	 */
	public static <T> T createInstance(Class<T> classToInstantiate) {
		T tmpInstance = null;
		try {
			tmpInstance = classToInstantiate.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
		return tmpInstance;
	}
	
	
	/**
	 * @return Retrieves all dynamically read {@code Enemies} with a correct annotation sorted by enemy strength.
	 */
	public static List<Class<IEnemy>> getEnemies(){
		List<Class<IEnemy>> enemies = getExportedClasses(Enemy.class);
		Collections.sort(enemies, new EnemyComparator());
		return enemies;
	}
	
	/**
	 * Get the enemies with strength {@code maxLevel} or less.
	 * @param maxLevel the maximum strength of the enemies to retrieve.
	 * @return a list of enemies that conform to the parameters.
	 */
	public static List<Class<IEnemy>> getEnemies(int maxLevel) {
		return filterList(getEnemies(), maxLevel, new EnemyStrengthFilter(maxLevel));
	}
	
	/**
	 * Method used for filtering a list (often Enemies or Towers).
	 * @param list the list to filter
	 * @param maxLevel the max level strength to use in the filter.
	 * @param filter the actual filter to use.
	 * @return a list filtered used the supplied filter.
	 */
	private static <T> List<T> filterList(List<T> list, int maxLevel, Filter<T> filter){
		List<T> filteredList = new ArrayList<T>(list.size());

		for(T item : list) {
			if(filter.accept(item)){
				filteredList.add(item);
			}
		}
		
		return filteredList;
	}
	
	/**
	 * 
	 * @return Retrieves all dynamically read {@code Towers} with a correct annotation sorted by tower strength.
	 */
	public static List<Class<ITower>> getTowers() {
		List<Class<ITower>> towers = getExportedClasses(Tower.class);
		Collections.sort(towers, new TowerComparator());
		return towers;
	}

	/**
	 * Get the towers with strength {@code maxLevel} or less.
	 * @param maxLevel the maximum strength of the towers to retrieve.
	 * @return a list of towers that conform to the parameters.
	 */
	public static List<Class<ITower>> getTowers(int maxLevel) {
		return filterList(getTowers(), maxLevel, new TowerStrengthFilter(maxLevel));
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
	
	// TODO: Possible refactoring of the following classes? They are quite similar.
	/**
	 * Used to compare enemy strength. Allows for sorting of list of enemies.
	 * @author Emil Edholm
	 * @date   Apr 23, 2012
	 */
	private static class EnemyComparator implements Comparator<Class<IEnemy>> {

		@Override
		public int compare(Class<IEnemy> o1, Class<IEnemy> o2) {
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
	private static class TowerComparator implements Comparator<Class<ITower>> {

		@Override
		public int compare(Class<ITower> o1, Class<ITower> o2) {
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
	
	/**
	 * Used to filter the max level enemy strength described in its annotation.
	 * @author Emil Edholm
	 * @date   Apr 25, 2012
	 */
	private static class EnemyStrengthFilter implements Filter<Class<IEnemy>> {
		private final int maxLevel;
		public EnemyStrengthFilter(int maxLevel) {
			this.maxLevel = maxLevel;
		}
		
		@Override
		public boolean accept(Class<IEnemy> object) {
			Enemy annotation = object.getAnnotation(Enemy.class);
			if(annotation != null) {
				return Double.compare(annotation.enemyStrength(), maxLevel) <= 0;
			}
			return false;
		}
		
	}
	
	/**
	 * Used to filter the max level tower strength described in its annotation.
	 * @author Emil Edholm
	 * @date   Apr 25, 2012
	 */
	private static class TowerStrengthFilter implements Filter<Class<ITower>> {
		private final int maxLevel;
		public TowerStrengthFilter(int maxLevel) {
			this.maxLevel = maxLevel;
		}
		@Override
		public boolean accept(Class<ITower> object) {
			Tower annotation = object.getAnnotation(Tower.class);
			if(annotation != null) {
				return Double.compare(annotation.towerStrength(), maxLevel) <= 0;
			}
			return false;
		}
		
	}
}
