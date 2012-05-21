package se.chalmers.tda367.std.core;

import com.google.inject.ImplementedBy;

/**
 * Used to represent a human game player. 
 * Contains the game score, amount of money in the treasury etc. 
 * @author Emil Edholm
 * @date   May 21, 2012
 */
@ImplementedBy(Player.class)
public interface IPlayer {

	/**
	 * @return the current player score
	 */
	public abstract int getCurrentScore();

	/**
	 * @param score - the player score to set.
	 */
	public abstract void addScore(int score);

	/**
	 * @return the amount of money in the treasury.
	 */
	public abstract int getMoney();

	/**
	 * @param amount - the amount which will be removed from the treasury
	 */
	public abstract void removeMoney(int money);

	/**
	 * @param amount - the amount which will be added to the treasury
	 */
	public abstract void addMoney(int money);

	/**
	 * @return the name of the player
	 */
	public abstract String getName();

	/**
	 * Set the name of the player. 
	 * @param name to give player.
	 */
	public abstract void setName(String name);

	/**
	 * This will return a reference to this player's character
	 * @return a reference to the player's {@code PlayerCharacter}
	 */
	public abstract IPlayerCharacter getCharacter();

}