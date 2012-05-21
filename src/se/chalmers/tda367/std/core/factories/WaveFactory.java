package se.chalmers.tda367.std.core.factories;

import java.util.*;
import java.util.logging.Logger;

import se.chalmers.tda367.std.core.*;
import se.chalmers.tda367.std.core.anno.Enemy;
import se.chalmers.tda367.std.core.enemies.IEnemy;

/**
 * A wave factory class.
 * Used for dynamically create game waves based on a single integer parameter which decides the difficulty of the wave.
 * @author Emil Edholm
 * @date Apr 24, 2012
 */
public class WaveFactory implements IFactory<Wave, Integer> {

	/**
	 * Creates a wave from the specified parameter(s).
	 * @param level the level difficulty of the wave about to be created.
	 * @throws java.io.FileNotFoundException - if the map corresponding to the level does not exist.
	 */
	@Override
	public Wave create(Integer level) {
		List<Class<IEnemy>> exportedEnemies = DynamicLoader.getEnemies(level);
		List<WaveItem> waveList = new ArrayList<WaveItem>();
		
		// TODO: Tweak and balance the algorithm a little.
		// TODO: Remove System.out.println...
		
		Random rnd = new Random();
		Enemy enemyAnno = null;
		int count = 0, delay = 0;
		for(Class<IEnemy> enemyClass : exportedEnemies) {
			enemyAnno = enemyClass.getAnnotation(Enemy.class);
			count = (int) ((Math.abs(enemyAnno.enemyStrength() - (rnd.nextInt(9) + 5)))) * level; // Number of enemies to add.

			Logger.getLogger("se.chalmers.tda367.std.core.factories").info("Releasing " + count + " " + enemyClass.toString());
			while(count-- > 0) {
				waveList.add(new WaveItem(DynamicLoader.createInstance(enemyClass), delay));
				delay = rnd.nextInt(500);
				delay = delay + 50;
			}
		}

		Collections.shuffle(waveList);
		
		// Convert the list to a queue.
		Queue<WaveItem> queue = new LinkedList<WaveItem>();
		for(WaveItem item : waveList)
			queue.add(item);
		return new Wave(queue);
	}
}
