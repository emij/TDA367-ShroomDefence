package se.chalmers.tda367.std.core;

import java.util.Queue;


/**
 * Represents a wave of enemies.
 * This is used to determine what types of enemies, how many and the spawn delay.
 * @author Johan Andersson
 * @date Mar 22, 2012
 */
public class Wave {
	
	private Queue<WaveItem> items;
	
	public Wave(Queue<WaveItem> items){
		this.items = items;
	}
	
	
	/** 
	 * Gets the next enemy in line.
	 * 
	 * @return the next enemy in line or null if queue, empty.
	 */
	public WaveItem getNext() {
		return items.poll();
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
