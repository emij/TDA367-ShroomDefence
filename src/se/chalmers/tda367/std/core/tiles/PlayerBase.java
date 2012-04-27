package se.chalmers.tda367.std.core.tiles;

import se.chalmers.tda367.std.utilities.Sprite;

/**
 * A concrete player base implementation.
 * @author Emil Johansson
 * @date Mar 22, 2012
 */
public class PlayerBase implements IPlayerBase {
	
	private int health;
	private final Sprite sprite; // TODO: get and set a resource for the playerBase.
	
	public PlayerBase(int health){
		this.health = health;
		sprite = new Sprite("/images/gameplay/castle-tile.png");
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public int increaseHealth(int inc) {
		health = health + inc;
		return health;
	}

	@Override
	public int decreaseHealth() {
		health--;
		return health;
	}

	@Override
	public int decreaseHealth(int dmg) {
		health = health - dmg;
		return health;
	}

}
