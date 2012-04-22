package se.chalmers.tda367.std.core.tiles.enemies;

import java.beans.PropertyChangeListener;

import se.chalmers.tda367.std.core.tiles.IBoardTile;

/**
 * Represents a killable enemy.
 * Implements the PropertyChangeListener interface.
 * This is called when an enemy is in range of a tower and is shot on, or otherwise damaged.
 * @author Emil Edholm
 * @modified Emil Johansson
 * @date Mar 25, 2012
 */
public interface IEnemy extends IBoardTile, PropertyChangeListener{
	/**
	 * Returns the health of the enemy.
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
	/**
	 * Returns the amount of gold you get for killing an enemy.
	 * @return the lootValue of the enemy.
	 */
	public int getLootValue();

	/**
	 * Returns the speed of the enemy.
	 * @return the speed of the enemy
	 */
	public int getSpeed();
	/**
	 * Increase speed of the enemy with the specified value
	 * @param inc
	 * @return current speed
	 */
	public int increaseSpeed(int inc);
	/**
	 * Decrease speed of the enemy with the specified value
	 * @param inc
	 * @return current speed
	 */
	public int decreaseSpeed(int inc);
	/**
	 * Returns the current boardValue of the enemy.
	 * Higher value means closer to playerBase
	 * @return current boardValue
	 */
	public int getBoardValue();
	/**
	 * Sets the boardValue of the enemy
	 * @param boardValue
	 */
	public void setBoardValue(int boardValue);
}
