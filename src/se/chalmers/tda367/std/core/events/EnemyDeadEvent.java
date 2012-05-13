package se.chalmers.tda367.std.core.events;

import se.chalmers.tda367.std.core.enemies.IEnemy;

/**
 * This event is fired when an enemy has died.
 * @author Emil Edholm
 * @date   May 13, 2012
 */
public final class EnemyDeadEvent {
	private final IEnemy deadEnemy;
	
	public EnemyDeadEvent(IEnemy deadEnemy) {
		this.deadEnemy = deadEnemy;
	}

	/**
	 * @return the dead enemy
	 */
	public IEnemy getDeadEnemy() {
		return deadEnemy;
	}
	
	
}
