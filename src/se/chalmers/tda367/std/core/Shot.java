package se.chalmers.tda367.std.core;

import se.chalmers.tda367.std.core.effects.IEffect;

/**
 * Represents a shot from a friendly to an enemy.
 * Contains info about damage, effects etc.
 * @author Emil Edholm
 * @date   May 16, 2012
 */
public interface Shot {
	
	/**
	 * @return the damage the shot makes. (May be negated by armor or such)
	 */
	public int getDamage();
	
	/**
	 * @return the effect that comes with the shot, such as poison or slow.
	 */
	public IEffect getEffect();
}
