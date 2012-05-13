package se.chalmers.tda367.std.core.events;

import se.chalmers.tda367.std.core.EnemyItem;

/**
 * This event is fired when an enemy has entered the player base.
 * @author Emil Edholm
 * @date   May 13, 2012
 */
public final class EnemyEnteredBaseEvent {
	private final EnemyItem offendingEnemy;
	
	public EnemyEnteredBaseEvent(EnemyItem offendingEnemy) {
		this.offendingEnemy = offendingEnemy;
	}

	/**
	 * @return the offending enemy
	 */
	public EnemyItem getOffendingEnemy() {
		return offendingEnemy;
	}
}
