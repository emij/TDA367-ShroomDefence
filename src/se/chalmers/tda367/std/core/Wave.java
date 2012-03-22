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
		throw new NotImplementedException();
	}
	public WaveItem getNext(){
		throw new NotImplementedException();
	}

}
