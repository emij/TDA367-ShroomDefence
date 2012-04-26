package se.chalmers.tda367.std.core;

import se.chalmers.tda367.std.core.enemies.IEnemy;

/**
 * Represents a wave item for use in the <code>Wave</code>.
 * Contains one enemy and the spawn delay of this certain enemy.
 * @author Emil Johansson
 * @date Mar 22, 2012
 */
public class WaveItem implements Comparable<WaveItem> {
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
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(WaveItem waveItem) {
		return this.enemy.compareTo(waveItem.enemy);
	}
	
	

}
