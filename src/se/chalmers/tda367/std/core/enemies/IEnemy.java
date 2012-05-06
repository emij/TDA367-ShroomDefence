package se.chalmers.tda367.std.core.enemies;

import java.beans.PropertyChangeListener;
import java.util.List;

import se.chalmers.tda367.std.core.effects.IEffect;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Represents a killable enemy.
 * Implements the PropertyChangeListener interface.
 * This is called when an enemy is in range of a tower and is shot on, or otherwise damaged.
 * @author Emil Edholm
 * @modified Emil Johansson, Johan Andersson
 * @date Mar 25, 2012
 */
public interface IEnemy extends PropertyChangeListener, Comparable<IEnemy> {
	
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
	public float getSpeed();
	/**
	 * Increase speed of the enemy with the specified value
	 * @param inc
	 * @return current speed
	 */
	public float increaseSpeed(float inc);
	/**
	 * Decrease speed of the enemy with the specified value
	 * @param inc
	 * @return the new speed
	 */
	public float decreaseSpeed(float inc);
	
	/**
	 * Returns the current boardValue of the enemy.
	 * Higher value means closer to playerBase
	 * @return current boardValue
	 */
	public int getBoardValue();
	
	/** 
	 *  Add an effect to an enemy.
	 * @param effect - Effect to be added.
	 */
	public void addEffect(IEffect effect);
	
	/** 
	 *  Remove an effect from the enemy.
	 *  Remvoves the first instance found of the effect,
	 *  if the enemy is affected by multiple effects of the
	 *  same type only the first one will be removed.
	 * @param effect - Effect to be removed.
	 */
	public void removeEffect(IEffect effect);
	
	/**
	 * Returns the effect of the enemy.
	 * @return - List of effects on the enemy.
	 */
	public List<IEffect> getEffects();
	
	
	/**
	 * Sets the boardValue of the enemy
	 * @param boardValue
	 */
	public void setBoardValue(int boardValue);
	/**
	 * Compares the enemies boardValue to check
	 * which is closer to playerBase.
	 * Returns 1 
	 */
	@Override
	public int compareTo(IEnemy enemy);
	
	/**
	 * 
	 * @return
	 */
	public Sprite getSprite();

}
