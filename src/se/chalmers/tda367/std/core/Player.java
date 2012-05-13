package se.chalmers.tda367.std.core;

import se.chalmers.tda367.std.utilities.Position;

/**
 * Represents a player. Contains name, score etc.
 * @author Unchanged
 * @modified Johan Gustafsson
 * @date Mar 22, 2012
 */
public class Player {
	
	private String name;
	private int currentScore;
	private int money;
	private IPlayerCharacter character;
	
	public Player(){
		
	}
	public Player(String name){
		this.name = name;
		this.character = new PlayerCharacter(new Position(100, 100));
	}
	/**
	 * @return the currentScore
	 */
	public int getCurrentScore() {
		return currentScore;
	}
	/**
	 * @param currentScore the currentScore to set
	 */
	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}
	/**
	 * @return the money
	 */
	public int getMoney() {
		return money;
	}
	/**
	 * @param money the money to set
	 */
	public void setMoney(int money) {
		this.money = money;
	}
	
	/**
	 * @param amount which will be removed
	 */
	public void removeMoney(int money) {
		this.money -= money;
	}
	
	/**
	 * @param amount which will be added
	 */
	public void addMoney(int money) {
		this.money += money;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This will return a reference to this player's character
	 * @return a reference to the player's {@code PlayerCharacter}
	 */
	public IPlayerCharacter getCharacter() {
		return character;
	}
}
