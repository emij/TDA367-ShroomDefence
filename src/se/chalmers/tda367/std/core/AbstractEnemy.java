package se.chalmers.tda367.std.core;

import se.chalmers.tda367.std.utilities.Sprite;

/**
 * A skeleton implementation of the IEnemy.
 * @author Unchanged
 * @date Mar 22, 2012
 */
public abstract class AbstractEnemy implements IEnemy{

	/* (non-Javadoc)
	 * @see se.chalmers.tda367.std.core.IEnemy#getHealth()
	 */
	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see se.chalmers.tda367.std.core.IEnemy#decreaseHealth(int)
	 */
	@Override
	public void decreaseHealth(int dmg) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see se.chalmers.tda367.std.core.IBoardTile#getSprite()
	 */
	@Override
	public Sprite getSprite() {
		// TODO Auto-generated method stub
		return null;
	}

}
