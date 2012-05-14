package se.chalmers.tda367.std.core;

import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;
import se.chalmers.tda367.std.utilities.SpriteCreator;

/**
 * Concrete implementation of the {@code PlayerCharacter} class, this is where all the character data is stored.
 * @author Johan Gustafsson
 * @date   May 14, 2012
 */
public class PlayerCharacter implements IPlayerCharacter {
	private Position pos;
	
	private final static Sprite sprite = SpriteCreator.create("/images/gameplay/enemy.png");

	public PlayerCharacter(Position pos) {
		this.pos = pos;
	}
	
	@Override
	public void moveTo(Position pos) {
		pos = new Position(pos);
	}
	
	@Override
	public Position getPos() {
		return pos;
	}
	
	@Override
	public Sprite getSprite() {
		return sprite;
	}
}
