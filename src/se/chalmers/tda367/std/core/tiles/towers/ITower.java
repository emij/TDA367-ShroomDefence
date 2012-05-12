package se.chalmers.tda367.std.core.tiles.towers;

import java.util.List;

import se.chalmers.tda367.std.core.effects.IEffect;
import se.chalmers.tda367.std.core.tiles.IBoardTile;

/**
 * Represents a general tower.
 * @author Emil Edholm
 * @modified Johan Andersson
 * @modified Johan Gustafsson
 * @date Mar 25, 2012
 */
public interface ITower extends IBoardTile {
	/**
	 * Upgrade the tower to the next level
	 */
	public void upgrade();
	
	/**
	 * Returns the cost to upgrade the tower to the next level.
	 * @return the actual cost to upgrade the tower or -1 if no upgrade possible.
	 */
	public int getUpgradeCost();
	
	/**
	 * Refunds some of the tower value, i.e. selling the tower
	 * @return 75% of the base cost.
	 */
	public int refund();
	
	/**
	 * Returns the effective radius of the tower
	 */
	public int getRadius();
	
	/**
	 * The cost to build the tower.
	 * @return the base cost of the tower.
	 */
	public int getBaseCost();
	
	/**
	 * Returns the effects the tower can apply to enemies.
	 * @return - effects the tower can apply.
	 */
	public List<IEffect> getEffects();
	
	/**
	 * Returns the towers current level.
	 * @return current level of tower
	 */
	public int getCurrentLevel();
}
