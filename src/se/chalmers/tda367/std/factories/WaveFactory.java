package se.chalmers.tda367.std.factories;

import java.util.*;
import java.util.logging.Logger;

import se.chalmers.tda367.std.core.*;
import se.chalmers.tda367.std.core.enemies.IEnemy;

/**
 * A wave factory class.
 * Used for dynamically create game waves based on a single integer parameter which decides the difficulty of the wave.
 * @author Emil Edholm
 * @date Apr 24, 2012
 */
public class WaveFactory implements IFactory<Wave> {

	/**
	 * Creates a wave from the specified parameter(s).
	 * @param waveLevel the level difficulty of the wave about to be created.
	 */
	@Override
	public Wave create(Object waveLevel) {
		if(!(waveLevel instanceof Integer)) {
			Logger.getLogger("se.chalmers.tda367.std.factories").severe("Supplied parameter is not a wave level (integer).");
		}
		int lvl = (Integer)waveLevel;
		List<Class<IEnemy>> exportedEnemies = DynamicLoader.getEnemies(lvl);
		
		Queue<WaveItem> waveQueue = new LinkedList<WaveItem>();
		
		// TODO: Tweak and balance the algorithm since it now just releases one enemy of each type.
		int count = 0;
		for(Class<IEnemy> enemyClass : exportedEnemies) {
			waveQueue.add(new WaveItem(DynamicLoader.createInstance(enemyClass), count++ * 1000));
		}

		return new Wave(waveQueue);
	}
}
