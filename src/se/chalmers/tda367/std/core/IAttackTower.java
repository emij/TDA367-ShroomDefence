/**
 * TDA367-ShroomDefence
 * 
 * Class explanation goes here.
 */
package se.chalmers.tda367.std.core;

/**
 * @author Unchanged
 * @date Mar 22, 2012
 */
public interface IAttackTower extends ITower{
	/**
	 * Returns the damage of the tower
	 */
	public int getDmg();
	/**
	 * Returns the attack speed of the tower
	 */
	public int getAttackSpeed();
	/**
	 * Damage done inside the radius from the point of impact
	 * @return
	 */
	public int getDmgRadius();
	

}
