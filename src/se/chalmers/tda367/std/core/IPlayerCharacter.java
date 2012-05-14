package se.chalmers.tda367.std.core;

import java.util.List;

import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Interface for the players character.
 * @author Johan Gustafsson
 * @date   May 14, 2012
 */
public interface IPlayerCharacter {
	public void moveTo(Position pos);
	
	/**
	 * Gives reference to the character's position
	 * @return position of the character
	 */
	public Position getPos();
	
	/**
	 * @return the sprite (image representation) of the character.
	 */
	public Sprite getSprite();
	
	/**
	 * Causes the character to attack an enemy.
	 * @param enemies list of possible enemies to attack
	 */
	void shoot(List<IEnemy> enemies);
	
	/**
	 * Checks if the character is ready to attack again.
	 * @param delta - the time since the last game state update in milliseconds.
	 * @return true if ready to attack, else false.
	 */
	boolean isAttackReady(int delta);
}
