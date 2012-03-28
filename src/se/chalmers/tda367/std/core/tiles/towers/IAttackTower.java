package se.chalmers.tda367.std.core.tiles.towers;

import se.chalmers.tda367.std.core.tiles.enemies.IEnemy;

/**
 * Represents a type of tower that is able to attack enemies.
 * <p>Should contain a PropertyChangeSupport to support the target list.</p>
 * Dmg == Damage.
 * @author Emil Edholm
 * @date Mar 25, 2012
 */
public interface IAttackTower extends ITower{
	
	/**
	 * Fire the "weapon" on the tower.
	 * Essentially notify all enemies in the target list of damage done to them.
	 */
	public void fire();
	
	/**
	 * Returns the damage of the tower.
	 * @return the base amount of damage the tower does to an enemy.
	 */
	public int getDmg();
	
	/**
	 * Returns the attack speed of the tower.
	 * @return the delay in ms (milliseconds) between attacks.
	 */
	public int getAttackSpeed();
	
	/**
	 * Damage done inside the radius from the point of impact
	 * @return the damage radius on the impact zone, from which enemies are hurt.
	 */
	public int getDmgRadius();
	
	/**
	 * Add an enemy to the towers target list.
	 * <p>
	 * The enemies added here will be damaged by the towers base damage
	 * based on the attack speed.</p> 
	 * 
	 * <p>Only enemies within the towers effective radius should be added here.</p>
	 * <p>If {@code enemy} is null, no action is taken and no exception is thrown.</p>
	 * @param enemy the enemy within range.
	 */
	public void addToTargetList(IEnemy enemy);
	
	/**
	 * Remove an enemy from the target list.
	 * If {@code enemy} is not in the target list or null, no action will be taken.
	 * @param enemy the enemy to remove.
	 */
	public void removeFromTargetList(IEnemy enemy);
	
}
