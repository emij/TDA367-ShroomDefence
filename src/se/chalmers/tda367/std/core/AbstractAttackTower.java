/**
 * TDA367-ShroomDefence
 * 
 * Class explanation goes here.
 */
package se.chalmers.tda367.std.core;

import se.chalmers.tda367.std.utilities.ISprite;

/**
 * @author Unchanged
 * @date Mar 22, 2012
 */
public  abstract class AbstractAttackTower implements IAttackTower{

	/* (non-Javadoc)
	 * @see se.chalmers.tda367.std.core.ITower#upgrade()
	 */
	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see se.chalmers.tda367.std.core.ITower#refund()
	 */
	@Override
	public int refund() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see se.chalmers.tda367.std.core.ITower#getRadius()
	 */
	@Override
	public int getRadius() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see se.chalmers.tda367.std.core.IAttackTower#getDmg()
	 */
	@Override
	public int getDmg() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see se.chalmers.tda367.std.core.IAttackTower#getAttackSpeed()
	 */
	@Override
	public int getAttackSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see se.chalmers.tda367.std.core.IAttackTower#getDmgRadius()
	 */
	@Override
	public int getDmgRadius() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see se.chalmers.tda367.std.core.IBoardTile#getSprite()
	 */
	@Override
	public ISprite getSprite() {
		// TODO Auto-generated method stub
		return null;
	}

}
