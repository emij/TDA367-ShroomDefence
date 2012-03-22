package se.chalmers.tda367.std.core;

/**
 * Represents a general tower.
 * @author Unchanged
 * @date Mar 22, 2012
 */
public interface ITower extends IBoardTile{
	/**
	 * Upgrade the tower to the next level
	 */
	public void upgrade();
	/**
	 * refunds some of the tower value, i.e. selling the tower
	 * @return
	 */
	public int refund();
	/**
	 * Returns the effective radius of the tower
	 */
	public int getRadius();
	

}
