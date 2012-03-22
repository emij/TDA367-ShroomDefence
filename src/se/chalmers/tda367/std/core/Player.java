package se.chalmers.tda367.std.core;

/**
 * Represents a player. Contains name, score etc.
 * @author Unchanged
 * @date Mar 22, 2012
 */
public class Player {
	
	private String name;
	private int currentScore;
	private int money;
	
	public Player(){
		
	}
	public Player(String name){
		this.name = name;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	

}
