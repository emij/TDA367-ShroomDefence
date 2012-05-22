package se.chalmers.tda367.std.core;

import se.chalmers.tda367.std.core.effects.IEffect;
import se.chalmers.tda367.std.utilities.Position;

/**
 * Represents a attackable entity, such as an enemy.
 * @author Emil Edholm
 * @date   May 19, 2012
 * @modified by Emil Johansson (May 22, 2012)
 */
public interface Attackable extends Comparable<Attackable>{
	
	/** @return a defensive copy of the {@code Attackable} position. */
	public Position getPosition();
	
	/**
	 * Receive a shot (think bullet) from a {@code Attacker}.
	 * This should also apply the containing {@code IEffect} on the enemy.
	 * @param s - the shot that contains info about damage, etc.
	 * @see {@link se.chalmers.tda367.std.core.Attacker}
	 */
	public void receiveShot(Shot s);
	
	/**
	 * Whether or not {@code this} has any effect of type {@code type} applied.
	 * @param type - the type to look for.
	 * @return true if any effect of type {@code type} is applied.
	 */
	public boolean hasEffect(Class<? extends IEffect> type);
	
	/**
	 * Compares two Attackables to see which one is closest to the base
	 * @return the difference between two Attackable distance traveled
	 */
	public int compareTo(Attackable o);
}
