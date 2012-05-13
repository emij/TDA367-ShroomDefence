package se.chalmers.tda367.std.core.events;

import se.chalmers.tda367.std.core.enemies.IEnemy;

/**
 * This event is fired when an enemy has entered the player base.
 * @author Emil Edholm
 * @date   May 13, 2012
 */
public final class EnemyEnteredBaseEvent {
	private final IEnemy offendingEnemy;
	
	public EnemyEnteredBaseEvent(IEnemy offendingEnemy) {
		this.offendingEnemy = offendingEnemy;
	}

	/**
	 * @return the offending enemy
	 */
	public IEnemy getOffendingEnemy() {
		return offendingEnemy;
	}
}
