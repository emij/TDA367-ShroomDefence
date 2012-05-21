package se.chalmers.tda367.std.core;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents a wave of enemies.
 * This is used to determine what types of enemies, how many and the spawn delay.
 * @author Johan Andersson
 * @date Mar 22, 2012
 */
public class Wave {
	
	private Queue<WaveItem> items;
	
	/** 
	 * Create a new wave with the specified items in the queue.
	 * @param items - use these items in the queue.
	 */
	public Wave(Queue<WaveItem> items){
		this.items = new LinkedList<WaveItem>(items);
	}
	
	/** 
	 * Gets the next enemy in line.
	 * 
	 * @return the next enemy in line or null if queue, empty.
	 */
	public WaveItem getNext() {
		return items.poll();
	}
	
	/** 
	 * Gets the number of enemies in the wave.
	 * @return - the number of enemies in the wave.
	 */
	public int size(){
		return items.size();
	}
}
