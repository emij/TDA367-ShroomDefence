package se.chalmers.tda367.std.core;

import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;

public interface IPlayerCharacter {
	public void moveTo(Position pos);
	
	public Position getPos();
	
	/**
	 * Makes the character move up.
	 */
	public void moveUp();
	
	/**
	 * Makes the character move down.
	 */
	public void moveDown();
	
	/**
	 * Makes the character move right.
	 */
	public void moveRight();
	
	/**
	 * Makes the character move left.
	 */
	public void moveLeft();
	
	/**
	 * @return the sprite (image representation) of the character.
	 */
	public Sprite getSprite();
}
