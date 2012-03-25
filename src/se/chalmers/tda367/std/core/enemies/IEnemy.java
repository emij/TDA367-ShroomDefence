package se.chalmers.tda367.std.core.enemies;

import java.beans.PropertyChangeListener;

import se.chalmers.tda367.std.core.IBoardTile;

/**
 * Represents a killable enemy.
 * Implements the PropertyChangeListener interface.
 * This is called when an enemy is in range of a tower and is shot on, or otherwise damaged.
 * @author Emil Edholm
 * @date Mar 25, 2012
 */
public interface IEnemy extends IBoardTile, PropertyChangeListener{
	/**
	 * Returns the health of the enemy
	 * @return the current health of the enemy.
	 */
	public int getHealth();
	
	/**
	 * Damage the enemy with the specified base damage.
	 * The enemy may mitigate the damage based on it's properties, such as shield or armor.
	 * <p>
	 * If supplied damage results in a negative number, health should be set to 0.
	 * </p>
	 * @param dmg the base damage a tower does.
	 */
	public void decreaseHealth(int dmg);
	

}
