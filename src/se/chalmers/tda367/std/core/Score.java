package se.chalmers.tda367.std.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Represents a score object for use in the highscore.
 * @see {@link se.chalmers.tda367.std.core.Highscore}
 * @author Emil Edholm
 * @modified Johan Gustafsson
 * @date Mar 25, 2012
 */
public final class Score implements Comparable<Score>, Serializable {
	private static final long serialVersionUID = 5019072701109375149L;
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
		int scoreDiff = o.score - score;
		if(scoreDiff != 0)
			return scoreDiff;
		
		return name.compareTo(o.name);
	}
	
	@Override
	public boolean equals(Object rhs){
		if(this == rhs)
			return true;
		
		if(!(rhs instanceof Score))
			return false;
		
		// Now it is safe to cast.
		Score s = (Score)rhs;
		return score == s.score && name.equals(s.name);
	}
	
	@Override
	public String toString(){
		return "Score {" + name + ", " + score + "}";
	}
	
	@Override
	public int hashCode(){
		int result = 17;
		result = 31 * result + score;
		result = 31 * result + name.hashCode();
		
		return result;
	}
	
	/**
	 * Serialize this {@code Score}.
	 * @serialData name is written first then the score.
	 */
	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();
		s.writeObject(name);
		s.writeInt(score);
	}
	
	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		s.defaultReadObject();
		this.name = (String) s.readObject();
		this.score = s.readInt();
	}
}
