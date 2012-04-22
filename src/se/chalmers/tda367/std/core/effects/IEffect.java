package se.chalmers.tda367.std.core.effects;

/**
 * Represents an effect which can be placed upon enemies.
 * @author Johan Gustafsson
 * @date Apr 23, 2012
 */

public interface IEffect {
	
	/**
	 * Method to get remaining duration on the effect
	 * @return Duration left on the effect
	 */
	public int getDuration();
	
	/**
	 * Decreases the duration of the effect by 1.
	 */
	public void decrementDuration();
	
	/**
	 * Method to get the effect's speed modifier
	 * @return a integer representing the effect's speed modifier.
	 */
	public int getSpeedModifier();
	
	/**
	 * Method to get the effect's health modifier
	 * @return a integer representing the effect's health modifier.
	 */
	public int getHealthModifier();
	
	/**
	 * Method to get the effect's armor modifier
	 * @return a integer representing the effect's armor modifier.
	 */
	public int getArmorModifier();
}
