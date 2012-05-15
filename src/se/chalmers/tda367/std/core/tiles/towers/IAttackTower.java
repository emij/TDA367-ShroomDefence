package se.chalmers.tda367.std.core.tiles.towers;

import java.util.List;

import se.chalmers.tda367.std.core.effects.IEffect;
import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Represents a type of tower that is able to attack enemies.
 * <p>Should contain a PropertyChangeSupport to support the target list.</p>
 * Dmg == Damage.
 * @author Emil Edholm
 * @date Mar 25, 2012
 */
public interface IAttackTower extends ITower{
	/**
	 * Returns the damage of the tower.
	 * @return the base amount of damage the tower does to an enemy.
	 */
	public int getDmg();
	
	/**
	 * Returns a list of effects
	 * @return a list of effects
	 */
	public List<IEffect> getEffects();
	
	/**
	 * Returns the attack speed of the tower.
	 * @return the delay in ms (milliseconds) between attacks.
	 */
	public int getAttackSpeed();
	
	/**
	 * Decides whether or not the tower is attack-ready.
	 * @param delta - the time since the last game state update in milliseconds.
	 * @return true if ready to attack, else false.
	 */
	public boolean isAttackReady(int delta);
	
	/**
	 * Damage done inside the radius from the point of impact
	 * @return the damage radius on the impact zone, from which enemies are hurt.
	 */
	public int getDmgRadius();

	/**  
	 * Tower shoot at enemies. //TODO, better doc
	 * @param enemies - List of enemies to be able too shoot at.
	 * @param pos - Position of tower
	 */
	public void shoot(List<IEnemy> enemies, Position pos);
	
	/**
	 * Method for upgrading the currentLevel on a tower.
	 */
	public void upgrade();
	
	/**
	 * Calculates the amount of gold that is refunded to the player.
	 * @return amount of gold
	 */
	public int refund();
	
	/**
	 * Returns the baseCost of the Tower
	 * @return baseCost of tower
	 */
	public int getBaseCost();
	
	/**
	 * Returns the effective radius of the Tower
	 * @return the effective radius of the Tower
	 */
	public int getRadius();
	
	/**
	 * Returns the graphical representation of the Tower
	 * @return the sprite of the tower
	 */
	public Sprite getSprite();
	
	/**
	 * Returns the cost for upgrading the tower
	 * @return the cost for upgrading the tower
	 */
	public int getUpgradeCost();
	
	/**
	 * Returns a string that represents the tower
	 * @return a string that represents the tower
	 */
	public String toString();
	
	/**
	 * Returns the current level of the Tower
	 * @return the current level of the Tower
	 */
	public int getCurrentLevel();
}
