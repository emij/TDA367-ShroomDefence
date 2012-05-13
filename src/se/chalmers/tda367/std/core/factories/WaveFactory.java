package se.chalmers.tda367.std.core.factories;

import java.util.*;
import se.chalmers.tda367.std.core.*;
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
	 */
	@Override
	public Wave create(Integer level) {
		List<Class<IEnemy>> exportedEnemies = DynamicLoader.getEnemies(level);
		Queue<WaveItem> waveQueue = new LinkedList<WaveItem>();
		
		// TODO: Tweak and balance the algorithm since it now just releases one enemy of each type.
		int count = 0;
		for(Class<IEnemy> enemyClass : exportedEnemies) {
			waveQueue.add(new WaveItem(DynamicLoader.createInstance(enemyClass), count++ * 1000));
		}

		return new Wave(waveQueue);
	}
}
