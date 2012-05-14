package se.chalmers.tda367.std.core;

import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Interface for the players character.
 * @author Johan Gustafsson
 * @date   May 14, 2012
 */
public interface IPlayerCharacter {
	public void moveTo(Position pos);
	
	/**
	 * Gives reference to the character's position
	 * @return position of the character
	 */
	public Position getPos();
	
	/**
	 * @return the sprite (image representation) of the character.
	 */
	public Sprite getSprite();
}
