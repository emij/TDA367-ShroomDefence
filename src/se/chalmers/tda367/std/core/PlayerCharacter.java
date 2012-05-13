package se.chalmers.tda367.std.core;

import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;
import se.chalmers.tda367.std.utilities.SpriteCreator;

public class PlayerCharacter {
	private Position pos;
	
	private final static Sprite sprite = SpriteCreator.create("/images/gameplay/enemy.png");

	public PlayerCharacter(Position pos) {
		this.pos = pos;
	}
	
	public void moveTo(Position pos) {
		pos = new Position(pos);
	}
	
	public Position getPos() {
		return pos;
	}
	
	public void moveUp() {
		pos.setY(pos.getY()-5);
	}
	
	public void moveDown() {
		pos.setY(pos.getY()+5);
	}
	
	public void moveRight() {
		pos.setX(pos.getX()+5);
	}
	
	public void moveLeft() {
		pos.setX(pos.getX()-5);
	}
	
	/**
	 * @return the sprite (image representation) of the character.
	 */
	public Sprite getSprite() {
		return sprite;
	}
}
