/**
 * TDA367-ShroomDefence
 * 
 * Class explanation goes here.
 */
package se.chalmers.tda367.std.core;

/**
 * @author Unchanged
 * @date Mar 22, 2012
 */
public class WaveItem {
	private IEnemy enemy;
	private int delay;
	
	public WaveItem(IEnemy enemy, int delay){
		this.enemy = enemy;
		this.delay = delay;
	}

	/**
	 * @return the enemy
	 */
	public IEnemy getEnemy() {
		return enemy;
	}

	/**
	 * @return the delay
	 */
	public int getDelay() {
		return delay;
	}
	
	

}
