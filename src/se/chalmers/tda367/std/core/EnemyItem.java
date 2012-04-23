package se.chalmers.tda367.std.core;

import se.chalmers.tda367.std.core.tiles.enemies.IEnemy;
import se.chalmers.tda367.std.utilities.Position;

/**
 * Represents an enemy, the enemy's position on the gameboard and it's distance to the end position.
 * @author Johan Gustafsson
 * @date April 15, 2012
 */
class EnemyItem {
	private IEnemy enemy;
	private Position enemyPos;
	private double distanceTraveled;
	
	public EnemyItem(IEnemy enemy, Position enemyPos) {
		this.enemy = enemy;
		this.enemyPos = enemyPos;
		this.distanceTraveled = 0;
	}
	
	/**
	 * @return the enemy reference.
	 */
	public IEnemy getEnemy() {
		return enemy;
	}
	
	/**
	 * @return the Position the enemy have on the gameboard.
	 */
	public Position getEnemyPos() {
		return enemyPos;
	}
	
	/**
	 * @return the shortest distance between the enemy and the end position.
	 */
	public double getDistanceTraveled() {
		return distanceTraveled;
	}
}
