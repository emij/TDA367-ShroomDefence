/**
 * TDA367-ShroomDefence
 * 
 * Class explanation goes here.
 */
package se.chalmers.tda367.std.core;

/**
 * @author Unchanged
 * @date Mar 22, 2012
 */
public class Score {
	
	private String name;
	private int score;
	
	public Score(String name, int score){
		this.name = name;
		this.score = score;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	

}
