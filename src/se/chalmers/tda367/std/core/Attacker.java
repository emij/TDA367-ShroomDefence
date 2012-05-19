package se.chalmers.tda367.std.core;

import java.util.List;

import se.chalmers.tda367.std.utilities.Position;

/**
 * Represents a attacker (such as a Tower of PlayerCharacter) that can shoot at a {@code Attackable}.
 * @see {@link se.chalmers.tda367.std.core.Attackable}
 * @author Emil Edholm
 * @date   May 16, 2012
 */
public interface Attacker {
	
	/**
	 * Causes the {@code Entity} to attack the enemies supplied.
	 * @param enemies - list of enemies to attack
	 * @param pos - The position of the {@code Attacker}
	 */
	void shoot(List<Attackable> enemies, Position pos);
	
	/**
	 * Checks if the character is ready to attack again.
	 * @param delta - the time since the last game state update in milliseconds.
	 * @return true if ready to attack, else false.
	 */
	boolean isAttackReady(int delta);
}
