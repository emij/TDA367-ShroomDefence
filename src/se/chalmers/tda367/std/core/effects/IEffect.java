package se.chalmers.tda367.std.core.effects;

/**
 * Represents an effect which can be placed upon enemies.
 * @author Johan Gustafsson
 * @modifed Johan Andersson
 * @date Apr 23, 2012
 */

public interface IEffect {
	
	/**
	 * Method to get remaining duration on the effect
	 * @return Duration left on the effect
	 */
	public double getDuration();
	
	/**
	 * Decreases the duration of the effect by specified time in milisec.
	 * @param milisec - time in miliseconds.
	 */
	public void decrementDuration(double milisec);
	
	/**
	 * Method to get the effect's speed modifier
	 * @return a double representing the effect's speed modifier.
	 */
	public double getSpeedModifier();
	
	/**
	 * Method to get the effect's health modifier
	 * @return a double representing the effect's health modifier.
	 */
	public double getHealthModifier();
	
	/**
	 * Method to get the effect's armor modifier
	 * @return a double representing the effect's armor modifier.
	 */
	public double getArmorModifier();
	
	/**
	 * This will set the effects duration to given double.
	 * @param duration double to set the duration to.
	 */
	public void setDuration(double duration);
	
	/**
	 * This will give a new copy of the effect which called the method.
	 * @return copy of the calling effect.
	 */
	public IEffect getCopy();
}
