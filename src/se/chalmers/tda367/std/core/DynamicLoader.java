package se.chalmers.tda367.std.core;

import java.io.File;
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

		return classList;
	}
	
	/**
	 * Used to create instances of a list of classes.
	 */
	private static <T> List<T> getInstanceList(Class<T> type, List<Class<?>> classList) {
		if(type == null) {
			throw new NullPointerException("type is null");
		}
		if(classList == null){
			throw new NullPointerException("classList is null");
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
	public static List<IEnemy> getEnemies(){
		List<Class<?>> enemyClasses = getExportedClasses(Enemy.class);
		Collections.sort(enemyClasses, new EnemyComparator());
		return getInstanceList(IEnemy.class, enemyClasses);
	}
	
	/**
	 * Get the enemies with strength {@code maxLevel} or less.
	 * @param maxLevel the maximum strength of the enemies to retrieve.
	 * @return a list of enemies that conform to the parameters.
	 */
	public static List<IEnemy> getEnemies(int maxLevel) {
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
	public static List<ITower> getTowers() {
		List<Class<?>> towers = getExportedClasses(Tower.class);
		Collections.sort(towers, new TowerComparator());
		return getInstanceList(ITower.class, towers);
	}

	/**
	 * Get the towers with strength {@code maxLevel} or less.
	 * @param maxLevel the maximum strength of the towers to retrieve.
	 * @return a list of towers that conform to the parameters.
	 */
	public static List<ITower> getTowers(int maxLevel) {
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
	
	/**
	 * Used to filter the max level enemy strength described in its annotation.
	 * @author Emil Edholm
	 * @date   Apr 25, 2012
	 */
	private static class EnemyStrengthFilter implements Filter<IEnemy> {
		private final int maxLevel;
		public EnemyStrengthFilter(int maxLevel) {
			this.maxLevel = maxLevel;
		}
		
		@Override
		public boolean accept(IEnemy object) {
			Enemy annotation = object.getClass().getAnnotation(Enemy.class);
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
	private static class TowerStrengthFilter implements Filter<ITower> {
		private final int maxLevel;
		public TowerStrengthFilter(int maxLevel) {
			this.maxLevel = maxLevel;
		}
		@Override
		public boolean accept(ITower object) {
			Tower annotation = object.getClass().getAnnotation(Tower.class);
			if(annotation != null) {
				return Double.compare(annotation.towerStrength(), maxLevel) <= 0;
			}
			return false;
		}
		
	}
}
