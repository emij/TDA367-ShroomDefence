package se.chalmers.tda367.std.core;

/**
 * Represents a score object for use in the highscore.
 * @author Unchanged
 * @date Mar 22, 2012
 */
public class Score implements Comparable<Score> {
	
	private String name;
	private int score;
	
	public Score(String name, int score){
		this.name = name;
		this.score = score;
	}

	/**
	 * @return the name of the player that got the score.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the score achieved.
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Compare two scores to each other.
	 */

	@Override
	public int compareTo(Score o) {
		// TODO implement.
		return 0;
	}
	
	// equals, toString and hashCode goes here.
}
