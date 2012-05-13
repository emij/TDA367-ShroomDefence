package se.chalmers.tda367.std.core;

import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;
import se.chalmers.tda367.std.utilities.SpriteCreator;

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
	public void moveUp() {
		pos.setY(pos.getY()-5);
	}
	
	@Override
	public void moveDown() {
		pos.setY(pos.getY()+5);
	}
	
	@Override
	public void moveRight() {
		pos.setX(pos.getX()+5);
	}
	
	@Override
	public void moveLeft() {
		pos.setX(pos.getX()-5);
	}
	
	@Override
	public Sprite getSprite() {
		return sprite;
	}
}
