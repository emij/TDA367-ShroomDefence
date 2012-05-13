package se.chalmers.tda367.std.core.tiles;

import se.chalmers.tda367.std.utilities.SpriteCreator;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * A concrete player base implementation.
 * @author Emil Johansson
 * @date Mar 22, 2012
 */
public class PlayerBase implements IPlayerBase {
	
	private static final int DEFAULT_HEALTH = 2;
	private int health;
	
	//TODO: change sprite for the PlayerBase tile.
	private final static Sprite sprite = SpriteCreator.create("/images/gameplay/castle-tile.png");
	
	public PlayerBase(int health){
		this.health = health;
	}
	
	public PlayerBase() {
		this.health = DEFAULT_HEALTH;
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
