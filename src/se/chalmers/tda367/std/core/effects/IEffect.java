package se.chalmers.tda367.std.core.effects;

import com.google.inject.ImplementedBy;

/**
 * Represents an effect which can be placed upon enemies.
 * @author Johan Gustafsson
 * @modifed Johan Andersson
 * @modified Emil Edholm (May 16, 2012)
 * @date Apr 23, 2012
 */
@ImplementedBy(NoEffect.class)
public interface IEffect extends Cloneable {
	
	/**
	 * Method to get remaining duration on the effect
	 * @return Duration left on the effect
	 */
	public double getDuration();
	
	/**
	 * Decreases the duration of the effect by specified time in milliseconds.
	 * @param millisec - time in milliseconds.
	 */
	public void decrementDuration(int millisec);
	
	/**
	 * Resets the duration to the initial value if and only if the duration
	 * has not already reached zero.
	 */
	public void resetDuration();
	
	/**
	 * Method that modifies the speed.
	 * @return a float representing the new modified speed
	 */
	public float modifySpeed(float baseSpeed);
	
	/**
	 * Modifies the base health.
	 * @param baseHealth - the base health to use
	 * @return a modified health value.
	 */
	public int modifyHealth(int baseHealth);
	
	/**
	 * Modifies the armor based on the supplied armor
	 * @param baseArmor - the base armor to use
	 * @return a modified armor value
	 */
	public int modifyArmor(int baseArmor);
	
	public IEffect clone();
}
