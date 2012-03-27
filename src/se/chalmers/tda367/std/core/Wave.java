package se.chalmers.tda367.std.core;

import java.util.Queue;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Represents a wave of enemies.
 * This is used to determine what types of enemies, how many and the spawn delay.
 * @author Unchanged
 * @date Mar 22, 2012
 */
public class Wave {
	
	// Maybe some type of node implementation or some iterator of some kind instead?
	private Queue<WaveItem> items;
	
	public Wave(Queue<WaveItem> items){
		this.items = items;
	}
	
	
	/** Gets the next enemy in line.
	 * 
	 * @return - enemy in line.
	 * @throws Exception - if there's no enemy.
	 */
	public WaveItem getNext() throws Exception{
		WaveItem item = items.poll();
		if(item != null){
			return item;
		} else {
			//TODO: Look over the exception type.
			throw new Exception("No more itemzzz");
		}
	}
	
	/** Gets the number of enemies.
	 * 
	 * @return - the number of enemies in the wave.
	 */
	public int getNumberOfEnemies(){
		return items.size();
	}
	
	/** Gets the total loot value of the wave.
	 * 
	 * @return - the total loot value;
	 */
	public int getWaveLootValue(){
		int total = 0;
		for (WaveItem wi: items) {
			total += wi.getEnemy().getLootValue();
		}
		return total;
	}
	
	/** Gets the total health value of the wave.
	 * 
	 * @return - the total health value;
	 */
	public int getHealthValue(){
		int total = 0;
		for (WaveItem wi: items) {
			total += wi.getEnemy().getHealth();
		}
		return total;
	}

}
